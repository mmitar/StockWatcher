package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import beans.Stock;
import business.StockInterface;
import util.StockNotFoundException;

/**
 * Handles Stock model requests. Injects Stock service layer.
 * @author Matthew Mitar & Joey Alexander
 *
 */
@ManagedBean
@ViewScoped
public class StockController {
	
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
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String symbol = request.getParameter("symbolForm:symbol");
		
        Stock stock= null;
        
        try 
        {
			stock = this.service.getStock(symbol);
        }
        catch(Exception e)
        {
        	this.error = "No stock data found using: "+symbol;
			this.redirect = "HomePage.xhtml"; // return view
		}
        
		//Forwards the User ManagedBean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("stock", stock);
		
		if(this.redirect != null) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("error",this.error);
			return this.redirect;
		}
		
		return "StockData.xhtml"; 
		
	}	
}
