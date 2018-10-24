package controllers;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.User;
import business.UserInterface;

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
	
		public String onSubmit(User user)
	{	
		user = service.findBy(user);		
		
		//Forwards the User ManagedBean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		
		if(user == null)
		{
			return "RegistrationPage.xhtml";
		}
		
		return "RegistrationSuccess.xhtml"; // return view
	}
}
