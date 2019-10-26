package net.paragon.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@Table(name = "ctmx_Employee")
@DiscriminatorValue("Xray")
public class Xray extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8770316482532412869L;

	public Xray() {
	}

}
