package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;
import business.UserInterface;
import util.UserNotFoundException;

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
	 * Valides the User Model and navigates them to the homePage if successful.
	 * 
	 * @param user
	 * @return
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
			this.service.findBy(user);
		}
		catch(Exception e)
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
