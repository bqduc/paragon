/**
 * 
 */
package net.paragon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.paragon.framework.entity.BizObjectBase;

/**
 * Prawn seed (category)
 * @author bqduc
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "seed")
public class Seed extends BizObjectBase {
	@Column(name = "name")
	private String name;

	@Column(name = "comments")
	private String comments;

	/**A place where eggs are hatched under artificial conditions (especially fish eggs)*/
	@Column(name = "hatchery")
	private String hatchery;

	public String getHatchery() {
		return hatchery;
	}

	public void setHatchery(String hatchery) {
		this.hatchery = hatchery;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}