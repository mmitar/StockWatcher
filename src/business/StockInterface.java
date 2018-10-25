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
	 * Calls the IOT to consume Json API from IEX
	 * @return Stock
	 */	
	public Stock getStock(String symbol) throws StockNotFoundException;
	public boolean saveStock(Stock stock) throws StockNotFoundException;

}
