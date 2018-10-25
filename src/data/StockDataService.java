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
@Local(DataAccessInterface.class)
@LocalBean
public class StockDataService implements DataAccessInterface<Stock> {

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

	@Override
	public Stock findBy(String symbol) {
		
		Stock stock = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			String sql = String.format("SELECT * FROM `stock` WHERE `SYMBOL` = '%s' LIMIT 1", symbol);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println(sql);
			if(rs.next())
			{
				stock = Stock.getOneResultSet(rs); // Stock Found
			}
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
		return stock;
	}
	
	@Override
	public boolean create(Stock stock) 
	{
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			String sql = "INSERT INTO `stock` ("+Stock.getParamSet()+") VALUES ("+Stock.getValueSet(stock)+")";
			System.out.println(sql);
			
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
