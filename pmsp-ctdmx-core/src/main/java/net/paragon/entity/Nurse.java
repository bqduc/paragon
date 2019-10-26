package net.paragon.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Table(name = "ctmx_Employee")
@DiscriminatorValue("Nurse")
public class Nurse extends Employee implements Serializable {


/**
	 * 
	 */
	private static final long serialVersionUID = -7074715529494965210L;

//	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Room.class, cascade = { CascadeType.ALL })
//	@JoinTable(name = "nurseID_roomID", joinColumns = { @JoinColumn(name = "nurse_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "room_id") })
//	private List<Room> rooms = new ArrayList<>();

	public Nurse() {
	}

	


}
