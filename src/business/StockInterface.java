package business;

import beans.Stock;
import util.StockNotFoundException;

/**
 * Contracts StockService with implemented methods
 * @author Matthew & Joey
 *
 */
public interface StockInterface {
	
	/**
	 * Calls the dao to get stock by symbol
	 * 
	 * @throws StockNotFoundException
	 * @param symbol String
	 * @return stock Stock
	 */
	public Stock getStock(String symbol) throws StockNotFoundException;
	
	/**
	 * Calls the IOT to consume Json API from IEX
	 * 
	 * @throws StockNotFoundException
	 * @param stock Stock
	 * @return boolean
	 */	
	public boolean saveStock(Stock stock) throws StockNotFoundException;

}
