package dbLayer.interfaceLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.DBException;
import modelLayer.Car;
import modelLayer.CarExtra;
import modelLayer.Customer;
import modelLayer.Inspection;

/**
 * Interface for DBCar.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBInspection {

	/**
	 * Gets the CarExtra.
	 *
	 * @param Car the car
	 * @return the CarExtra
	 */
	public ArrayList<Inspection> getInspections(Car car);

	/**
	 * Insert Car into the database.
	 *
	 * @param ext the CarExtra
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertInspection(Inspection inspec, Car car) throws SQLException;

	/**
	 * @param inspections
	 * @param car
	 */
	public void insertInspections(ArrayList<Inspection> inspections, Car car) throws SQLException;
	
}
