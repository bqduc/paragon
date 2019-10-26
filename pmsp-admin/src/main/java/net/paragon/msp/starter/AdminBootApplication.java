/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.paragon.msp.starter;

import java.util.List;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import net.paragon.entity.Car;
import net.paragon.msp.config.BaseConfiguration;
import net.paragon.msp.config.SecurityConfig;
import net.paragon.msp.config.SpringInternationalizationConfig;
import net.paragon.msp.util.Utils;

/**
 * @author rmpestano
 */
@Import(value = { 
		BaseConfiguration.class, 
		SecurityConfig.class, 
		SpringInternationalizationConfig.class})
@SpringBootApplication
public class AdminBootApplication {
	@Inject
	private Utils utils;

    @Bean
    public List<Car> getCars() {
        return utils.getCars();
    }
	
}
