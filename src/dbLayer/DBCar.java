package dbLayer;

import java.sql.Connection;
import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import dbLayer.interfaceLayer.IFDBCar;

/**
 * Class for DBCar
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBCar implements IFDBCar {
	private Connection conn;
	
	public DBCar(){
		conn = DBConnection.getInstance().getDBCon();
	}

	@Override
	public ArrayList<Car> getCars(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car getCar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCar(Car car) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCar(Car car) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCar(Car car) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car getCarByVin(String vin, boolean retAsso) {
		// TODO Auto-generated method stub
		return null;
	}

}
