package dbLayer.interfaceLayer;

import java.util.ArrayList;

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
	 * @return the cars
	 */
	public ArrayList<Car> getCars(Customer customer);
	
	/**
	 * Gets the car by reg nr.
	 *
	 * @param regNr the reg nr
	 * @return the car by reg nr
	 */
	public Car getCarByRegNr(String regNr);
	
	/**
	 * Gets the car by vin.
	 *
	 * @param vin the vin
	 * @return the car by vin
	 */
	public Car getCarByVin(String vin);
	
	/**
	 * Gets the car.
	 *
	 * @param id the id
	 * @return the car
	 */
	public Car getCar(int id);

	/**
	 * Insert Car into the database.
	 *
	 * @param car the Car
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertCar(Car car);
	
	/**
	 * Update Car in the database.
	 *
	 * @param car the Car
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updateCar(Car car);
	
	/**
	 * Delete Car from the database..
	 *
	 * @param car the Car
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int deleteCar(Car car);
}
