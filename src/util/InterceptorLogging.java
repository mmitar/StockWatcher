package util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Intercepts System invocations and logs the method called or exceptions thrown.
 */
public class InterceptorLogging implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Dependency Injected the Logger Interface
	 * @return Action Logger
	 */
	@Inject
	private ILogger logger;
	
	/**
	 * Intercepts System invocations and logs the method called or exceptions thrown.
	 * 
	 * @param ctx InvocationContext
	 * @return Object : method || null
	 * @throws Exception
	 */
	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx) throws Exception
	{
		// Info level log that declares the invoked method
		logger.info("Invoked " + ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "();");
		
		// Try to proceed the invoked method
		try
		{
			// Continue the system's method call
			return ctx.proceed();
		} 
		// If a database exception was thrown continue here
		catch(DatabaseException e)
		{
			// Log the exception that was thrown.
			logger.warn("Database Thrown: " + e.getClass() + " ## Reconnect!! ");
			
			// Ignore trying to redirect and let the system continue exception procedure.
			return null;
		}
		// If an Exception was intercepted, continue here
		catch(Exception e)
		{
			// Log the exception that was thrown.
			logger.warn("Exception Thrown: " + e.getClass() + ";");
			
			// Proceed the systems lifecyles to handle the checked Exception being thrown.
			return ctx.proceed();
		}
	}
}
