package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Stock;
import beans.User;
import business.StockInterface;

/**
 * Handles Stock model requests. Injects Stock service layer.
 * @author Matthew Mitar & Joey Alexander
 *
 */
@ManagedBean
@ViewScoped
public class StockController {
	
	/**
	 * @return StockService methods
	 */
	@EJB
	StockInterface service;
	
	/**
	 * Contracted with StockService to access functions
	 * 
	 * @return StockService methods
	 */
	public StockInterface getService() {
		return service;
	}
	
	/**
	 * Requests the StockService to consume Stock IOT
	 *  
	 * @return View
	 */
	public String onSubmit(String symbol)
	{	
		
		Stock stock = service.getStock(symbol);
		//Forwards the User ManagedBean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("stock", stock);
		
		if(stock !=null)
		{
			return "StockData.xhtml"; 
		}
		else
		{
			return "HomePage.xhtml"; // return view
		}
	
		
	}
	
}
