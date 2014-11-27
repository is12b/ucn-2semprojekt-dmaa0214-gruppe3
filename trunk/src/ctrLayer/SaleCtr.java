package ctrLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;
import ctrLayer.exceptionLayer.CarDoesntExistException;
import ctrLayer.interfaceLayer.IFCarCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import ctrLayer.interfaceLayer.IFProductCtr;
import ctrLayer.interfaceLayer.IFSaleCtr;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;

public class SaleCtr implements IFSaleCtr {
	
	private Sale sale;
	
	public SaleCtr() {}
	
	/**
	 * Sale
	 */

	@Override
	public Sale createSale() {
		sale = new Sale();
		return sale;
	}

	@Override
	public void setCar(Car car){
		sale.setCar(car);
	}
	
	@Override
	public void setCustomer(Customer customer){
		sale.setCustomer(customer);
	}

	@Override
	public void createPartSale(Product product, double amount) {
		PartSale partSale = new PartSale(amount, product);
		sale.addPartSale(partSale);
	}
	
	
	/**
	 * Product
	 */
	
	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) {
		IFProductCtr pCtr = new ProductCtr();
		return pCtr.searchProductsByItemNumber(itemNumber);
	}
	
	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		IFProductCtr pCtr = new ProductCtr();
		return pCtr.searchProductsByName(name);
	}
	
	/**
	 * Car
	 */
	
	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso)
			throws NullPointerException {
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarByRegNr(regNr, retAsso);
	}

	@Override
	public Car getCarByVin(String vin, boolean retAsso)
			throws NullPointerException {
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarByVin(vin, retAsso);
	}	
	
	/**
	 * Customer
	 */

	@Override
	public Customer getCustomerByCvr(String cvr, boolean retAsso) {
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.getCustomerByCvr(cvr, retAsso);
	}

	@Override
	public ArrayList<Customer> searchCustomersByName(String name,
			boolean retAsso) {
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.searchCustomersByName(name, retAsso);
	}

	@Override
	public ArrayList<Customer> searchCustomersByPhone(String phone,
			boolean retAsso) {
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.searchCustomersByPhone(phone, retAsso);
	}



}
