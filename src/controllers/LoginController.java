package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import beans.User;

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
	public String onSubmit(User user)
	{	
		//Forwards the User ManagedBean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user",user);
		return "HomePage.xhtml"; // return view
	}
}
