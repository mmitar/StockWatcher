package business;

import javax.ejb.Local;
import beans.User;

@Local
public interface UserInterface {
	public User findBy(User user);
	public boolean create(User user);
}
