package net.paragon.msp.faces.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FlowEvent;

import net.paragon.msp.faces.model.User;

@Named
@ViewScoped
public class UserWizardMB implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -5894033064204770396L;

	private User user = new User();

	private boolean skip;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void save() {
		FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getFirstname());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}
}
