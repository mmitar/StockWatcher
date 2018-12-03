package util;

import java.io.IOException;

import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ActionLogger
 */

@Local(ILogger.class)
@WebServlet("/ActionLogger")
public class ActionLogger extends HttpServlet implements ILogger {
	
	private static final Logger logger = Logger.getLogger(ActionLogger.class.getName());
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("logging DEBUG Message");
		logger.info("logging INFO message");
		logger.warn("logging ERROR message");
		logger.fatal("logging FATAL ERROR message");
	}

	@Override
	public void debug(String message) {
		logger.debug(message);
	}

	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void warn(String message) {
		logger.warn(message);
	}

	@Override
	public void fatal(String message) {
		logger.fatal(message);
	}
}
