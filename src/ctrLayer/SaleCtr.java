package ctrLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;
import ctrLayer.exceptionLayer.CarDoesntExistException;
import ctrLayer.interfaceLayer.IFCarCtr;
import ctrLayer.interfaceLayer.IFProductCtr;
import ctrLayer.interfaceLayer.IFSaleCtr;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;

public class SaleCtr implements IFSaleCtr {
	
	private Sale sale;
	
	public SaleCtr() {
	}

	@Override
	public Sale createSale() {
		Sale sale = new Sale();
		return sale;
	}

	@Override
	public void addCar(String regNr) throws CarDoesntExistException {
		IFCarCtr carCtr = new CarCtr();
		IFDBSale dbSale = new DBSale();
		
		Car car = carCtr.getCarByRegNr(regNr, false);
		sale.setCar(car);
		
		if(car == null) {
			throw new CarDoesntExistException("There is no car with this registration number.");
		}
		
		dbSale.updateSale(sale);
	}

	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) {
		IFProductCtr pCtr = new ProductCtr();
		return pCtr.searchProductsByItemNumber(itemNumber);
	}

	@Override
	public void createPartSale(Product product, double amount) {
		PartSale partSale = new PartSale(amount, product);
		sale.addPartSale(partSale);
	}

}
