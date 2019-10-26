/**
 * 
 */
package net.paragon.msp.global;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;

import lombok.Builder;
import net.paragon.utility.CommonUtility;
import net.paragon.utility.ListUtility;

/**
 * @author ducbui
 *
 */
@Builder
public class GlobalDataRepository {
	public final static String SESSION_LOCALE = "locale";

	private static List<Locale> supportedLocales = ListUtility.createArrayList();
	private static Map<String, Locale> supportedLocaleMap = ListUtility.createMap();

	@Builder.Default
	private Locale defaultLocale = getVietnameseLocale();

	private static Locale getVietnameseLocale() {
		return new Locale("vi", "VN");
	}

	public Locale getDefaultLocale() {
		return this.defaultLocale;
	}

	public List<Locale> getSupportLocales(){
		if (CommonUtility.isNotEmpty(supportedLocales))
			return supportedLocales;

		Iterator<Locale> itr = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		while (itr.hasNext()) {
			supportedLocales.add(itr.next());
		}

		return supportedLocales;
	}

	public Map<String, Locale> getSupportedLocaleMap(){
		if (supportedLocaleMap.isEmpty()) {
  		List<Locale> supportedLocales = getSupportLocales();
  		for (Locale supportedLocale :supportedLocales) {
  			supportedLocaleMap.put(supportedLocale.toString(), supportedLocale);
  		}
		}
		return supportedLocaleMap;
	}
}
