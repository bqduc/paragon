/**
 * 
 */
package net.paragon.msp.config;

/**
 * @author bqduc
 *
 */
public interface ConfigurationConstants {
	final static String PACKAGE_ROOT = "net.sunrise";
	final static String PACKAGE_DOMAIN = PACKAGE_ROOT + ".*";
	final static String PACKAGE_ENTITY = PACKAGE_ROOT + ".*";
	final static String PACKAGE_REPOSITORY = PACKAGE_ROOT + ".*";



	final static String AUTHENTICATION_REALM = "VPX_REALM";
	final static String REST_API = "/rapi/";

	final static String DEFAULT_LANGUAGE = "vi";
	final static String DEFAULT_COUNTRY = "VN";

	final static String DEFAULT_ENCODING = "UTF-8";
	final static String CSRF_TOKEN = "myToken";
}
