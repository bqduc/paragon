package net.paragon.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@Table(name = "ctmx_Employee")
@DiscriminatorValue("pharmacist")
public class Pharmatiest extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8727578516369299611L;

	public Pharmatiest() {
	}

}
