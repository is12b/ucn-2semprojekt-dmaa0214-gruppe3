package ctrLayer;

import java.util.ArrayList;

import ctrLayer.exceptionLayer.ObjectNotExistException;
import ctrLayer.interfaceLayer.IFCarCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;
import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Sale;

/**
 * Controller for SaleOverview
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class SaleOverviewCtr {

	/**
	 * Constructor for SaleOverviewCtr objects.
	 *
	 */
	public SaleOverviewCtr() {
		// TODO Auto-generated constructor stub
	}
	
	public Sale getSaleByID(int id) throws ObjectNotExistException {
		IFDBSale dbSale = new DBSale();
		Sale s = dbSale.getSale(id);
		if (s == null) {
			throw new ObjectNotExistException("Faktura Nummeret blev ikke fundet");
		}
		return s;
	}
	
	public ArrayList<Sale> getAllSales() throws ObjectNotExistException {
		IFDBSale dbSale = new DBSale();
		
		ArrayList<Sale> list = dbSale.getAllSales();
		if (list == null || list.size() == 0) {
			throw new ObjectNotExistException("Der er ingen fakturaer i systemet");
		}
		return list;
	}
	
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
