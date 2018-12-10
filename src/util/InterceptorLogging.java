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
		
		// Continue the system's method call
		Object result = ctx.proceed();
		
		logger.info("Exiting " + ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "();");
		
		return result;
	}
}
