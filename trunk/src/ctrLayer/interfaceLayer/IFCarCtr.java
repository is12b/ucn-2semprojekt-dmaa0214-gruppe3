package ctrLayer.interfaceLayer;
import modelLayer.Car;
import modelLayer.Customer;
import exceptions.DBException;
import exceptions.ObjectNotExistException;

/**
 * Class for IFCarCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFCarCtr {
	
	public Car getCarByRegNr(String regNr, boolean retAsso) throws ObjectNotExistException;
	
	public Car getCarByVin(String vin, boolean retAsso) throws ObjectNotExistException;
	
	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year, Customer owner) throws DBException; // NO_UCD (unused code)
	
	public void updateCar(Car car) throws ObjectNotExistException, DBException; // NO_UCD (test only)
	
	public void deleteCar(Car car) throws ObjectNotExistException, DBException; // NO_UCD (test only)

}
