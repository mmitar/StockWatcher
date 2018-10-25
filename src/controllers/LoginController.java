package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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
	/**
	 * Valides the User Model and navigates them to the homePage if successful.
	 * 
	 * @param user
	 * @return
	 */	
	@Inject
	UserInterface service;
	
	/**
	 * returning service of UserInterface which was  via @EJB
	 *  
	 * @return View
	 */
	
	// Login method
	public String loginUser(User user)
	{	
		try	
		{
			service.findBy(user);	
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
			return "HomePage.xhtml"; // return view
		}
		catch(UserNotFoundException e)
		{  
			String error = "Username or Password is Incorrect.";
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error", error);
			return "LoginPage.xhtml";
		}
	}	
}
