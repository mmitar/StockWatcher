package business;

import beans.User;
import util.UserFoundException;
import util.UserNotFoundException;

/**
 * Contracts functions using for CDI
 */
public interface UserInterface {
	
	/**
	 * Function that enforces a validated user 
	 * 
	 * @param user User
	 * @throws UserNotFoundException
	 * @return User
	 */
	public User findBy(User user) throws UserNotFoundException;
	
	/**
	 * Function that enforces that a User was successfully created
	 * 
	 * @param user User
	 * @throws UserFoundException
	 * @return boolean
	 */
	public boolean create(User user) throws UserFoundException;
}
