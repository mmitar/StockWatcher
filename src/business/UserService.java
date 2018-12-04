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
	
	@Inject
	DataAccessInterface<User> dao;
	
	
	@Override	
	public User findBy(User user) throws UserNotFoundException
	{ 
		// Step 1: Get and return the user that logged in
        user = dao.findBy(user);
          
        if(user != null)
        {
        	
        	return user;
        }
        else
        {
        	throw new UserNotFoundException();
        }
    }

	@Override
	public boolean create(User user) throws UserFoundException
	{	
        if(dao.findBy(user) == null)
        {
        	return dao.create(user);
        }
        else
        {
            throw new UserFoundException();
        }
	}
}

