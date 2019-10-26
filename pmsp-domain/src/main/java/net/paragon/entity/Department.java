/**
 * 
 */
package net.paragon.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.paragon.framework.entity.BizObjectBase;

/**
 * @author ducbui
 */
/*@Entity
@Table
public class Department extends BizObjectBase {
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 2428090921013713652L;

	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<Employee> employees = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
*/