package business;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import beans.User;
import data.DataAccessInterface;
import util.AccountErrorException;
import util.AccountFoundException;
import util.AccountNotFoundException;

@Stateless
@Local(UserInterface.class)
@LocalBean
public class UserService implements UserInterface {
	
	@Inject
	DataAccessInterface<User> dao;
	
	@Override
	public User findBy(User user) throws AccountNotFoundException
	{ 
		// Step 1: Get and return the user that logged in
        user = dao.findBy(user);
        
        if(user != null)
        {
        	return user;
        }
        else
        {
        	throw new AccountNotFoundException();
        }
    }

	@Override
	public boolean create(User user) throws AccountFoundException, AccountErrorException
	{	
        if(dao.findBy(user) != null)
        {
             throw new AccountFoundException();
        }
       
        if(dao.create(user))
        {
            return true;
        }
        else 
        {
            throw new AccountErrorException();
        }
        
	}
}

