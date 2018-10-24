package data;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import beans.Stock;
import beans.User;
import business.StockService;


/**
 * Contracts StockDataService with implemented methods
 * @author Matthew & Joey
 *
 */
public interface DataAccessInterface <T> 
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
