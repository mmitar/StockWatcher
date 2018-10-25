package controllers;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;
import business.UserInterface;
import util.AccountFoundException;
import util.AccountNotFoundException;

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
	@EJB
	UserInterface service;
	
	/**
	 * returning service of UserInterface which was  via @EJB
	 *  
	 * @return View
	 */
	
	public UserInterface getService() {
		return service;
	}
	
	//login method
	public String onSubmit(User user)
	{	
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		try	
		{
			service.findBy(user);	
			
			return "LoginPage.xhtml";
		}
		catch(AccountNotFoundException e)
		{  
			return "HomePage.xhtml"; // return view
		}
	}	
}
