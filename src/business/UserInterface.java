package business;

import beans.User;
import util.UserFoundException;
import util.UserNotFoundException;

/**
 * Implemented from the UserService
 * @author Matt & Joey
 *
 */
public interface UserInterface {
	
	/**
	 * Calls dao to find the user. if not found throw custom exception
	 * 
	 * @throws UserNotFoundException
	 * @param user User
	 * @return user User
	 */
	public User findBy(User user) throws UserNotFoundException;
	
	/**
	 * Calls dao to find user before creating. If found throw Custom Exception
	 * If not found, create user
	 * 
	 * @throws UserFoundException
	 * @param user User
	 * @return result Boolean
	 */
	public boolean create(User user) throws UserFoundException;
}
