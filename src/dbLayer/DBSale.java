package dbLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Sale;
import dbLayer.interfaceLayer.IFDBSale;

/**
 * Class for DBSale
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBSale implements IFDBSale {

	@Override
	public ArrayList<Sale> getSales(Car car) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Sale> getSales(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sale getSale(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Sale> getAllSales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSale(Sale sale) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSale(Sale sale) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSale(Sale sale) {
		// TODO Auto-generated method stub
		return 0;
	}

}
