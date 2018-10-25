package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.User;
import data.DataAccessInterface;
import util.UserErrorException;
import util.UserFoundException;
import util.UserNotFoundException;

@Stateless
@Local(UserInterface.class)
@LocalBean
public class UserService implements UserInterface {
	
	@EJB
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
		user = dao.findBy(user);
		System.out.println(user);
		
        if(dao.findBy(user) == null)
             throw new UserFoundException();
       
        if(dao.create(user) == false)
        	throw new UserErrorException();
        
        return true;
	}
}

