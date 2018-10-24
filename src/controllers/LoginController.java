package controllers;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import business.StockInterface;
import business.UserInterface;
import business.UserService;

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
	
	public UserInterface getService() {
		return service;
	}
	//login method
	public String onSubmit(User user)
	{	
		user = service.findBy(user);
		
		//Forwards the User ManagedBean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		
		if(user == null)
		{
			return "LoginPage.xhtml";
		}
		
		return "HomePage.xhtml"; // return view
	}
	
}
