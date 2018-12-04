package business;

import beans.Stock;
import util.PostException;
import util.StockNotFoundException;

/**
 * Contracts StockService with implemented methods
 */
public interface StockInterface 
{
	/**
	 * calls consumeStockIOT through SDI service
	 * 
	 * @param stock Stock
	 * @throws PostException
	 * @return Stock
	 */
	public boolean saveStock(Stock stock) throws PostException;

	/**
	 * Calls DAO to find stock and enforces logic for stock data
	 * 
	 * @param symbol String
	 * @throws StockNotFoundException
	 * @return Stock
	 */
	public Stock getStock(String symbol) throws StockNotFoundException;
}
