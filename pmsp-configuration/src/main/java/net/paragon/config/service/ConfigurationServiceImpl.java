package net.paragon.config.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.paragon.config.entity.Configuration;
import net.paragon.config.repository.ConfigurationRepoSpecification;
import net.paragon.config.repository.ConfigurationRepository;
import net.paragon.exceptions.MspDataException;
import net.paragon.exceptions.ObjectNotFoundException;
import net.paragon.framework.model.SearchParameter;
import net.paragon.framework.repository.BaseRepository;
import net.paragon.framework.service.GenericServiceImpl;

@Service
public class ConfigurationServiceImpl extends GenericServiceImpl<Configuration, Long> implements ConfigurationService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435351574637430464L;
	@Inject 
	private ConfigurationRepository repository;
	
	protected BaseRepository<Configuration, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Optional<Configuration> getOne(String name) throws ObjectNotFoundException {
		return repository.findByName(name);
	}

	@Override
	protected Page<Configuration> performSearch(String keyword, Pageable pageable) {
		return repository.search(keyword, pageable);
	}

	@Override
	public Page<Configuration> getObjects(SearchParameter searchParameter) {
		return this.repository.findAll(ConfigurationRepoSpecification.buildSpecification(searchParameter), searchParameter.getPageable());
	}

	@Override
	public List<Configuration> getByGroup(String group) {
		return this.repository.findByGroup(group);
	}

	@Override
	protected Optional<Configuration> fetchBusinessObject(Object key) throws MspDataException {
		return super.getBizObject("findByName", key);
	}
}
