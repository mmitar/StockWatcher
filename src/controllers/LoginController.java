package controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import beans.User;
import business.UserInterface;
import util.InterceptorLogging;
import util.UserNotFoundException;

/**
 * Validates login modules within the app.
 * 
 * @author Matthew & Joey
 *
 */
@ManagedBean
@ViewScoped
@Interceptors(InterceptorLogging.class)
public class LoginController implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String redirect = null;
	private String error = null;
	
	/**
	 * Valides the User Model and navigates them to the homePage if successful.
	 * 
	 * @param user
	 * @return
	 */	
	@Inject
	private UserInterface service;
	
	/**
	 * Method that is used to login the User and call services to verify credentials.
	 * 
	 * @param user: User
	 * @return view: String
	 */
	public String loginUser(User user)
	{	
		try	
		{
			// Call service to try to find the user by their input
			this.service.findBy(user);
		}
		// If User Does not Exist / Error finding User
		catch(UserNotFoundException e)
		{  
			this.error = "Username or Password is Incorrect.";
			this.redirect = "LoginPage.xhtml";
		}
		
		// Put the user to the faces context for the next view
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		// Redirect the user to the exception view.
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		 // return view
		return "HomePage.xhtml";
	}	
}
