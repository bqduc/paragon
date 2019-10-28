/**
 * 
 */
package net.paragon.config.repository;

import org.springframework.data.jpa.domain.Specification;

import lombok.Builder;
import net.paragon.config.entity.Configuration;
import net.paragon.framework.model.SearchParameter;
import net.paragon.framework.model.SearchRequest;
import net.paragon.framework.specification.BrillianceSpecifications;

/**
 * @author bqduc
 *
 */
@Builder
public class ConfigurationRepoSpecification extends BrillianceSpecifications<Configuration, SearchRequest>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4115991455691287486L;

	public static Specification<Configuration> buildSpecification(final SearchParameter searchParameter) {
		return ConfigurationRepoSpecification
				.builder()
				.build()
				.buildSpecifications(searchParameter);
	}
}
