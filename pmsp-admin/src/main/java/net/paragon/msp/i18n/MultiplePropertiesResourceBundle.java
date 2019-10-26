/**
 * 
 */
package net.paragon.msp.i18n;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import net.paragon.msp.global.GlobalDataRepository;
import net.paragon.utility.CommonUtility;
import net.paragon.utility.ListUtility;

/**
 * <code>MultiplePropertiesResourceBundle</code> is an abstract base implementation to allow to
 * combine a ResourceBundle from multiple properties files whereas these properties files must end
 * with the same name - the base-name for these combined ResourceBundle.
 * <p>
 * A concrete implementation must subclass this class and provide a default constructor in which
 * <code>super("base-name");</code> or <code>super("package.name","base-name");</code> must be
 * called depending on if your properties files are located in default or a specific package.
 * </p>
 * 
 * <pre>
 * public class ExampleResourceBundle extends MultiplePropertiesResourceBundle {
 * 	public ExampleResourceBundle() {
 * 		super(&quot;example&quot;);
 * 	}
 * }
 * 
 * or 
 * 
 * public class ExampleResourceBundle extends MultiplePropertiesResourceBundle {
 * 	public ExampleResourceBundle() {
 * 		super(&quot;my.package&quot;, &quot;example&quot;);
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * For each Locale that you need to support you also must provide Locale variants of your java
 * ResourceBindle class as shown below. Creating an empty subclass of the above class does the job -
 * the separate class is needed to let {@link ResourceBundle#getBundle(String, Locale)} find and
 * cache your bundle with the right Locale.
 * </p>
 * 
 * <pre>
 * public class ExampleResourceBundle_de extends ExampleResourceBundle {
 * }
 * </pre>
 * 
 * <h3>File name rules</h3>
 * <p>
 * To allow automatic detection of the multiple properties files, for each filename you must provide
 * a general properties file without any Locale extension in the name (e.g.
 * additional-example.properties as a variant to examples.properties) - otherwise that properties
 * file name will not be used to load as PropertyResourceBundle. Let's assume we used the base-name
 * "example" and the following list of properties files are reachable:
 * </p>
 * <ul>
 * <li>example.properties</li>
 * <li>example_de.properties</li>
 * <li>example_en.properties</li>
 * <li>additional-example.properties</li>
 * <li>additional-example_en.properties</li>
 * <li>another-example_de.properties</li>
 * </ul>
 * <p>
 * Only <i>example</i> and <i>additional-example</i> will be used as base-names to load this
 * <code>MultiplePropertiesResourceBundle</code> - <i>another-example.properties</i> is missing
 * and therefore <i>another-example</i> is not detected as a valid base-name.
 * </p>
 * <p>
 * It is also supported to provide additional properties files with a jar file. To make sure that
 * jar file is recognized as properties file provider it must contain a file "base-name".properties
 * (e.g. example.properties). This marker file may be empty and is only used to find all resource
 * paths containing properties files of interest (with matching base-name). In fact, every path
 * location containing properties files to be combined into one MultiplePropertiesResourceBundle
 * must contain that "base-name".properties file.
 * </p>
 * <p>
 * The load order of the properties files is base-named file first and then the additional 
 * properties files in natural sort order of the file name. Properties files loaded later may 
 * override previously loaded properties.
 * </p>
 * 
 * @author ducbui
 *
 */
public abstract class MultiplePropertiesResourceBundle extends ResourceBundle {
	private final static String SEPARATOR = ";";
	private static final String CLASS = MultiplePropertiesResourceBundle.class.getName();

	/** private Logger instance */
	private static final Logger LOG = Logger.getLogger(CLASS);

	private Locale locale = GlobalDataRepository.builder().build().getDefaultLocale();
	/**
	 * The base name for the ResourceBundles to load in.
	 */
	private String baseName;

	/**
	 * The package name where the properties files should be.
	 */
	private String packageName;
	private String[] packageNames;

	/**
	 * A Map containing the combined resources of all parts building this
	 * MultiplePropertiesResourceBundle.
	 */
	private Map<String, Object> combined;

	/**
	 * Construct a <code>MultiplePropertiesResourceBundle</code> for the passed in base-name.
	 * 
	 * @param baseName
	 *          the base-name that must be part of the properties file names.
	 */
	/*protected MultiplePropertiesResourceBundle(String baseName) {
		this(null, baseName);
	}*/

