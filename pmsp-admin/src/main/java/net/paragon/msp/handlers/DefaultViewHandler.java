/**
 * 
 */
package net.paragon.msp.handlers;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sun.faces.application.view.MultiViewHandler;

import net.paragon.msp.global.GlobalDataRepository;

/**
 * @author ducbui
 *
 */
public class DefaultViewHandler extends MultiViewHandler {
	private GlobalDataRepository globalDataRepository = GlobalDataRepository.builder().build();

	@Override
	public Locale calculateLocale(FacesContext context) {
		Locale locale = null;
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		if (session != null) {
			// Return the locale saved by the managed bean earlier
			locale = (Locale) session.getAttribute(GlobalDataRepository.SESSION_LOCALE);
			if (locale != null) {
				return locale;
			} else {
				locale = globalDataRepository.getDefaultLocale();
				session.setAttribute(GlobalDataRepository.SESSION_LOCALE, locale);
				return locale;
			}
		}
		return super.calculateLocale(context);
	}

}
