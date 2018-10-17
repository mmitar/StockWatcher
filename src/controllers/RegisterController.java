package controllers;

import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import beans.User;

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
	public String onSubmit(User user)
	{	
		//Forwards the User ManagedBean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		return "RegistrationSuccess.xhtml"; //return view
	}
}