	/**
	 * Construct a <code>MultiplePropertiesResourceBundle</code> for the passed in base-name.
	 * 
	 * @param packageName
	 *          the package name where the properties files should be.
	 * @param baseName
	 *          the base-name that must be part of the properties file names.
	 */
	protected MultiplePropertiesResourceBundle(String packageName, String baseName) {
		this.packageName = packageName;
		this.baseName = baseName;
	}

	/**
	 * Construct a <code>MultiplePropertiesResourceBundle</code> for the passed in base-name.
	 * 
	 * @param packageName
	 *          the package name where the properties files should be.
	 * @param baseName
	 *          the base-name that must be part of the properties file names.
	 */
	protected MultiplePropertiesResourceBundle(String[] packageNames, String baseName) {
		this.packageNames = new String[packageNames.length];
		for (int i = 0; i < packageNames.length; ++i) {
			this.packageNames[i] = packageNames[i];
		}
		this.baseName = baseName;
	}

	@Override
	public Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}
		loadBundlesOnce();
		return combined.get(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		loadBundlesOnce();
		ResourceBundle parent = this.parent;
		return new ResourceBundleEnumeration(combined.keySet(), (parent != null) ? parent.getKeys()
				: null);
	}

	/**
	 * Load the resources once.
	 */
	private void loadBundlesOnce() {
		if (combined == null || combined.isEmpty()) {
			combined = new HashMap<String, Object>(128);

			String currentLocaleInfo = this.locale.toString();
			List<String> bundleNames = findBaseNames(baseName);
			for (String bundleName : bundleNames) {
				if (!bundleName.contains(currentLocaleInfo))
					continue;

				ResourceBundle bundle = ResourceBundle.getBundle(bundleName, this.locale);
				Enumeration<String> keys = bundle.getKeys();
				String key = null;
				while (keys.hasMoreElements()) {
					key = keys.nextElement();
					combined.put(key, bundle.getObject(key));
				}
			}
		}
	}

	/**
	 * Return a Set with the real base-names of the multiple properties based resource bundles that
	 * contribute to the full set of resources.
	 * 
	 * @param baseName
	 *          the base-name that must be part of the properties file names.
	 * @return a List with the real base-names.
	 */
	private List<String> findBaseNames(final String baseName) {
		if (baseName.contains("*"))
			return findCustomBaseNames(baseName);

		String name = null;
		String path = null;
		String filename = null;
		File dir = null;
		URL jarUrl = null;
		JarFile jar = null;
		JarEntry entry = null;
		final String METHOD = "findBaseNames";
		boolean isLoggable = LOG.isLoggable(Level.FINE);

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		List<String> bundleNames = new ArrayList<String>();
		try {
			String baseFileName = baseName + ".properties";
			String resourcePath = getResourcePath();
			String resourceName = resourcePath + baseFileName;
			if (isLoggable) {
				LOG.logp(Level.FINE, CLASS, METHOD, "Looking for files named '" + resourceName + "'");
			}

			Enumeration<URL> names = cl.getResources(resourceName);
			while (names.hasMoreElements()) {
				jarUrl = names.nextElement();
				if (isLoggable) {
					LOG.logp(Level.FINE, CLASS, METHOD, "inspecting: " + jarUrl);
				}
				if ("jar".equals(jarUrl.getProtocol())) {
					path = jarUrl.getFile();
					filename = path.substring(0, path.length() - resourceName.length() - 2);
					if (filename.startsWith("file:")) {
						filename = filename.substring(5);
					}
					jar = new JarFile(filename);
					for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();) {
						entry = entries.nextElement();
						name = entry.getName();
						addMatchingNameOnce("", baseName, bundleNames, baseFileName, name);
					}
					jar.close();
				} else {
					dir = new File(jarUrl.getFile());
					dir = dir.getParentFile();
					if (dir.isDirectory()) {
						for (String fileName : dir.list()) {
							addMatchingNameOnce(resourcePath, baseName, bundleNames, baseFileName, fileName);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, CLASS, e.getMessage());
			e.printStackTrace();
		}

		Collections.sort(bundleNames, new Comparator<String>() {

			public int compare(String o1, String o2) {
				int rc = 0;
				if (baseName.equals(o1)) {
					rc = -1;
				} else if (baseName.equals(o2)) {
					rc = 1;
				} else {
					rc = o1.compareTo(o2);
				}
				return rc;
			}

		});

		if (isLoggable) {
			LOG.logp(Level.FINE, CLASS, METHOD, "Combine ResourceBundles named: " + bundleNames);
		}
		return bundleNames;
	}

	private List<String> findCustomBaseNames(final String baseName) {
		final String METHOD = "findBaseNames";
		boolean isLoggable = LOG.isLoggable(Level.FINE);

		String resourceName = null;
		ResourceBundle bundle = null;
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
		List<String> bundleNames = new ArrayList<String>();
		List<String> packageBundleNames = null;
		try {
			String baseFileName = baseName /*+ ".properties"*/;
			if (!baseName.endsWith(".properties")) {
				baseFileName += ".properties";
			}

			if (this.packageNames.length > 0 ) {
				for (String resourcePath :this.packageNames) {
					resourceName = convertResourcePath(resourcePath) + baseFileName;
					packageBundleNames = getResourceBundleNames(resolver, resourcePath, resourceName);
					if (CommonUtility.isNotEmpty(packageBundleNames)) {
						bundleNames.addAll(packageBundleNames);
					}
				}
			} else {
				String resourcePath = getResourcePath();
				resourceName = resourcePath + baseFileName;
				if (isLoggable) {
					LOG.logp(Level.FINE, CLASS, METHOD, "Looking for files named '" + resourceName + "'");
				}

				Resource[] resources = resolver.getResources(resourceName);
				for (Resource resource :resources) {//resource.getFile().getName()
					try {
						bundle = ResourceBundle.getBundle(resourcePath + "/" + resource.getFilename().replace(".properties", ""), this.locale);
					} catch (NullPointerException | MissingResourceException e) {
						bundle = null;
					}
					if (null != bundle && !bundleNames.contains(bundle.getBaseBundleName())) {
						bundleNames.add(bundle.getBaseBundleName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(bundleNames, new Comparator<String>() {

			public int compare(String o1, String o2) {
				int rc = 0;
				if (baseName.equals(o1)) {
					rc = -1;
				} else if (baseName.equals(o2)) {
					rc = 1;
				} else {
					rc = o1.compareTo(o2);
				}
				return rc;
			}

		});
		if (isLoggable) {
			LOG.logp(Level.FINE, CLASS, METHOD, "Combine ResourceBundles named: " + bundleNames);
		}
		return bundleNames;
	}

	private List<String> getResourceBundleNames(PathMatchingResourcePatternResolver resolver, String resourcePath, String resourceName) throws IOException{
		List<String> resourceBundleNames = ListUtility.createArrayList();
		Resource[] resources = resolver.getResources(resourceName);
		ResourceBundle bundle = null;
		for (Resource resource :resources) {
			try {
				bundle = ResourceBundle.getBundle(resourcePath + "/" + resource.getFilename().replace(".properties", ""), this.locale);
			} catch (NullPointerException | MissingResourceException e) {
				bundle = null;
			}

			if (null != bundle && !resourceBundleNames.contains(bundle.getBaseBundleName())) {
				resourceBundleNames.add(bundle.getBaseBundleName());
			}
		}

		return resourceBundleNames;
	}

	private List<String> eliminateLocaleInfo(List<String> baseBundleNames) {
		List<String> eliminatedLocales = ListUtility.createDataList();
		Map<String, Locale> supportedLocaleMap = GlobalDataRepository.builder().build().getSupportedLocaleMap();
		for (String baseBundleName :baseBundleNames) {
		}
		return eliminatedLocales;
	}

	private String getResourcePath() {
		String result = "";
		if (packageName != null) {
			result = packageName.replaceAll("\\.", "/") + "/";
		}
		return result;
	}

	private String convertResourcePath(String resourcePath) {
		String result = "";
		if (resourcePath != null) {
			result = resourcePath.replaceAll("\\.", "/") + "/";
		}
		return result;
	}

	private String[] getResourcePaths() {
		String resourcePaths[] = null;
		if (packageName != null && this.packageName.contains(SEPARATOR)) {
			resourcePaths = packageName.split(SEPARATOR);
			for (String resourcePath :resourcePaths) {
				resourcePath = resourcePath.replaceAll("\\.", "/") + "/";
			}
		}
		return resourcePaths;
	}

	private void addMatchingNameOnce(String resourcePath, String baseName, List<String> bundleNames,
			String baseFileName, String name) {
		int prefixed = name.indexOf(baseName);
		if (prefixed > -1 && name.endsWith(baseFileName)) {
			String toAdd = resourcePath + name.substring(0, prefixed) + baseName;
			if (!bundleNames.contains(toAdd)) {
				bundleNames.add(toAdd);
			}
		}
	}

}
