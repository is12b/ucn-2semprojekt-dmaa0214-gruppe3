package dbLayer.interfaceLayer;

import java.util.ArrayList;

import exceptions.DBException;
import modelLayer.Car;
import modelLayer.Customer;

/**
 * Interface for DBCar.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBCar {

	/**
	 * Gets all cars.
	 *
	 * @param customer the customer 
	 * @param retAsso If true: include associations to other objects
	 * @return the cars
	 */
	public ArrayList<Car> getCars(Customer customer, boolean retAsso);
	
	/**
	 * Gets the car by reg nr.
	 *
	 * @param regNr the reg nr
	 * @param retAsso If true: include associations to other objects
	 * @return the car by reg nr
	 */
	public Car getCarByRegNr(String regNr, boolean retAsso);
	
	/**
	 * Gets the car by vin.
	 *
	 * @param vin the vin
	 * @param retAsso If true: include associations to other objects
	 * @return the car by vin
	 */
	public Car getCarByVin(String vin, boolean retAsso);
	
	/**
	 * Gets the car.
	 *
	 * @param id the id
	 * @return the car
	 */
	public Car getCar(int id, boolean retAsso);

	/**
	 * Insert Car into the database.
	 *
	 * @param car the Car
	 * @param selfControlTransaction true if this method may control transactions
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertCar(Car car, boolean selfControlTransaction) throws DBException ;
	
	/**
	 * Update Car in the database.
	 *
	 * @param car the Car
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updateCar(Car car, boolean updateAsso) throws DBException ;
	
	/**
	 * Delete Car from the database..
	 *
	 * @param car the Car
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int deleteCar(Car car) throws DBException ;
}
