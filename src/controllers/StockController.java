package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
	
	/**
	 * @return StockService methods
	 */
	@Inject
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
	public String getStock()
	{	
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String symbol = request.getParameter("symbolForm:symbol");
		
        try {
		
			Stock stock = service.getStock(symbol);
			
			//Forwards the User ManagedBean
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("stock", stock);
		
			return "StockData.xhtml"; 
        }
        catch(StockNotFoundException e)
        {
			return "HomePage.xhtml"; // return view
		}
	}	
}
