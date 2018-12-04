package beans;

import util.DatabaseException;
import util.PostException;
import util.StockNotFoundException;

/**
 * Delegates the process of assembling an accurate response based on the dataset for REST services.
 * If dataset is instanceof boolean, a POST method was invoked.
 * If dataset is instanceof Stock, a GET method was invoked.
 * if dataset is instanceof Exception, an error or business logic failure was encountered.
 */
public class ResponseFactory 
{
	/**
	 * Status Code 200 for successful transactions. 
	 * 
	 * @param dataset Object
	 * @return Response
	 */
	public Response getResponse200(Object dataset)
	{
		// Default Reponse params
		int status = 200;
		String message = "Request Executed: ";
		Stock data = null;
		
		// If null, a GET was FAILED. This issue should be caught in Business Layer and reported as a 400.
		if(dataset == null)
		{
			status = 204;
			message += "Data set is Null.";
		}
		// If dataset is Stock. It was a GET Method
		else if(dataset instanceof Stock)
		{
			status = 202;
			message += "Data set successfully retrieved.";
			
			data = (Stock) dataset;
		}
		// Boolean datasets are for POST methods
		else if(dataset instanceof Boolean)
		{
			// If true the POST was successfuly
			if(((Boolean) dataset).booleanValue() == true)
			{
				status = 201;
				message += "Content Successfully Posted.";
			}
			// If false the POST failed. This should be be caught in Business Layer and reported as 400.
			else
			{
				status = 204;
				message += "There was an error successfully processing your request.";
			}
		}
		// Generic Status reponse if dataset is unrecognized. Mostly for troubleshooting.
		else
		{
			status = 200;
			message += "Successful.";
		}
		
		// return assembled ResponseDataModel
		return new ResponseDataModel(status, message, data);
	}

	/**
	 * Status code 400 for client errors
	 * 
	 * @param exception Object
	 * @return Response
	 */
	public Response getResponse400(Object exception)
	{
		// Default Response params
		int status = 400;
		String message = "Bad Request: ";
		
		// if GET failed and Stock does not exist.
		if(exception instanceof StockNotFoundException)
		{
			status = 404;
			message += "Stock Does Not Exist.";
		}
		// If POST failed and did not get processed successfully.
		else if(exception instanceof PostException)
		{
			status = 406;
			message += "Could not be successfully processed.";
		}
		// Generic response if Exception isn't recognized. Shouldn't be reached.
		else
		{
			message += "Try again later.";
		}
		
		// Return assembled ReponseModel
		return new ResponseModel(status, message);
	}
	
	/**
	 * Status code 500 for internal system errors
	 * 
	 * @param exception Object
	 * @return Response
	 */
	public Response getResponse500(Object exception)
	{
		int status = 500;
		String message = "Internal System Explosion: ";
		
		// If there was an exception thrown from the database
		if(exception instanceof DatabaseException)
		{
			message += "Database server is currently unavailable.";
		}
		// Generic response message for the whole system crashing, unknown origin.
		else if(exception instanceof Exception)
		{
			message += "Yikes.. Try again later.";
		}
		// Should be unreachedable.
		else
		{
			message += "Call 911.";
		}
		
		// return assembled ResponseModel
		return new ResponseModel(status, message);
	}
}
