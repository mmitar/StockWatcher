package business;

import beans.User;
import util.UserErrorException;
import util.UserFoundException;
import util.UserNotFoundException;

public interface UserInterface {
	
	public User findBy(User user) throws UserNotFoundException;
	public boolean create(User user) throws UserFoundException, UserErrorException;
}
