package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Register users by USER properties. Verify login by username/password.
 * @author Matthew & Joey
 *
 */
@ManagedBean
@ViewScoped
public class User {
	
	@NotNull(message="Firstname is required")
	@Size(max=30, message="First Name cannot be longer than 30 characters.")
	String firstName;
	
	@NotNull(message="Lastname is required")
	@Size(max=30, message="Last Name cannot be longer than 30 characters.")
	String lastName;
	
	@NotNull(message="Username is required")
	@Size(min=2, max=30, message="Username must be between 2 and 30 characters.")
	String username;
	
	@NotNull(message="Password is required")
	@Size(min=2, max=30, message="Password must be between 2 and 30 characters.")
	String password;
	
	public User()
	{
		firstName = "";
		lastName = "";
		username = "";
		password = "";
	}
	
	public User(String firstName, String lastName, String username, String password)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
