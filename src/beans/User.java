package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	private int ID;
	
	@NotNull(message="Firstname is required")
	@Size(max=30, message="First Name cannot be longer than 30 characters.")
	private String firstName;
	
	@NotNull(message="Lastname is required")
	@Size(max=30, message="Last Name cannot be longer than 30 characters.")
	private String lastName;
	
	@NotNull(message="Username is required")
	@Size(min=2, max=30, message="Username must be between 2 and 30 characters.")
	private String username;
	
	@NotNull(message="Password is required")
	@Size(min=2, max=30, message="Password must be between 2 and 30 characters.")
	private String password;
	
	public User()
	{
		firstName = "";
		lastName = "";
		username = "";
		password = "";
	}
	
	public User(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String firstName, String lastName, int ID)
	{
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	@Override
	public String toString() {
		return "User [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	public static User getResultSet(ResultSet rs) throws SQLException
	{
		User user = new User(
				rs.getString("USERNAME"),
				rs.getString("PASSWORD"),
				rs.getString("FIRSTNAME"),
				rs.getString("LASTNAME"),
				rs.getInt("ID")
				);
		return user;
	}

}
