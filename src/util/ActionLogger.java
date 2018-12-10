package util;

import javax.ejb.Local;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * Log4j implementation.
 * Servlet implementation class ActionLogger that will Log actions implemented by the server.
 */
@Local(ILogger.class)
@WebServlet("/ActionLogger")
public class ActionLogger extends HttpServlet implements ILogger {
	
	/**
	 * Log4j Logger Declaration.
	 */
	private static final Logger logger = Logger.getLogger(ActionLogger.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * Debug Level for diagnostic information helping development
	 * @param message String
	 */
	@Override
	public void debug(String message) {
		logger.debug("DEBUG : " + message);
	}

	/**
	 * Info Level for general information on system invocations
	 * @param message String
	 */
	@Override
	public void info(String message) {
		logger.info("INFO : " + message);
	}

	/**
	 * Warn Level for application anomalies but the system is able to recover from.
	 * @param message String
	 */
	@Override
	public void warn(String message) {
		logger.warn("WARN : " + message);
	}

	/**
	 * Fatal Level for the application forcing shutdown or data corruption.
	 * @param message String
	 */
	@Override
	public void fatal(String message) {
		logger.fatal("FATAL : " + message);
	}
}
