/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.paragon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author rmpestano
 */
@Entity
@Table(name = "car")
public class Car implements Serializable, Comparable<Car> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 764655991815787294L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "model")
	private String model;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Double price;

	public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Car model(String model) {
        this.model = model;
        return this;
    }

    public Car price(Double price) {
        this.price = price;
        return this;
    }

    public Car name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public boolean hasModel() {
        return model != null && !"".equals(model.trim());
    }

    public boolean hasName() {
        return name != null && !"".equals(name.trim());
    }

    @Override
    public int compareTo(Car o) {
        return this.id.compareTo(o.getId());
    }
}
