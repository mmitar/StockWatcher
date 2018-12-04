package util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorLogging implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ILogger logger;
	
	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx) throws Exception
	{
		logger.info("Invoked " + ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "();");
		
		try
		{
			return ctx.proceed();
		} 
		catch(Exception e)
		{
			logger.warn("Exception Thrown: " + e.getClass() + " = FOR = " + e.getMessage());
			return null;
		}
	}
}
