package ctrLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import ctrLayer.interfaceLayer.IFCarCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import dbLayer.DBCustomer;
import dbLayer.interfaceLayer.IFDBCustomer;
import exceptions.DBException;
import exceptions.DBNotFoundException;
import exceptions.ObjectNotExistException;

public class CustomerCtr implements IFCustomerCtr {

	@Override
	public Customer createCustomer(String name, String phoneNumber, String address, int postalCode, String city, int cvr, String email, boolean hidden) throws DBException {
		IFDBCustomer dbCus = new DBCustomer();
		Customer newCustomer = new Customer(name, phoneNumber, address, postalCode, city, cvr, email, hidden);
		dbCus.insertCustomer(newCustomer);
		
		return newCustomer;
	}

	@Override
	public Customer updateCustomer(Customer customer, String name, String phoneNumber, String address, String city, int postalCode, int cvr, String email, boolean hidden) throws ObjectNotExistException, DBException {

		IFDBCustomer dbCus = new DBCustomer();
		Customer tempCus = null;
		try {
			tempCus = customer.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("CustomerCtr: CloneNotSupportedException: "+ e.getMessage());
			//e.printStackTrace();
		}
		
		final boolean setCity = !city.trim().isEmpty();
		final boolean setName = !name.trim().isEmpty();
		final boolean setPhoneNumber = !phoneNumber.trim().isEmpty();
		final boolean setAddress = !address.trim().isEmpty();
		final boolean setPostalCode = postalCode != 0;
		final boolean setCvr = cvr != 0;
		final boolean setEmail = !email.trim().isEmpty();

		if(setCity) {
			customer.setCity(city);
		}
		
		if(setName) {
			customer.setName(name);
		}
		if(setPhoneNumber) {
			customer.setPhoneNumber(phoneNumber);
		}
		if(setAddress) {
			customer.setAddress(address);
		}
		if(setPostalCode) {
			customer.setPostalCode(postalCode);
		}
		if(setCvr) {
			customer.setCvr(cvr);
		}
		if(setEmail) {
			customer.setEmail(email);
		}
		
		customer.setHidden(hidden);
		try {
			dbCus.updateCustomer(customer);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Kunden blev ikke fundet");
		} catch(DBException e) {
			customer.setToClone(tempCus);
			throw new DBException(e.getMessage());
		}
		
		return customer;
	}

	@Override
	public void deleteCustomer(Customer customer) throws ObjectNotExistException, DBException {
		IFDBCustomer dbCus = new DBCustomer();
		try {
			dbCus.deleteCustomer(customer);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Kunden blev ikke fundet");
		}
	}

	@Override
	public Customer getCustomerByCvr(String cvr, boolean retAsso) throws ObjectNotExistException{
		IFDBCustomer dbCus = new DBCustomer();
		Customer foundCustomer = dbCus.getCustomerByCvr(cvr, retAsso);
		if(foundCustomer == null ){
			throw new ObjectNotExistException("Ingen kunder fundet");
		}
		return foundCustomer;
	}

	@Override
	public ArrayList<Customer> searchCustomersByName(String name, boolean retAsso) throws ObjectNotExistException{
		ArrayList<Customer> foundCustomers = null;
		IFDBCustomer dbCus = new DBCustomer();
		foundCustomers = dbCus.getCustomersByName(name, retAsso);
		if(foundCustomers == null || foundCustomers.size() == 0){
			throw new ObjectNotExistException("Ingen kunder fundet");
		}
		return foundCustomers;
	}

	@Override
	public ArrayList<Customer> searchCustomersByPhone(String phone, boolean retAsso) throws ObjectNotExistException{
		ArrayList<Customer> foundCustomers = null;
		IFDBCustomer dbCus = new DBCustomer();
		foundCustomers = dbCus.getCustomersByPhone(phone, retAsso);
		if(foundCustomers == null || foundCustomers.size() == 0){
			throw new ObjectNotExistException("Ingen kunder fundet");
		}
		return foundCustomers;
	}

	@Override
	public Customer getCustomerByRegNr(String regNr) throws ObjectNotExistException{
		IFDBCustomer dbCus = new DBCustomer();
		Customer cust = dbCus.getCustomerByRegNr(regNr);
		if(cust == null){
			throw new ObjectNotExistException("Ingen kunde fundet");
		}
		return cust;
	}

	
	// Iteration 2
	@Override
	public Car getCarData(String regOrVin) throws ObjectNotExistException {
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarData(regOrVin);
	}

}

