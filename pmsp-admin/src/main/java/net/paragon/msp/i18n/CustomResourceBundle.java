 package net.paragon.msp.i18n;

/**
 * Adds support for UTF-8 based bundles for admin i18n messages
 * 
 * Taken from: https://stackoverflow.com/a/3646601/1260910
 *
 * @author rafael-pestano
 */
public class CustomResourceBundle extends MultiplePropertiesResourceBundle {

    protected static final String BUNDLE_NAME = "admin";
    protected static final String BUNDLE_EXTENSION = "properties";
    protected static final String CHARSET = "UTF-8";

    public CustomResourceBundle() {
    	//super("i18n/*." + BUNDLE_EXTENSION);
    	//super("net.paragon.resources.i18n", "*.properties");//ok. Fine
    	super(new String[] {"i18n.admin", "i18n.general"}, 
    			"*.properties");//ok. Fine
    }
}
