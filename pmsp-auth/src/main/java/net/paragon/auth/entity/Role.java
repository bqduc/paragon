
package net.paragon.auth.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.paragon.framework.entity.ObjectBase;

/**
 * 
 * @author ducbq
 * 
 */

@Entity
@Table(name = "role")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT u FROM UserProfile u"),
    @NamedQuery(name = "Role.findById", query = "SELECT u FROM UserProfile u WHERE u.id = :id"),
    @NamedQuery(name = "Role.findByLogin", query = "SELECT u FROM UserProfile u WHERE u.login = :login"),
    @NamedQuery(name = "Role.findByPassword", query = "SELECT u FROM UserProfile u WHERE u.password = :password"),
    @NamedQuery(name = "Role.findByName", query = "SELECT u FROM UserProfile u WHERE u.name = :name"),
    @NamedQuery(name = "Role.findByUserType", query = "SELECT u FROM UserProfile u WHERE u.userType = :userType"),
    @NamedQuery(name = "Role.findByActive", query = "SELECT u FROM UserProfile u WHERE u.active = :active")})
public class Role extends ObjectBase {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private Boolean active;

	@Column(name = "info")
	private String info;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role[ id=" + getId() + " ]";
    }
    
}
