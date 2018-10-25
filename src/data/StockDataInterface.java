package data;

import java.util.List;

/**
 * Contracts StockDataService with implemented methods
 * @author Matthew & Joey
 *
 */
public interface StockDataInterface<T> 
{
		public List<T> findAll();
		public T findById(int id);
		public T findBy(T t);
		//TODO change string value name
		public T findBy(String string);
		public boolean create(T t);
		public boolean update(T t);
		public boolean delete(T t);
}
