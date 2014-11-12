package dbLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import dbLayer.interfaceLayer.IFDBCustomer;

/**
 * Class for DBCustomer
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBCustomer implements IFDBCustomer {

	@Override
	public Customer getCustomer(Car car) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getCustomersByPhone(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> getCustomersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(String cvr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCustomer(Customer Customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return 0;
	}

}
