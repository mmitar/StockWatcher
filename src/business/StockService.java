package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import beans.Stock;
import data.StockDataInterface;
import util.StockNotFoundException;

@Stateless
@Local(StockInterface.class)
@LocalBean
public class StockService implements StockInterface {

	/**
	 * @return StockDataService methods
	 */
	
	@EJB
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
			throw new StockNotFoundException();
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

