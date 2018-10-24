package business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import beans.Stock;

@Stateless
@Path("/stocks")
public class StockRestService {
	
	@EJB
	StockInterface service;
	
	@POST
	@Path("/previous")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getIEX_Previous(Stock stock) {
		
		boolean result = service.updateIEX_Previous(stock);
		
		int status;
		String message;
		
		if(result == false) {
			status = 404;
			message = "Could not retain IEX APPL PREVIOUS";
		} else {
			status = 201;
			message = "IEX APPL PREVIOUS saved to database.";
		}
		
		return Response.status(status).entity(message).build();
	}
}
