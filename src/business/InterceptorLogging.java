package business;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorLogging {
	
	
	private static InterceptorLogging SINGLE_INSTANCE = null;
    
	private InterceptorLogging() {}
   
	public static InterceptorLogging getInstance() {
        if (SINGLE_INSTANCE == null) {
            synchronized (InterceptorLogging.class) {
                if (SINGLE_INSTANCE == null) {
                    SINGLE_INSTANCE = new InterceptorLogging();
                }
            }
        }
        return SINGLE_INSTANCE;
    }
	
	@AroundInvoke
	public Object methodInterceptor(InvocationContext ctx) throws Exception
	{
	    	System.out.println("******************* Intercepting call to method via AroundInvoke: " + 
	    				ctx.getTarget().getClass() + "." + ctx.getMethod().getName() + "()");
			return ctx.proceed();
	}

}
