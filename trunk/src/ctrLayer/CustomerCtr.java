package ctrLayer;

import java.util.ArrayList;

import modelLayer.Customer;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import dbLayer.DBCustomer;
import dbLayer.interfaceLayer.IFDBCustomer;

public class CustomerCtr implements IFCustomerCtr {
	
	//TODO FEJL HÅNDTERING !!
	//TODO FEJL HÅNDTERING !!
	//TODO FEJL HÅNDTERING !!

	@Override
	public Customer createCustomer(String name, String phoneNumber, String address, int postalCode, int cvr, Boolean hidden) {
		IFDBCustomer dbCus = new DBCustomer();
		Customer newCustomer = new Customer(name, phoneNumber, address, postalCode, cvr, hidden);
		dbCus.insertCustomer(newCustomer);
		return newCustomer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		IFDBCustomer dbCus = new DBCustomer();
		dbCus.updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		IFDBCustomer dbCus = new DBCustomer();
		dbCus.deleteCustomer(customer);
	}

	@Override
	public Customer getCustomerByCvr(String cvr) {
		Customer foundCustomer = null;
		IFDBCustomer dbCus = new DBCustomer();
		foundCustomer = dbCus.getCustomerByCvr(cvr);
		return foundCustomer;
	}

	@Override
	public ArrayList<Customer> searchCustomersByName(String name) {
		ArrayList<Customer> foundCustomers = null;
		IFDBCustomer dbCus = new DBCustomer();
		foundCustomers = dbCus.getCustomersByName(name);
		return foundCustomers;
	}

	@Override
	public ArrayList<Customer> searchCustomersByPhone(String phone) {
		ArrayList<Customer> foundCustomers = null;
		IFDBCustomer dbCus = new DBCustomer();
		foundCustomers = dbCus.getCustomersByPhone(phone);
		return foundCustomers;
	}

}
