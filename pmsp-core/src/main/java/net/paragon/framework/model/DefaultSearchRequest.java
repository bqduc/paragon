/**
 * 
 */
package net.paragon.framework.model;

/**
 * @author bqduc
 *
 */
public class DefaultSearchRequest extends SearchRequest{
	public final static String fieldCode = "code";

	private String code;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
