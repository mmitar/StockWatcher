package controllers;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.User;
import business.UserInterface;
import util.InterceptorLogging;
import util.UserFoundException;


/**
 * Moderates register modules within the app. No DB is currently implemented.
 * @author Matthew & Joey
 *
 */
@ManagedBean
@ViewScoped
@Interceptors(InterceptorLogging.class)
public class RegisterController implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String redirect = null;
	private String error = null;
	
	/**
	 * Registers the User Model and navigates them to the Success page
	 * 
	 * @param user: User
	 * @return view: String
	 */
	@Inject
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
			// Call Service to create the user
			this.service.create(user);
		} 
		// Catch a User Found Exception if the create method fails
		catch (UserFoundException e) 
		{
			this.redirect = "RegistrationPage.xhtml";
			this.error = "User name already exists";
		}

		// Set a user instance to the Faces Context.
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		
		// Redirect user to the exception view
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		 // return view
		return "RegistrationSuccess.xhtml";
	}
}
