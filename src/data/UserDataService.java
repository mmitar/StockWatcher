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
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/swatcherdb";
	String username = "root";
	String password = "root";
	
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}	
	@Override
	public User findBy(User user) {
		
		try
		{
			conn = DriverManager.getConnection(url, username, password);
			String sql = String.format("SELECT * FROM `user` WHERE `USERNAME` = '%s' AND `PASSWORD` = '%s'", user.getUsername(),user.getPassword());
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
			if(rs.next())
			{
				user = User.getResultSet(rs);
				System.out.println("------------------>" + user);
			} 
			else 
			{
				user = null;
			}
			
			rs.close();
			stmt.close();
		}
		catch(SQLException e)        
    	{
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
		System.out.print(user + "NO USER????");
		return user;
    }	
	
	@Override
	public boolean create(User user) 
	{
		boolean result = false;
		try
		{
			conn = DriverManager.getConnection(url, username,password);
			String sql = String.format("INSERT INTO user (`FIRSTNAME`,`LASTNAME`, `USERNAME`, `PASSWORD`)VALUES('%s','%s','%s','%s')",user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword());
			
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			
			if(rs > 0)
			{
				result = true;
			}	
			
			stmt.close();
		
		}catch(SQLException e)        
    	{
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
