/**
 * 
 */
package net.paragon.msp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import net.paragon.utility.UserLocale;

/**
 * @author ducbui
 *
 */
@Named(value = "localeController")
@SessionScoped
public class LocaleController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8125527130882332498L;

	private Locale locale;

	@PostConstruct
	public void init() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void changeLanguage(String language) throws IOException {
		if ("vi".equalsIgnoreCase(language)){
			this.locale = Locale.forLanguageTag("vi-VN");//new Locale(language, "VN");
		} else {
			this.locale = Locale.US;
		}
			
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}

	@Produces
	@UserLocale
	public Locale getLocale() {
		return locale;
	}
}
