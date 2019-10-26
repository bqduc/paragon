/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paragon.msp.faces.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.DragDropEvent;

import net.paragon.msp.faces.model.FacesCar;
import net.paragon.msp.faces.service.FacesCarService;

/**
 *
 * @author rafael-pestano
 */
@Named
@ViewScoped
public class DragDropMB implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -6791638735834760154L;

		@Inject
    private FacesCarService service;
 
    private List<FacesCar> cars;
     
    private List<FacesCar> droppedCars;
     
    private FacesCar selectedCar;
     
    @PostConstruct
    public void init() {
        cars = service.createCars(9);
        droppedCars = new ArrayList<>();
    }
     
    public void onCarDrop(DragDropEvent ddEvent) {
        FacesCar car = ((FacesCar) ddEvent.getData());
  
        droppedCars.add(car);
        cars.remove(car);
    }
     
    public void setService(FacesCarService service) {
        this.service = service;
    }
 
    public List<FacesCar> getCars() {
        return cars;
    }
 
    public List<FacesCar> getDroppedCars() {
        return droppedCars;
    }    
 
    public FacesCar getSelectedCar() {
        return selectedCar;
    }
 
    public void setSelectedCar(FacesCar selectedCar) {
        this.selectedCar = selectedCar;
    }
    
}
