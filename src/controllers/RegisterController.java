package controllers;

import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import beans.User;
import business.UserInterface;
import util.RegistrationFailedException;


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
			boolean usr = service.create(user);
			if (usr == false) {
				return "RegistrationPage.xhtml";
			}
		} catch (RegistrationFailedException e) {
			System.out.println("======================== User not not added to data base");
		}

		return "RegistrationSuccess.xhtml"; // return view
	}
}
