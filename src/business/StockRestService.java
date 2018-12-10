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
	
	/**
	 * @return StockService methods
	 */
	@Inject
	StockInterface service;
	
	/**
	 * POST method
	 * Client sends AAPL Stock and we return it 
	 * 
	 * @param stock Stock
	 * @return Response DTO
	 */
	@POST
	@Path("/previous")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveStock_AAPL(Stock stock) {
		
		// Assembles Responses Based on Status + Parameter
		ResponseFactory factory = new ResponseFactory();
		
		try 
		{
			// Call StockService.saveStock to try and retain the new AAPL Stock info
			boolean result = service.saveStock(stock);
			
			// Call and return the factory to assemble a dynamic code 200 response
			return factory.getResponse200(result);
		}
		// When POST failed and stock was not saved.
		catch(PostException e)
		{
			// Call and return the factory to assemble a responsive code 400 response
			return factory.getResponse400(e);
		} 
		// Catching DB exceptions
		catch(Exception e)
		{
			// Call and return the factory to assemble a dynamic code 500 response
			return factory.getResponse500(e);
		}
	}
}
