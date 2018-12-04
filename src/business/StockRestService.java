package business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Response;
import beans.ResponseFactory;
import beans.Stock;
import util.InterceptorLogging;
import util.PostException;

@Stateless
@Path("/stocks")
@Interceptors(InterceptorLogging.class)
public class StockRestService {
	
	@Inject
	StockInterface service;
	
	@POST
	@Path("/previous")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveStock_AAPL(Stock stock) {
		
		// Assembles Responses Based on Status + Parameter
		ResponseFactory factory = new ResponseFactory();
		
		try 
		{
			Response dto = factory.getResponse200(service.saveStock(stock));
			
			return dto;
		}
		// When POST failed and stock was not saved.
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
