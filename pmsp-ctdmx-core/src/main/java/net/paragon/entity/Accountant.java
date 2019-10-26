package net.paragon.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@Table(name = "ctmx_Employee"  )
@DiscriminatorValue("Accountant")
public class Accountant extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6977575254238099345L;

	public Accountant() {
	}

}
