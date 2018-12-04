package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import beans.Stock;
import data.StockDataInterface;
import util.PostException;
import util.StockNotFoundException;

@Stateless
@Local(StockInterface.class)
@LocalBean
public class StockService implements StockInterface {

	/**
	 * @return StockDataService methods
	 */
	
	@Inject
	StockDataInterface<Stock> dao;
	/**
	 * calls consumeStockIOT through SDI service
	 * @return Stock
	 */
	
	@Override
	public boolean saveStock(Stock stock) throws StockNotFoundException{
		
		if(dao.create(stock) == true)
		{
			return true;
		}
		else
		{
			throw new PostException();
		}
		
	}

	@Override
	public Stock getStock(String symbol) throws StockNotFoundException{
		
		Stock stock = dao.findBy(symbol);
		
		if(stock == null)
		{
			throw new StockNotFoundException();
		}
		else
		{
			return stock;
		}
	}
}

