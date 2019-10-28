/**
 * 
 */
package net.paragon.framework.specification;

import org.springframework.data.jpa.domain.Specification;

import lombok.Builder;
import net.paragon.framework.model.SearchParameter;

/**
 * @author bqduc
 *
 */
@Builder
public class DefaultSpecification<UserType, UserRequest> extends BrillianceSpecifications<UserType, UserRequest>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -144516463300852783L;

	public Specification<UserType> buildSpecification(final SearchParameter searchParameter) {
		return super.buildSpecifications(searchParameter);
	}
}
