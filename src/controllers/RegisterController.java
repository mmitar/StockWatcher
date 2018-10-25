package controllers;

import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.User;
import business.UserInterface;
import util.UserErrorException;
import util.UserFoundException;


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
	 * Registers the User Model and navigates them to the Success page
	 * 
	 * @param user: User
	 * @return view: String
	 */
	@EJB
	private UserInterface service;

	/**
	 * Controller method on Submit in takes a user from user Model and returns a String for registration.
	 *  
	 * @return View
	 */
	public String registerUser(User user) 
	{
		try 
		{
			this.service.create(user);
		} 
		catch (Exception e) 
		{
			this.redirect = "RegistrationPage.xhtml";
			this.error = "User Exists.";
		}

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		return "RegistrationSuccess.xhtml"; // return view
	}
}
