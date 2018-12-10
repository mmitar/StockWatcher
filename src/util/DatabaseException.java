package util;

import javax.ejb.ApplicationException;

/**
 * Enforces Data Logic.
 * Useful for errors regarding Database issues.
 */
@ApplicationException(rollback=true)
public class DatabaseException extends RuntimeException
{
	private static final long serialVersionUID = 0L;
	
	public DatabaseException(Throwable e)
	{
		super(e);
	}
}
