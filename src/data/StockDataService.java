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

import beans.Stock;
import util.DatabaseException;

/**
 * Contracted with StockDataInterface. Calls the IOT to collect API data
 * @author Matthew & Joey
 *
 */
@Stateless
@Local(StockDataInterface.class)
@LocalBean
public class StockDataService implements StockDataInterface<Stock> {

	private static Connection conn = null;
	private final String url = "jdbc:mysql://localhost:3306/swatcherdb";
	private final String username = "root";
	private final String password = "root";
	
	@Override
	public List<Stock> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Finding stock 
	 * 
	 * @param String
	 * @return Stock
	 */	

	@Override
	public Stock findBy(String symbol) {
		
		Stock stock = null;
		try {
			conn = DriverManager.getConnection(url, username, password);// creating connection
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
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException(e);
		}finally {
			{
				//Cleanup Database
				if(conn != null) 
				{
					try {
					conn.close();
					} catch (SQLException e)
					{
						e.printStackTrace();			
						throw new DatabaseException(e);
					}
				}
			}
		}
		//returning stock if null or with data
		return stock;
	}
	/**
	 * Constantly collecting more stock data and it is put in database through this method
	 * 
	 * @param Stock
	 * @return boolean
	 */	
	
	@Override
	public boolean create(Stock stock) 
	{
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(url, username, password);//conection made
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
		catch(SQLException e)
		{
			// DB expcetion catch
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		finally 
		{
			//Cleanup Database
			if(conn != null) 
			{
				try 
				{
					//close connection
				conn.close();
				} 
				catch (SQLException e)
				{
					e.printStackTrace();			
					throw new DatabaseException(e);
				}
			}
		}
		//return reuslt if null or with data
		return result;
	}

	@Override
	public boolean update(Stock stock) {
		
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			String sql = "UPDATE `stock` SET "+Stock.getUpdateSetBySymbol(stock);
			
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			
			if(rs > 0) 
			{
				result = true; 
			}
			
			stmt.close();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		finally 
		{
			//Cleanup Database
			if(conn != null) 
			{
				try 
				{
					conn.close();
				} 
				catch (SQLException e)
				{
					e.printStackTrace();			
					throw new DatabaseException(e);
				}
			}
		}
		return result;
	}

	@Override
	public boolean delete(Stock t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Stock findBy(Stock t) {
		// TODO Auto-generated method stub
		return null;
	}

}
