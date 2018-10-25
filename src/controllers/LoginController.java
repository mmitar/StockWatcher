package controllers;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import beans.User;
import business.UserInterface;
import util.LoginFailedException;
import util.RegistrationFailedException;

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
			user = service.findBy(user);	
			if(user == null)
			{
				System.out.println(user + "NULL_____ FROM LOGIN CONTROLLER");
				return "LoginPage.xhtml";
			}
		}catch(LoginFailedException e)
		{  
			System.out.println("======================== Login Failed Excpetion");
		}
		return "HomePage.xhtml"; // return view
	}	
}
