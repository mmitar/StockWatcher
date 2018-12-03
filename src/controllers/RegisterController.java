package controllers;

import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.User;
import business.UserInterface;


/**
 * Moderates register modules within the app. No DB is currently implemented.
 * @author Matthew & Joey
 *
 */

@ManagedBean
@ViewScoped
public class RegisterController {
	
	private String redirect = null;
	private String error = null;
	
	/**
	 * @injects UserService methods
	 */
	@EJB
	private UserInterface service;
	
	/**
	 * Controller method on Submit in takes a user from user Model and returns a String for registration.
	 * 
	 * @param user User
	 * @return view String
	 */
	public String registerUser(User user) 
	{
		try 
		{
			// Call UserService.create
			this.service.create(user);
		} 
		// If User exists / Error creating user
		catch (Exception e) 
		{
			this.redirect = "RegistrationPage.xhtml";
			this.error = "User name already exists";
		}

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		
		// Redirects User if Error occured
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		return "RegistrationSuccess.xhtml"; // return view
	}
}
