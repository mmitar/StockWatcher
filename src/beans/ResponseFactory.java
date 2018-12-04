package beans;

import util.PostException;
import util.StockNotFoundException;

public class ResponseFactory 
{
	/**
	 * Status Code: 201. Posted objected was created.
	 * @return Response
	 */
	public Response getResponse200(Object dataset)
	{
		int status = 201;
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
		
		return new ResponseDataModel(status, message, data);
	}

	public Response getResponse400(Object e)
	{
		int status = 400;
		String message = "Bad Request: ";
		
		// if GET failed and Stock does not exist.
		if(e instanceof StockNotFoundException)
		{
			status = 404;
			message += "Stock Does Not Exist.";
		}
		// If POST failed and did not get processed successfully.
		else if(e instanceof PostException)
		{
			status = 406;
			message += "Could not be successfully processed.";
		}
		// Generic response if Exception isn't recognized. Shouldn't be reached.
		else
		{
			message += "Try again later.";
		}
		
		return new ResponseModel(status, message);
	}
	
	public Response getResponse500(Object e)
	{
		int status = 500;
		String message = "Internal System Explosion: ";
		
		if(e instanceof Exception)
		{
			message += "Yikes.. Try again later.";
		}
		
		return new ResponseModel(status, message);
	}
}
