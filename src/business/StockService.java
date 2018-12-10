package business;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import beans.Stock;
import data.StockDataInterface;
import util.InterceptorLogging;
import util.PostException;
import util.StockNotFoundException;

@Stateless
@Local(StockInterface.class)
@LocalBean
@Interceptors(InterceptorLogging.class)
public class StockService implements StockInterface {

	/**
	 * @return StockDataService methods
	 */
	@Inject
	StockDataInterface<Stock> dao;
	
	/**
	 * calls consumeStockIOT through SDI service
	 * 
	 * @param stock Stock
	 * @throws PostException
	 * @return Stock
	 */
	@Override
	public boolean saveStock(Stock stock) throws PostException{
		
		// Try to add the new stock and verify it was added
		if(dao.create(stock) == true)
		{
			// return true if successfully added
			return true;
		}
		// if creation failed, throw exception
		else
		{
			throw new PostException();
		}
	}

	/**
	 * Calls DAO to find stock and enforces logic for stock data
	 * 
	 * @param symbol String
	 * @throws StockNotFoundException
	 * @return Stock
	 */
	@Override
	public Stock getStock(String symbol) throws StockNotFoundException{
		
		// Try to stock data according to the user input symbol
		Stock stock = dao.findBy(symbol);
		
		// if not results were found throw exception
		if(stock == null)
		{
			throw new StockNotFoundException();
		}
		// if resulst were found return the stock data set
		else
		{
			return stock;
		}
	}
}

