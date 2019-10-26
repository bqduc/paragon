package net.paragon.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity (name="Doctor")
//@Table(name = "ctmx_Employee")
@DiscriminatorValue("Doctor")
public class Doctor extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 285475880392133340L;

	public Doctor() {
	}

}
