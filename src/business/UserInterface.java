package business;

import beans.User;

public interface UserInterface {
	public User findBy(User user);
	public boolean create(User user);
}
