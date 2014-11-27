package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Customer;

/**
 * Class for IFCustomerCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFCustomerCtr {

	public Customer createCustomer(String name, String phoneNumber, String address, int postalCode, int cvr, boolean hidden);
	
	public Customer updateCustomer(Customer customer, String name, String phoneNumber, String address, int postalCode, int cvr, boolean hidden);
	
	public void deleteCustomer(Customer customer);

	/**
	 * @param cvr
	 * @return
	 */
	public Customer getCustomerByCvr(String cvr, boolean retAsso);

	/**
	 * @param name
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByName(String name, boolean retAsso);

	/**
	 * @param phone
	 * @return
	 */
	public ArrayList<Customer> searchCustomersByPhone(String phone, boolean retAsso);

	/**
	 * @param regNr
	 * @return
	 */
	public Customer getCustomerByRegNr(String regNr);
	
}
