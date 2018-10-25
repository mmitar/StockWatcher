package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;
import business.UserInterface;

/**
 * Validates login modules within the app. No DB is currently implemented.
 * @author Matthew & Joey
 *
 */
@ManagedBean
@ViewScoped
public class LoginController {
	
	private String redirect = null;
	private String error = null;
	
	/**
	 * @injects UserService methods
	 */
	@EJB
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
			// Call UserService.findBy the User input
			this.service.findBy(user);
		}
		// If User Does not Exist / Error finding User
		catch(Exception e)
		{  
			this.error = "Username or Password is Incorrect.";
			this.redirect = "LoginPage.xhtml";
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		// Redirects user if error occured.
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		return "HomePage.xhtml"; // return view
	}	
}
