package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import dbLayer.interfaceLayer.IFDBCar;
import dbLayer.interfaceLayer.IFDBCustomer;

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
	
	
	private Car singleWhere(String wQuery, boolean retAsso){
		Car car = null;
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				car = buildCar(rs);
				if(retAsso){
					IFDBCustomer dbCustomer = new DBCustomer();
					dbCustomer.getCustomer(car);
				}
			}
		}catch(Exception e){
			System.out.println("DBCar - SingleWhere - Exception");
			e.printStackTrace();
		}
		
		return car;
	}
	
	private ArrayList<Car> miscWhere(String wQuery, boolean retAsso){
		ArrayList<Car> cars = new ArrayList<Car>();
		
		
		return cars;
	}

	/**
	 * @param rs
	 * @return
	 */
	private Car buildCar(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param wQuery
	 * @return
	 */
	private String buildQuery(String wQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
