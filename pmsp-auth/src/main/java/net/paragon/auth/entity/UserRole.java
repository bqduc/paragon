package net.paragon.auth.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.paragon.framework.entity.ObjectBase;

/**
 * 
 * @author ducbq
 */
@Entity
@Table(name = "user_role")
public class UserRole extends ObjectBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5738835622345821174L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserProfile user;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
