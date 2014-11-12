package ctrLayer.interfaceLayer;

import modelLayer.Customer;

/**
 * Class for IFCustomerCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFCustomerCtr {

	public Customer createCustomer(String name, String phoneNumber);
	
	public void updateCustomer(Customer customer);
	
	public void deleteCustomer(Customer customer);
	
}
