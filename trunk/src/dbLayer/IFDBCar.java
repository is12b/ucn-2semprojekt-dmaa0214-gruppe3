package dbLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;

/**
 * Interface for DBCar
 * 
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFDBCar {

	
	public ArrayList<Car> getCars(Customer customer);
	
	public Car getCarByRegNr(String regNr);
	
	public Car getCarByVin(String vin);
	
	public Car getCar(int id);
		
}
