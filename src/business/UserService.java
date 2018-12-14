package business;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import beans.User;
import data.DataAccessInterface;
import util.InterceptorLogging;
import util.UserFoundException;
import util.UserNotFoundException;

@Stateless
@Local(UserInterface.class)
@LocalBean
@Interceptors(InterceptorLogging.class)
public class UserService implements UserInterface {
	
	/**
	 * @return UserDataService methods
	 */
	@Inject
	DataAccessInterface<User> dao;
	
	/**
	 * Function that enforces a validated user 
	 * 
	 * @param user User
	 * @throws UserNotFoundException
	 * @return User
	 */
	@Override
	public User findBy(User user) throws UserNotFoundException
	{ 
		// Get and return the user that logged in calling DAO
        user = dao.findBy(user);
          
        // check if the user is not null 
        if(user != null)
        {
        	// return validated User
        	return user;
        }
        // If user is null throw exception
        else
        {
        	throw new UserNotFoundException();
        }
    }

	/**
	 * Function that enforces that a User was successfully created
	 * 
	 * @param user User
	 * @throws UserFoundException
	 * @return boolean
	 */
	@Override
	public boolean create(User user) throws UserFoundException
	{	
		// check if the user already exists in the DB
        if(dao.findIfExists(user) == false)
        {
        	// If the user does not already exist, create a new user
        	return dao.create(user);
        }
        // If user exists throw exception
        else
        {
            throw new UserFoundException();
        }
	}

	@Override
	public boolean findIfExists(User user) throws UserFoundException {
		// TODO Auto-generated method stub
		return false;
	}
}

