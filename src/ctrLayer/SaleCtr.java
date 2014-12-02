package ctrLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;
import ctrLayer.exceptionLayer.ObjectNotExistException;
import ctrLayer.interfaceLayer.IFCarCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import ctrLayer.interfaceLayer.IFProductCtr;
import ctrLayer.interfaceLayer.IFSaleCtr;

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
	
	/**
	 * PartSale
	 */

	@Override
	public void createPartSale(Product product, double amount, double unitPrice) {
		PartSale pSale = isSameProduct(product, unitPrice);
		if(pSale != null){
			pSale.setAmount(pSale.getAmount() + amount);
		}else{
			PartSale partSale = new PartSale(amount, product, unitPrice);
			sale.addPartSale(partSale);
		}
	}
	
	private PartSale isSameProduct(Product product, double unitPrice){
		PartSale pSale = null;
		
		boolean found = false;
		int i = 0;
		while(!found && i < sale.getPartSales().size()){
			PartSale p = sale.getPartSales().get(i);
			if(p.getProduct().getId() == product.getId()){
				if(p.getUnitPrice() == unitPrice){
					pSale = p;
					found = true;
				}
			}
			i++;
		}
		
		return pSale;
	}
	
	/**
	 * Product
	 */
	
	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) throws ObjectNotExistException{
		IFProductCtr pCtr = new ProductCtr();
		return pCtr.searchProductsByItemNumber(itemNumber);
	}
	
	@Override
	public ArrayList<Product> searchProductsByName(String name) throws ObjectNotExistException{
		IFProductCtr pCtr = new ProductCtr();
		return pCtr.searchProductsByName(name);
	}
	
	/**
	 * Car
	 */
	
	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso) throws ObjectNotExistException{
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarByRegNr(regNr, retAsso);
	}

	@Override
	public Car getCarByVin(String vin, boolean retAsso) throws ObjectNotExistException{
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarByVin(vin, retAsso);
	}	
	
	/**
	 * Customer
	 */

	@Override
	public Customer getCustomerByCvr(String cvr, boolean retAsso) throws ObjectNotExistException{
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.getCustomerByCvr(cvr, retAsso);
	}

	@Override
	public ArrayList<Customer> searchCustomersByName(String name,
			boolean retAsso) throws ObjectNotExistException{
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.searchCustomersByName(name, retAsso);
	}

	@Override
	public ArrayList<Customer> searchCustomersByPhone(String phone,
			boolean retAsso) throws ObjectNotExistException{
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.searchCustomersByPhone(phone, retAsso);
	}



}
