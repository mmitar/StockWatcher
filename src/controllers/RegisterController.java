package controllers;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
	/**
	 * Registers the User Model and navigates them to the Success page
	 * 
	 * @param user: User
	 * @return view: String
	 */
	
	@Inject
	UserInterface service;
	
//	public UserInterface getService() {
//		return service;
//	}
	
	/**
	 * Controller method on Submit in takes a user from user Model and returns a String for registration.
	 *  
	 * @return View
	 */
	
	public String registerUser(User user) 
	{
		
		try {
			service.create(user);
		} catch (UserFoundException e) {
			
			String error = "User already exists.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error", error);
			return "RegistrationPage.xhtml";
		} catch (UserErrorException e) {
			
			String error = "Error creating new account.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error", error);
			return "RegistrationPage.xhtml";
		}

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		return "RegistrationSuccess.xhtml"; // return view
	}
}
