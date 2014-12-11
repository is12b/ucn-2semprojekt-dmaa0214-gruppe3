package ctrLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;
import ctrLayer.interfaceLayer.IFCarCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import ctrLayer.interfaceLayer.IFProductCtr;
import ctrLayer.interfaceLayer.IFSaleCtr;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;
import exceptions.DBException;
import exceptions.ObjectNotExistException;
import exceptions.SubmitException;

public class SaleCtr implements IFSaleCtr {
	
	private Sale sale;
	
	public SaleCtr() {}
	
	/*
	 * Sale
	 */

	@Override
	public Sale createSale() {
		sale = new Sale();
		return sale;
	}
	
	/*
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
	
	private PartSale isSameProduct(Product product, double price){
		PartSale pSale = null;
		
		boolean found = false;
		int i = 0;
		while(!found && i < sale.getPartSales().size()){
			PartSale p = sale.getPartSales().get(i);
			if(p.getProduct().getId() == product.getId()){
				if(p.getPrice() == price){
					pSale = p;
					found = true;
				}
			}
			i++;
		}
		
		return pSale;
	}
	
	@Override
	public void removePartSale(PartSale pSale){
		sale.removePartSale(pSale);
	}
	
	/*
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
	
	/*
	 * Car
	 */
	
	@Override
	public void setCar(Car car){
		sale.setCar(car);
	}
	
	@Override
	public Car getCarByRegNr(String regNr) throws ObjectNotExistException{
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarByRegNr(regNr, true);
	}

	@Override
	public Car getCarByVin(String vin) throws ObjectNotExistException{
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarByVin(vin, true);
	}	
	
	/*
	 * Customer
	 */
	
	@Override
	public void setCustomer(Customer customer){
		sale.setCustomer(customer);
	}

	@Override
	public Customer getCustomerByCvr(String cvr) throws ObjectNotExistException{
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.getCustomerByCvr(cvr, true);
	}

	@Override
	public ArrayList<Customer> searchCustomersByName(String name) throws ObjectNotExistException{
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.searchCustomersByName(name, true);
	}

	@Override
	public ArrayList<Customer> searchCustomersByPhone(String phone) throws ObjectNotExistException{
		IFCustomerCtr cCtr = new CustomerCtr();
		return cCtr.searchCustomersByPhone(phone, true);
	}
	
	/*
	 * Description
	 */

	@Override
	public void addDescription(String desc) {
		sale.setDescription(desc);
	}
	
	public String getDescription(){
		return sale.getDescription();
	}
	
	/*
	 * Mileage
	 */
	
	@Override
	public void addMileage(int mileage) {
		sale.setMileage(mileage);
	}
	
	@Override
	public int getMileage() {
		return sale.getMileage();
	}
		
	/*
	 * Commit / Save
	 */

	@Override
	public Sale commit(boolean paid) throws DBException, SubmitException {
		if(!checkPartSales()){
			throw new SubmitException("Der er ikke tilføjet nogle produkter til ordren");
		}else{
			IFDBSale dbSale = new DBSale();
			
			sale.setPaid(paid);
			dbSale.insertSale(sale);
		}
		
		return sale;
	}

	private boolean checkPartSales() {
		boolean retBool = true;
		
		if(sale.getPartSales() == null || sale.getPartSales().size() == 0){
			retBool = false;
		}
		
		return retBool;
	}

    /*
     * Sale	
     */
	
	@Override
	public Sale getSaleByID(int id) throws ObjectNotExistException {
		IFDBSale dbSale = new DBSale();
		Sale s = dbSale.getSale(id);
		if (s == null) {
			throw new ObjectNotExistException("Faktura Nummeret blev ikke fundet");
		}
		return s;
	}
	
	@Override
	public ArrayList<Sale> getAllSales() throws ObjectNotExistException {
		IFDBSale dbSale = new DBSale();
		
		ArrayList<Sale> list = dbSale.getAllSales();
		if (list == null || list.size() == 0) {
			throw new ObjectNotExistException("Der er ingen fakturaer i systemet");
		}
		return list;
	}
	
	@Override
	public ArrayList<Sale> getSaleByCarRegNr(String regNr) throws ObjectNotExistException {
		IFCarCtr carCtr = new CarCtr();
		Car car = carCtr.getCarByRegNr(regNr, false);
		
		IFDBSale dbSale = new DBSale();
		ArrayList<Sale> sales = dbSale.getSales(car);
		if (sales == null || sales.size() == 0) {
			throw new ObjectNotExistException("Bilen blev fundet, men der er ingen "
					+ "faktura tilknyttet bilen med regNr: " + car.getRegNr());
		}
		return sales;
	}
	
	@Override
	public ArrayList<Sale> getSaleByCarVIN(String vin) throws ObjectNotExistException {
		IFCarCtr carCtr = new CarCtr();
		Car car = carCtr.getCarByVin(vin, false);
		
		IFDBSale dbSale = new DBSale();
		ArrayList<Sale> sales = dbSale.getSales(car);
		if (sales == null || sales.size() == 0) {
			throw new ObjectNotExistException("Bilen blev fundet, men der er ingen "
					+ "faktura tilknyttet bilen med stelnr.: " + car.getVin());
		}
		return sales;
	}
	
	@Override
	public ArrayList<Sale> getSaleByCusName(String name) throws ObjectNotExistException {
		IFCustomerCtr cusCtr = new CustomerCtr();
		ArrayList<Customer> cusList = cusCtr.searchCustomersByName(name, false);
		
		ArrayList<Sale> sales = getSalesByCusList(cusList);
		
		if (sales == null || sales.size() == 0) {
			throw new ObjectNotExistException("min. 1 kunde med navnet blev fundet, men der er ingen "
					+ "faktura tilknyttet kunden/kunderne");
		}
		return sales;
	}
	
	@Override
	public ArrayList<Sale> getSaleByCusPhone(String phone) throws ObjectNotExistException {
		IFCustomerCtr cusCtr = new CustomerCtr();
		ArrayList<Customer> cusList = cusCtr.searchCustomersByPhone(phone, false);
		
		ArrayList<Sale> sales = getSalesByCusList(cusList);
		
		if (sales == null || sales.size() == 0) {
			throw new ObjectNotExistException("min. 1 kunde med navnet blev fundet, men der er ingen "
					+ "faktura tilknyttet kunden/kunderne");
		}
		return sales;
	}
	
	@Override
	public ArrayList<Sale> getSaleByCusCVR(String cvr) throws ObjectNotExistException {
		IFCustomerCtr cusCtr = new CustomerCtr();
		Customer cus = cusCtr.getCustomerByCvr(cvr, false);
		
		IFDBSale dbSale = new DBSale();
		ArrayList<Sale> sales = dbSale.getSales(cus);
		
		if (sales == null || sales.size() == 0) {
			throw new ObjectNotExistException("min. 1 kunde med navnet blev fundet, men der er ingen "
					+ "faktura tilknyttet kunden/kunderne");
		}
		return sales;
	}
		
	private ArrayList<Sale> getSalesByCusList(ArrayList<Customer> cusList) {
		IFDBSale dbSale = new DBSale();
		ArrayList<Sale> sales = new ArrayList<Sale>();
		
		for (Customer cus : cusList) {
			ArrayList<Sale> cusSales = dbSale.getSales(cus);
			if (cusSales != null && cusSales.size() != 0) {
				sales.addAll(cusSales);
			}
		}
		return sales;
	}
}
