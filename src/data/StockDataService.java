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

import beans.Stock;
import util.DatabaseException;
import util.InterceptorLogging;

/**
 * Contracted with StockDataInterface. Hanldes all DAO requests regarding stock.
 * @author Matthew & Joey
 *
 */
@Stateless
@Local(StockDataInterface.class)
@LocalBean
@Interceptors(InterceptorLogging.class)
public class StockDataService implements StockDataInterface<Stock> {

	/**
	 * Connection params to declare a connection with swatcherdb
	 */	
	private static Connection conn = null;
	private final String url = "jdbc:mysql://localhost:3306/swatcherdb";
	private final String username = "root";
	private final String password = "root";
	
	/**
	 * READ method
	 * extract the most recent stock data relative to the symbol input by the user. ex: "AAPL". 
	 * 
	 * @param String symbol
	 * @return Stock
	 * @throws DatabaseException
	 */	
	@Override
	public Stock findBy(String symbol) {
		
		Stock stock = null;
		
		try {
			// creating connection
			conn = DriverManager.getConnection(url, username, password);
			//SELECT FROM SQL statement
			String sql = String.format("SELECT * FROM `stock` WHERE `SYMBOL` = '%s' LIMIT 1", symbol);
			//creating connection with statement
			Statement stmt = conn.createStatement();
			//execeute statement
			ResultSet rs = stmt.executeQuery(sql);
			
			// if at least one row was selceted then return stock
			if(rs.next())
			{
				stock = Stock.getOneResultSet(rs); // Stock Found
			}
			//else return stock as null
			else 
			{
				stock = null; // No stock was found
			}
			
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
		//returning stock if null or with data
		return stock;
	}
	
	/**
	 * CREATE method
	 * Constantly collecting more stock data and it is put in database through this method
	 * 
	 * @param Stock stock
	 * @return boolean
	 * @throws DatabaseException
	 */	
	@Override
	public boolean create(Stock stock) 
	{
		boolean result = false;
		
		try {
			//conection made
			conn = DriverManager.getConnection(url, username, password);
			//INSERT sql statement
			String sql = "INSERT INTO `stock` ("+Stock.getParamSet()+") VALUES ("+Stock.getValueSet(stock)+")";
			//create connection for statement
			Statement stmt = conn.createStatement();
			//execute INSERT statement via update
			int rs = stmt.executeUpdate(sql);
			
			//if more than one row has been updated then return true
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
		//return reuslt if null or with data
		return result;
	}

	/**
	 * UPDATE method
	 * Updates an existing Stock set. Not currently implemented because we want new instances.
	 * 
	 * @param Stock stock
	 * @return boolean
	 * @throws DatabaseException
	 */
	@Override
	public boolean update(Stock stock) {
		
		boolean result = false;
		
		try {
			//Create a connection
			conn = DriverManager.getConnection(url, username, password);
			// UDPATE query that updates an existing Stock data aset
			String sql = "UPDATE `stock` SET "+Stock.getUpdateSetBySymbol(stock);
			// execute the statement
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			
			// verify the changes were made.
			if(rs > 0) 
			{
				result = true; 
			}
			
			// Close the connection
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
		// Return the results from the update
		return result;
	}

	/**
	 * Inactive.
	 */
	@Override
	public boolean delete(Stock t) {
		return false;
	}

	/**
	 * Inactive.
	 */
	@Override
	public Stock findBy(Stock t) {
		return null;
	}
	
	/**
	 * Inactive.
	 */
	@Override
	public List<Stock> findAll() {
		return null;
	}

	/**
	 * Inactive.
	 */
	@Override
	public Stock findById(int id) {
		return null;
	}
}
