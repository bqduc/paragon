package net.paragon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctmx_Blood_Group")
public class BloodGroup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6432523493102844167L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bloodGroupId")
	private Integer bloodGroupId;

	private String bloodGroupName;

	public BloodGroup() {
	}

	public Integer getBloodGroupId() {
		return this.bloodGroupId;
	}

	public void setBloodGroupId(Integer bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	public String getBloodGroupName() {
		return this.bloodGroupName;
	}

	public void setBloodGroupName(String bloodGroupName) {
		this.bloodGroupName = bloodGroupName;
	}

}
