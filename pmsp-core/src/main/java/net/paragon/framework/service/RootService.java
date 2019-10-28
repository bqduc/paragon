/**
 * 
 */
package net.paragon.framework.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import net.paragon.framework.component.RootComponent;
import net.paragon.utility.CommonConstants;

/**
 * @author bqduc
 *
 */
public abstract class RootService<T, PK extends Serializable> extends RootComponent{
	private static final long serialVersionUID = 3028267108934028604L;

	protected final Page<T> DUMMY_PAGEABLE = new PageImpl<T>(new ArrayList<T>());

	protected abstract Page<T> performSearch(String keyword, Pageable pageable);

	public Page<T> search(String keyword, Pageable pageable){
		return performSearch(keyword, pageable);
	}

	public Page<T> search(Map<String, Object> parameters) {
		String keyword = (String)parameters.get(CommonConstants.PARAM_KEYWORD);
		Pageable pageable = (Pageable)parameters.get(CommonConstants.PARAM_PAGEABLE);
		return performSearch(keyword, pageable);
	}

	protected T getOptionalObject(Optional<T> optObject) {
		if (optObject.isPresent())
			return optObject.get();

		return null;
	}
}
