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
	 * @injects StockDataService methods
	 */
	@EJB
	StockDataInterface<Stock> dao;

	/**
	 * Calls the IOT to consume Json API from IEX
	 * 
	 * @throws StockNotFoundException
	 * @param stock Stock
	 * @return boolean
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

	/**
	 * Calls the dao to get stock by symbol
	 * 
	 * @throws StockNotFoundException
	 * @param symbol String
	 * @return stock Stock
	 */
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

