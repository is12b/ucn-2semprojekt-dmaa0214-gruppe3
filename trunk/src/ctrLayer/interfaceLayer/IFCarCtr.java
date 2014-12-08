package ctrLayer.interfaceLayer;
import modelLayer.Car;
import modelLayer.Customer;
import ctrLayer.exceptionLayer.DeleteException;
import ctrLayer.exceptionLayer.InsertException;
import ctrLayer.exceptionLayer.ObjectNotExistException;
import ctrLayer.exceptionLayer.UpdateException;

/**
 * Class for IFCarCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFCarCtr {
	
	public Car getCarByRegNr(String regNr, boolean retAsso) throws ObjectNotExistException;
	
	public Car getCarByVin(String vin, boolean retAsso) throws ObjectNotExistException;
	
	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year, Customer owner) throws InsertException;
	
	public void updateCar(Car car) throws UpdateException;
	
	public void deleteCar(Car car) throws DeleteException;

}
