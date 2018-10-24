package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.User;
import data.DataAccessInterface;

@Stateless
@Local(UserInterface.class)
@LocalBean
public class UserService implements UserInterface  {
	
	@Inject
	DataAccessInterface<User> dao;
	
	public User findBy(User user)
	{ 
		// Step 1: Get and return the user that logged in
        user = dao.findBy(user);
        if(user!=null)
        {
        	System.out.println(user + " USER NOT NULL FROM USER SERVICE");
        	return user;
        }
        
        return null;
    }

	public boolean create(User user)
	{	
		if(dao.create(user) == true)
		{
			return true;
		}
		
		return false;
		
	}
	
}

