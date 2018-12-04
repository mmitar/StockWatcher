package util;

import javax.ejb.Local;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ActionLogger
 */

@Local(ILogger.class)
@WebServlet("/ActionLogger")
public class ActionLogger extends HttpServlet implements ILogger {
	
	private static final Logger logger = Logger.getLogger(ActionLogger.class.getName());
	private static final long serialVersionUID = 1L;

	@Override
	public void debug(String message) {
		logger.debug("DEBUG : " + message);
	}

	@Override
	public void info(String message) {
		logger.info("INFO : " + message);
	}

	@Override
	public void warn(String message) {
		logger.warn("WARN : " + message);
	}

	@Override
	public void fatal(String message) {
		logger.fatal("FATAL : " + message);
	}
}
