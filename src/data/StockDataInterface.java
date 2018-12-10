package data;

import java.util.List;

/**
 * Generic interface that contracts methods for CDI. This is currently only being implemented for StockDataService.
 */
public interface StockDataInterface<T> 
{
	/**
	 * READ method
	 * 
	 * @return List<T>
	 */
	public List<T> findAll();
	
	/**
	 * READ method
	 * 
	 * @param id int
	 * @return T
	 */
	public T findById(int id);
	
	/**
	 * READ method
	 * 
	 * @param t T
	 * @return T
	 */
	public T findBy(T t);
	
	/**
	 * READ method
	 * 
	 * @param string String
	 * @return T
	 */
	public T findBy(String string);
	
	/**
	 * CREATE method
	 * 
	 * @param t T
	 * @return boolean
	 */
	public boolean create(T t);
	
	/**
	 * UPDATE method
	 * 
	 * @param t T
	 * @return boolean
	 */
	public boolean update(T t);
	
	/**
	 * DELETE method
	 * 
	 * @param t T
	 * @return boolean
	 */
	public boolean delete(T t);
}
