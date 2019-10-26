package net.paragon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctmx_Work_Experince")
public class WorkExperince implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3069172695239142820L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "workExperienceId")
	private Integer workExperienceId;

	private String nameOfOrganization;
	private String position;
	private String endDate;
	private String startDate;

	public WorkExperince() {
	}

	public Integer getWorkExperienceId() {
		return this.workExperienceId;
	}

	public void setWorkExperienceId(Integer workExperienceId) {
		this.workExperienceId = workExperienceId;
	}

	public String getNameOfOrganization() {
		return this.nameOfOrganization;
	}

	public void setNameOfOrganization(String nameOfOrganization) {
		this.nameOfOrganization = nameOfOrganization;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
