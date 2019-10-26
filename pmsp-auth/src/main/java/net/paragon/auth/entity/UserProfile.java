
package net.paragon.auth.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import net.paragon.framework.entity.ObjectBase;

/**
 * 
 * @author ducbui
 * 
 */

@Entity
@Table(name = "user_profile")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM UserProfile u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM UserProfile u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM UserProfile u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM UserProfile u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM UserProfile u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByUserType", query = "SELECT u FROM UserProfile u WHERE u.userType = :userType"),
    @NamedQuery(name = "User.findByActive", query = "SELECT u FROM UserProfile u WHERE u.active = :active")})
public class UserProfile extends ObjectBase {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "login")
    private String login;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64, message = "{LongString}")
    @Column(name = "name")
    private String name;

    @Size(max = 64, message = "{LongString}")
    @Column(name = "user_type")
    private String userType;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private Boolean active;

    @Lob
    @Column(name = "image")
    private byte[] image;

	@SuppressWarnings("deprecation")
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "user")
	@Cascade({ 
		org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
		org.hibernate.annotations.CascadeType.DELETE, 
		org.hibernate.annotations.CascadeType.MERGE,
		org.hibernate.annotations.CascadeType.PERSIST, 
		org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<UserRole> roles = new ArrayList<UserRole>();

    public UserProfile() {
    }

    public UserProfile(String login, String password, String name, Boolean active) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.active = active;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
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
        if (!(object instanceof UserProfile)) {
            return false;
        }

        UserProfile other = (UserProfile) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User[ id=" + getId() + " ]";
    }
    
}
