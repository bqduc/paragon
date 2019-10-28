package net.paragon.config.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import net.paragon.config.entity.Configuration;
import net.paragon.exceptions.ObjectNotFoundException;
import net.paragon.framework.model.SearchParameter;
import net.paragon.framework.service.GenericService;

public interface ConfigurationService extends GenericService<Configuration, Long> {

	/**
	 * Get one Configuration with the provided name.
	 * 
	 * @param code
	 *            The Configuration name
	 * @return The Configuration
	 * @throws ObjectNotFoundException
	 *             If no such Configuration exists.
	 */
	Optional<Configuration> getOne(String name) throws ObjectNotFoundException;

	/**
	 * Get one Configurations with the provided search parameters.
	 * 
	 * @param searchParameter
	 *            The search parameter
	 * @return The pageable Configurations
	 */
	Page<Configuration> getObjects(SearchParameter searchParameter);

	/**
	 * Get one Configurations with the provided group.
	 * 
	 * @param group
	 *            The search group
	 * @return The pageable Configurations
	 */
	List<Configuration> getByGroup(String group);
}
