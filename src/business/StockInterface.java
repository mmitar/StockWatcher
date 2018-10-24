package business;

import beans.Stock;

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
	public Stock getStock(Stock stock);
	
	public boolean updateIEX_Previous(Stock stock);

	public Stock getPrevious();
}
