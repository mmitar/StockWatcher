package business;

import javax.ejb.Local;
import beans.User;

public interface UserInterface {
	public User findBy(User user);
	public boolean create(User user);
}
