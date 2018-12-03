package util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorLogging implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	ILogger logger;
	
	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx) throws Exception
	{
		logger.info("******************* Intercepting call to method via AroundInvoke: " + 
				ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()");
		
		return ctx.proceed();
	}

}
