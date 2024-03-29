package net.paragon.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctmx_Room_Type")
public class RoomType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8798537514154014818L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roomTypeId")
	private Integer roomTypeId;

	private String typeOfroom;
	
	private double roomCost;

	public RoomType() {
	}

	public Integer getRoomTypeId() {
		return this.roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getTypeOfroom() {
		return this.typeOfroom;
	}

	public void setTypeOfroom(String typeOfroom) {
		this.typeOfroom = typeOfroom;
	}

	public double getRoomCost() {
		return roomCost;
	}

	public void setRoomCost(double roomCost) {
		this.roomCost = roomCost;
	}

}
