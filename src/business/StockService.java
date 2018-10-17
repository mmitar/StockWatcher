package business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Stock;
import data.StockDataInterface;

@Stateless
@Local(StockInterface.class)
@LocalBean
@Alternative
public class StockService implements StockInterface {

	/**
	 * @return StockDataService methods
	 */
	@SuppressWarnings("rawtypes")
	@EJB
	StockDataInterface dao;
	
	/**
	 * calls consumeStockIOT through SDI service
	 * @return Stock
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateIEX_Previous(Stock stock) {
		
		if(dao.findBy(stock) == null)
		{
			if(dao.create(stock) == true) {
				return false;
			}
			else {
				return false;
			}
		}
		else if(dao.update(stock)) {
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
}
