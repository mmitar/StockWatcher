package business;

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
@Alternative
public class UserService implements UserInterface  {
	
	@Inject
	DataAccessInterface<User> dao;
	
	public User findBy(User user)
	{ 
		// Step 1: Get and return the user that logged in
        user = dao.findBy(user);
        
        return user;
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

