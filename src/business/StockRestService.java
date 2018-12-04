package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Response;
import beans.ResponseDataModel;
import beans.ResponseFactory;
import beans.Stock;
import util.PostException;
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
	public Response saveStock_AAPL(Stock stock) {
		
		ResponseFactory factory = new ResponseFactory();
		
		try 
		{
			Response dto = factory.getResponse200(service.saveStock(stock));
			
			return dto;
		}
		// When Stock cannot be Saved.
		catch(PostException e)
		{
			return factory.getResponse400(e);
		} 
		// Catching DB exceptions
		catch(Exception e)
		{
			return factory.getResponse500(e);
		}
	}
}
