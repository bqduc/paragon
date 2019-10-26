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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import net.paragon.msp.faces.model.FacesCar;

@Named
@ViewScoped
public class TabViewMB implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7903397415183640826L;
		private List<FacesCar> cars;

    @PostConstruct
    public void init() {
        cars = new ArrayList<FacesCar>();
        cars.add(new FacesCar("Y25YIH5", "Fiat", 2014, "Black", 10000, true));
        cars.add(new FacesCar("JHF261G", "BMW", 2013, "Blue", 50000, true));
        cars.add(new FacesCar("HSFY23H", "Ford", 2012, "Black", 35000, false));
        cars.add(new FacesCar("GMDK353", "Volvo", 2014, "White", 40000, true));
        cars.add(new FacesCar("345GKM5", "Jaguar", 2011, "Gray", 48000, false));
    }
    
    public List<FacesCar> getCars() {
        return cars;
    }
    
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
        
    public void onTabClose(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
