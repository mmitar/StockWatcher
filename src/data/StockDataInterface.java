package data;

import java.util.List;

import javax.ejb.Local;

import beans.Stock;


/**
 * Contracts StockDataService with implemented methods
 * @author Matthew & Joey
 *
 */
@Local
public interface StockDataInterface <T> 
{
		public List<T> findAll();
		public T findById(int id);
		public Stock findBy(T t);
		public boolean create(T t);
		public boolean update(T t);
		public boolean delete(T t);
}
