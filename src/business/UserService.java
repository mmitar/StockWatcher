package business;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import beans.User;
import data.DataAccessInterface;
import util.UserErrorException;
import util.UserFoundException;
import util.UserNotFoundException;

@Stateless
@Local(UserInterface.class)
@LocalBean
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
	public boolean create(User user) throws UserFoundException, UserErrorException
	{	
        if(dao.findBy(user) != null)
        {
             throw new UserFoundException();
        }
       
        if(dao.create(user))
        {
            return true;
        }
        else 
        {
            throw new UserErrorException();
        }
        
	}
}

