/*
* Copyright 2017, Bui Quy Duc
* by the @authors tag. See the LICENCE in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package net.paragon.config.manager;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.paragon.config.entity.Configuration;
import net.paragon.config.entity.ConfigurationDetail;
import net.paragon.config.repository.ConfigurationDetailRepository;
import net.paragon.config.service.ConfigurationService;
import net.paragon.exceptions.EcosysException;
import net.paragon.framework.component.RootComponent;
import net.paragon.global.WebAdminConstants;
import net.paragon.model.DateTimePatterns;
import net.paragon.utility.CommonConstants;

/**
 * Configuration service implementation. Provides implementation of the configuration
 * 
 * @author bqduc
 *
 */

@Service
@Transactional
public class ConfigurationManager extends RootComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5899346878484014565L;

	@Inject
	private ConfigurationService configurationService;

	@Inject
	private ConfigurationDetailRepository configurationDetailReppository;

	public Optional<Configuration> getConfigurationByName(String name) {
		return configurationService.getOne(name);
	}

	public void initializeMasterData() throws EcosysException{
		log.info("Setup the default configurations for date/time ");
		this.setupDateTimeConfigurations();

		log.info("Leave the setup master data of configuration");
	}

	protected void setupDateTimeConfigurations() {
		//Setup default date formats
		Configuration config = null;
		Optional<Configuration> optConfig = this.configurationService.getOne(WebAdminConstants.CONFIG_DATE_FORMAT);
		if (!optConfig.isPresent()) {
			StringBuilder dateFormats = new StringBuilder();
			for (DateTimePatterns dateTimePattern :DateTimePatterns.values()) {
				dateFormats.append(dateTimePattern.getDateTimePattern()).append(CommonConstants.SEMICOLON);				
			}

			config = Configuration.builder()
					.group(WebAdminConstants.CONFIG_GROUP_DATE_TIME)
					.name(WebAdminConstants.CONFIG_DATE_FORMAT)
					.value(dateFormats.toString())
					.build();
			this.configurationService.saveOrUpdate(config);
		}
	}

	public List<ConfigurationDetail> getConfigurationDetails(Configuration configuration){
		return this.configurationDetailReppository.findByConfiguration(configuration);
	}
}
