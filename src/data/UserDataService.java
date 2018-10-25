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

import beans.User;
import util.DatabaseException;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class UserDataService implements DataAccessInterface<User> {
	
	/**
	 * Creating connection to mySQL db 'swatcherdb'
	 * also using my username and password 
	 * 
	 * 
	 */	
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/swatcherdb";
	String username = "root";
	String password = "root";
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}	
	/**
	 * Login method to find selected user
	 * 
	 * @param user
	 * @return User
	 */	
	@Override
	public User findBy(User user) {
		
		try
		{
			conn = DriverManager.getConnection(url, username, password);//creating connection
			//SELECT sql statement to match user input
			String sql = String.format("SELECT * FROM `user` WHERE `USERNAME` = '%s' AND `PASSWORD` = '%s'", user.getUsername(),user.getPassword());
			//connecting SQL statement to db
			Statement stmt = conn.createStatement();
			//executing the result of the SQL statetment
			ResultSet rs = stmt.executeQuery(sql);
		
			//if user is found
			if(rs.next())
			{
				user = User.getResultSet(rs);//return user if found
			} 
			else 
			{
				
				user = null;//if no user is found return null
			}
			//close connection to prevent garabe build up
			rs.close();
			stmt.close();
		}
		catch(SQLException e)        
    	{
			//if database connection failed or doesnt make connection period
        	e.printStackTrace();
        	throw new DatabaseException(e);
        }
        finally
        {
        	if(conn != null)
        	{	
        		try
        		{
        			conn.close();
        		}
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
	 * DAO method to register new user and enter user data in database
	 * 
	 * @param user
	 * @return Boolean
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
		catch(SQLException e)        
    	{
			//DB exception for failed database connection
        	e.printStackTrace();
        	throw new DatabaseException(e);
        }
        finally
        {
        	if(conn != null)
        	{	
        		try
        		{
        			//if connection was made now close it
        			conn.close();
        		}
        		catch(SQLException e)
        		{
        			e.printStackTrace();
        			throw new DatabaseException(e);
        		}       		
        	}       	
        }
		//return result if its false or true
		return result;
    }
		
	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User findBy(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
