package data;

import java.util.List;

/**
 * Contracts StockDataService with implemented methods
 * @author Matthew & Joey
 *
 */
public interface DataAccessInterface <T> 
{
		public List<T> findAll();
		public T findById(int id);
		//Generate findby using for log in
		public T findBy(T t);
		public T findBy(String string);
		//generate create used for inserting new user
		public boolean create(T t);
		public boolean update(T t);
		public boolean delete(T t);
}
