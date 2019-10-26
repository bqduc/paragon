/**
 * 
 */
package net.paragon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.paragon.framework.entity.BizObjectBase;

/**
 * @author bqduc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "medicine")
public class Medicine extends BizObjectBase {
	@Column(name="code", unique=true, nullable=false)
	private String code;

	@Column(name="name", unique=true, nullable=false)
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Medicine(){
	}

	public Medicine(String code, String name){
		this.code = code;
		this.name = name;
	}
}
