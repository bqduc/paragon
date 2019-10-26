/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.paragon.msp.faces.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import net.paragon.msp.faces.model.FacesCar;
import net.paragon.msp.faces.service.FacesCarService;

@Named
@ViewScoped
public class DataGridMB implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -472083904340582873L;

		private List<FacesCar> cars;
    
    private FacesCar selectedCar;
    
    @Inject
    private FacesCarService service;
    
    @PostConstruct
    public void init() {
        cars = service.createCars(48);
    }

    public List<FacesCar> getCars() {
        return cars;
    }

    public void setService(FacesCarService service) {
        this.service = service;
    }

    public FacesCar getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(FacesCar selectedCar) {
        this.selectedCar = selectedCar;
    }
}
