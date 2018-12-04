package controllers;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Used to implement a child CustomExceptionHandler class
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory
{
	private ExceptionHandlerFactory parent;
	 
	 public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) 
	 {
		 this.parent = parent;
	 }

	@Override
	public ExceptionHandler getExceptionHandler()
	{
		return new CustomExceptionHandler(this.parent.getExceptionHandler());
	}
}
