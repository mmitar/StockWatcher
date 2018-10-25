package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.User;
import data.DataAccessInterface;
import util.UserFoundException;
import util.UserNotFoundException;

@Stateless
@Local(UserInterface.class)
@LocalBean
public class UserService implements UserInterface {
	
	/**
	 * @injects UserDataService
	 */
	@EJB
	DataAccessInterface<User> dao;
	
	/**
	 * Calls dao to find the user. if not found throw custom exception
	 * 
	 * @throws UserNotFoundException
	 * @param user User
	 * @return user User
	 */
	@Override
	public User findBy(User user) throws UserNotFoundException
	{ 
		// Step 1: Get and return the user that logged in
        user = dao.findBy(user);
        
        // If user is not Null return user
        if(user != null)
        {
        	return user;
        }
        // throw exception is user isn't found
        else
        {
        	throw new UserNotFoundException();
        }
    }

	/**
	 * Calls dao to find user before creating. If found throw Custom Exception
	 * If not found, create user
	 * 
	 * @throws UserFoundException
	 * @param user User
	 * @return result Boolean
	 */
	@Override
	public boolean create(User user) throws UserFoundException
	{	
		// Find inputed User. If not Found create new User
        if(dao.findBy(user) == null)
        {
        	System.out.println("Happy traitsl");
        	return dao.create(user);
        }
        // If found throw UserFoundException
        else
        {
        	System.out.println("throw this hsit");
            throw new UserFoundException();
        }
	}
}

