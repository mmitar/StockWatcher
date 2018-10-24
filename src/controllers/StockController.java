package controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Stock;
import business.StockInterface;
import data.StockDataService;

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
	public String getStockIOT()
	{
		System.out.println("StockController.getIOT(): Invoked.");
		
		Stock stock = service.getPrevious();
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("stock",stock);
		
		System.out.println("StockController.getIOT(): Returning View.");
		return "HomePage.xhtml"; //return view
	}
	
}
