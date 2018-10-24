package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.Stock;
import data.DataAccessInterface;

@Stateless
@Local(StockInterface.class)
@LocalBean
public class StockService implements StockInterface {

	/**
	 * @return StockDataService methods
	 */
	
	@Inject
	DataAccessInterface<Stock> dao;
	/**
	 * calls consumeStockIOT through SDI service
	 * @return Stock
	 */
	
	@Override
	public boolean updateIEX_Previous(Stock stock) {
		
		if(dao.create(stock) == true) {
			return true;
		}
		
		return false;
	}

	@Override
	public Stock getPrevious() {
		
		// TODO: Identify valid findBy parameter
		Stock stock = dao.findBy("AAPL");
		
		return stock;
	}

	@Override
	public Stock getStock(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

