package dbLayer.interfaceLayer;

import exceptions.DBException;
import modelLayer.Car;
import modelLayer.CarExtra;

/**
 * Interface for DBCar.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBCarExtra {

	/**
	 * Gets the CarExtra.
	 *
	 * @param Car the car
	 * @return the CarExtra
	 */
	public CarExtra getCarExtra(Car car);

	/**
	 * Insert Car into the database.
	 *
	 * @param ext the CarExtra
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertCarExtra(CarExtra ext, Car car) throws DBException;
	
	/**
	 * Update CarExtra in the database.
	 *
	 * @param ext the CarExtra
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updateCarExtra(CarExtra ext, Car car) throws DBException ;
	
}
