package controllers;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;

import beans.Stock;
import business.StockInterface;
import util.InterceptorLogging;
import util.StockNotFoundException;

/**
 * Handles Stock model requests. Injects Stock service layer.
 * @author Matthew Mitar & Joey Alexander
 *
 */
@Named
@ViewScoped
@Interceptors(InterceptorLogging.class)
public class StockController implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String redirect = null;
	private String error = null;
	
	/**
	 * @return StockService methods
	 */
	@EJB
	private StockInterface service;
	
	/**
	 * Requests the StockService to consume Stock IOT
	 *  
	 * @return View
	 */
	public String getStock()
	{	
		// Get the Form Post
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String symbol = request.getParameter("symbolForm:symbol");
		
        // creating a null instance of stock
        Stock stock = null;
        
        try 
        {
        	//if a stock is found 
			stock = this.service.getStock(symbol);
        }
     // catch if no stock has  been found and redirect them back to the same page
        catch(StockNotFoundException e)
        {
        	this.error = "No stock data found using: "+symbol;
			this.redirect = "HomePage.xhtml"; // return view
		}
        
		//Forwards the User ManagedBean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("stock", stock);
		
		// Redirect the user to the targeted view.
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		return "StockData.xhtml"; 
		
	}	
}
