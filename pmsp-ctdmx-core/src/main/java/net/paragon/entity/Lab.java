package net.paragon.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@Table(name = "ctmx_Employee")
@DiscriminatorValue("Lab")
public class Lab extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -144138261607287477L;

	public Lab() {
	}

}
