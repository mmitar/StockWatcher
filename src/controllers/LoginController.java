package controllers;

import javax.ejb.EJB;
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
public class LoginController {
	
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
	 * 
	 * @param user: User
	 * @return view: String
	 */
	public String loginUser(User user)
	{	
		try	
		{
			this.service.findBy(user);
		}
		// If User Does not Exist / Error finding User
		catch(UserNotFoundException e)
		{  
			this.error = "Username or Password is Incorrect.";
			this.redirect = "LoginPage.xhtml";
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		return "HomePage.xhtml"; // return view
	}	
}
