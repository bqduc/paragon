/**
 * 
 */
package net.paragon.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;

import net.paragon.framework.component.RootComponent;

/**
 * @author bqduc
 *
 */
public abstract class RootController extends RootComponent {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2445529753237451206L;

	protected static final String  DEFAULT_PAGE_SIZE = "100";
  protected static final String DEFAULT_PAGE_NUM = "0";

	@Inject
	protected MessageSource messageSource;

	@Inject
	protected HttpSession httpSession;

	protected void cachePut(String key, Object data){
		this.httpSession.setAttribute(key, data);
	}

	protected Object cacheGet(String key){
		return this.httpSession.getAttribute(key);
	}

}
