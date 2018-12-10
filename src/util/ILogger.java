package util;

/**
 * Interface that contracts methods for CDI
 */
public interface ILogger 
{
	/**
	 * Debug Level for diagnostic information helping development
	 * @param message String
	 */
	public void debug(String message);
	
	/**
	 * Info Level for general information on system invocations
	 * @param message String
	 */
	public void info(String message);
	
	/**
	 * Warn Level for application anomalies but the system is able to recover from.
	 * @param message String
	 */
	public void warn(String message);
	
	/**
	 * Fatal Level for the application forcing shutdown or data corruption.
	 * @param message String
	 */
	public void fatal(String message);
}
