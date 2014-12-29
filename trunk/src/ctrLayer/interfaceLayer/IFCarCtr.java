package ctrLayer.interfaceLayer;
import modelLayer.Car;
import modelLayer.Customer;
import exceptions.DBException;
import exceptions.ObjectNotExistException;

/**
 * Interface for CarCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFCarCtr {
	
	/**
	 * Get a car object by regNr
	 * @param regNr the regNr to look for
	 * @param retAsso retrieve associations to the object.
	 * @return The found car object
	 * @throws ObjectNotExistException If no car is found
	 */
	public Car getCarByRegNr(String regNr, boolean retAsso) throws ObjectNotExistException;
	
	public Car getCarByVin(String vin, boolean retAsso) throws ObjectNotExistException;
	
	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year, Customer owner) throws DBException; // NO_UCD (unused code)
	
	public void updateCar(Car car) throws ObjectNotExistException, DBException; // NO_UCD (test only)
	
	public void deleteCar(Car car) throws ObjectNotExistException, DBException; // NO_UCD (test only)

	// Iteration 2
	/**
	 * Method for get a new car object with all scraped data.
	 * 
	 * @param regOrVin the regNr or vin number of the car
	 * @return a car object
	 */
	public Car getCarData(String regOrVin) throws ObjectNotExistException;

}
