package controllers;

import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.User;
import business.UserInterface;
import util.AccountErrorException;
import util.AccountFoundException;


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
	
	@EJB
	UserInterface service;
	
	public UserInterface getService() {
		return service;
	}
	
	/**
	 * Controller method on Submit in takes a user from user Model and returns a String for registration.
	 *  
	 * @return View
	 */
	
	public String onSubmit(User user) 
	{
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		try {
			
			service.create(user);

			return "RegistrationSuccess.xhtml"; // return view
			
		} catch (AccountFoundException e) {
			
			String error = "User already exists.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error", error);
			return "RegistrationPage.xhtml";
			
		} catch (AccountErrorException e) {
			
			String error = "Error creating new account.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error", error);
			return "RegistrationPage.xhtml";
		}
	}
}
