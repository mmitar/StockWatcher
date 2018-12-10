package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import beans.User;
import util.DatabaseException;
import util.InterceptorLogging;

/**
 * Contracted with the DataAccessInterface. Handles all DAO requests regarding User information.
 * @author Matt
 *
 */
@Stateless
@Local(DataAccessInterface.class)
@LocalBean
@Interceptors(InterceptorLogging.class)
public class UserDataService implements DataAccessInterface<User> {
	
	/**
	 * Connection params to declare a connection with swatcherdb
	 */	
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/swatcherdb";
	String username = "root";
	String password = "root";
	
	/**
	 * READ method
	 * A validation method to identify if the parameters match an existing user and returns the User if found.
	 * 
	 * @param user User
	 * @return User
	 * @throws DatabaseException
	 */	
	@Override
	public User findBy(User user) {
		
		try
		{
			//creating connection
			conn = DriverManager.getConnection(url, username, password);
			//SELECT sql statement to match user input
			String sql = String.format("SELECT * FROM `user` WHERE `USERNAME` = '%s' AND `PASSWORD` = '%s'", user.getUsername(),user.getPassword());
			//connecting SQL statement to db
			Statement stmt = conn.createStatement();
			//executing the result of the SQL statetment
			ResultSet rs = stmt.executeQuery(sql);
		
			//if user is found
			if(rs.next())
			{
				//return user if found
				user = User.getResultSet(rs);
			} 
			else 
			{
				//if no user is found return null
				user = null;
			}
			// close connection to prevent garbage build up
			rs.close();
			stmt.close();
		}
		// If there was a SQL or DB Connection Error. Throw DB Exception
		catch(SQLException e)        
    	{
        	e.printStackTrace();
        	throw new DatabaseException(e);
        }
		// Execute after try-catch block to cleanup the connection
        finally
        {
        	// If there was a connection instantiated in some form.
        	if(conn != null)
        	{	
        		try
        		{
        			// if connection was made now close it
        			conn.close();
        		}
        		// If there was an issue closing the Connection
        		catch(SQLException e)
        		{
        			e.printStackTrace();
        			throw new DatabaseException(e);
        		}       		
        	}       	
        }
		//return user if its null or has data
		return user;
    }	
	/**
	 * CREATE method
	 * Registers a new user based on the input params.
	 * 
	 * @param user User
	 * @return boolean
	 * @throws DatabaseException
	 */	
	@Override
	public boolean create(User user) 
	{
		boolean result = false;
		try
		{
			conn = DriverManager.getConnection(url, username,password);//creating connection
			//INSERT SQL statement
			String sql = String.format("INSERT INTO user (`FIRSTNAME`,`LASTNAME`, `USERNAME`, `PASSWORD`)VALUES('%s','%s','%s','%s')",user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword());
			//running statement to the connected database
			Statement stmt = conn.createStatement();
			//executing statement
			int rs = stmt.executeUpdate(sql);
			
			//if more than zero rows were updated then return true 
			if(rs > 0)
			{
				result = true;
			}	
			//close connection
			stmt.close();
		}
		// If there was a SQL or DB Connection Error. Throw DB Exception
		catch(SQLException e)        
    	{
        	e.printStackTrace();
        	throw new DatabaseException(e);
        }
		// Execute after try-catch block to cleanup the connection
        finally
        {
        	// If there was a connection instantiated in some form.
        	if(conn != null)
        	{	
        		try
        		{
        			// if connection was made now close it
        			conn.close();
        		}
        		// If there was an issue closing the Connection
        		catch(SQLException e)
        		{
        			e.printStackTrace();
        			throw new DatabaseException(e);
        		}       		
        	}       	
        }
		// return result if its false or true
		return result;
    }
		
	/**
	 * Inactive.
	 */
	@Override
	public List<User> findAll() {
		return null;
	}	
	
	/**
	 * Inactive.
	 */
	@Override
	public boolean update(User t) {
		return false;
	}
	
	/**
	 * Inactive.
	 */
	@Override
	public boolean delete(User t) {
		return false;
	}
	
	/**
	 * Inactive.
	 */
	@Override
	public User findById(int id) {
		return null;
	}
}
