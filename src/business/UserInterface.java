package business;

import beans.User;
import util.AccountErrorException;
import util.AccountFoundException;
import util.AccountNotFoundException;

public interface UserInterface {
	
	public User findBy(User user) throws AccountNotFoundException;
	public boolean create(User user) throws AccountFoundException, AccountErrorException;
}
