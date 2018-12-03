package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.ResponseDataModel;
import beans.Stock;
import util.StockNotFoundException;

@Stateless
@Path("/stocks")
public class StockRestService {
	
	@Inject
	StockInterface service;
	
	@POST
	@Path("/previous")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseDataModel saveStock_AAPL(Stock stock) {
		
		try {
			service.saveStock(stock);
			
			ResponseDataModel dto = new ResponseDataModel(201,"Request Successfully Executed. Stock saved.", null);
			return dto;
		}
		// When Stock cannot be Saved
		catch(StockNotFoundException e)
		{
			ResponseDataModel dto = new ResponseDataModel(401,"We are unable to handle the request. Try again later.", null);
			return dto;
		} 
		catch(Exception e)
		{
			ResponseDataModel dto = new ResponseDataModel(501,"Internal System Explosion. Try again later.", null);
			return dto;
		}
	}
}
