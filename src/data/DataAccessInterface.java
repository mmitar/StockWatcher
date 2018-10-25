package data;

import java.util.List;

/**
 * Contracts StockDataService with implemented methods
 * @author Matthew & Joey
 *
 */
public interface DataAccessInterface <T> 
{
		/**
		 * READ Method
		 * Login method to find selected user
		 * 
		 * @param T
		 * @return T
		 */	
		public T findBy(T t);
		
		/**
		 * CREATE Method
		 * DAO method to register new user and enter user data in database
		 * 
		 * @param T
		 * @return boolean
		 */
		public boolean create(T t);
		
		public List<T> findAll();
		public T findById(int id);
		public boolean update(T t);
		public boolean delete(T t);
		public T findBy(String string);
}
