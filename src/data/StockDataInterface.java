package data;

import java.util.List;

/**
 * Contracts StockDataService with implemented methods
 * @author Matthew & Joey
 *
 */
public interface StockDataInterface<T> 
{
		/**
		 * READ crud method that finds the stock data by symbol
		 * 
		 * @param symbol String
		 * @result T
		 */
		public T findBy(String symbol);
		
		/**
		 * Constantly collecting more stock data and it is put in database through this method
		 * 
		 * @param T
		 * @return boolean
		 */	
		public boolean create(T t);
		
		/**
		 * Updates Stock data by the symbol
		 * 
		 * @param T
		 * @return boolean
		 */
		public boolean update(T t);
		
		public List<T> findAll();
		public T findById(int id);
		public T findBy(T t);
		public boolean delete(T t);
}
