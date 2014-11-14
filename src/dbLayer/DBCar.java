package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerException;

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
	public ArrayList<Car> getCars(Customer customer, boolean retAsso) {
		return miscWhere("CustomerID = " + customer.getId(), retAsso);
	}

	@Override
	public Car getCar(int id, boolean retAsso) {
		return singleWhere("CarID = " + id, retAsso);
	}

	@Override
	public int insertCar(Car car) {
		int rc = -1;
		
		try{
			String query = "INSERT INTO CAR"
					+ " (CustomerID, Brand, Model, RegNR, Mileage, VIN, Hidden, Year) VALUES "
					+ "(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setQueryTimeout(5);
			stmt.setInt(1, car.getOwner().getId());
			
			if(car.getBrand() == null || car.getBrand().isEmpty()){
				stmt.setNull(2, java.sql.Types.NULL);
			} else {
				stmt.setString(2, car.getBrand());
			}
			
			if(car.getModel() == null || car.getModel().isEmpty()){
				stmt.setNull(3, java.sql.Types.NULL);
			}else{
				stmt.setString(3, car.getModel());
			}
			
			if(car.getRegNr() == null || car.getRegNr().isEmpty()){
				stmt.setNull(4, java.sql.Types.NULL);
			}else{
				stmt.setString(4, car.getRegNr());
			}
			
			if(car.getMileage() == 0){
				stmt.setNull(5, java.sql.Types.NULL);
			}else{
				stmt.setInt(5, car.getMileage());
			}
			
			if(car.getVin() == null || car.getVin().isEmpty()){
				stmt.setNull(6, java.sql.Types.NULL);
			}else{
				stmt.setString(6, car.getVin());
			}
			

			stmt.setBoolean(7, car.isHidden());
			
			if(car.getYear() == 0){
				stmt.setNull(8, java.sql.Types.NULL);
			}else{
				stmt.setInt(8, car.getYear());
			}
			
			rc = stmt.executeUpdate();
			
			ResultSet genRs = stmt.getGeneratedKeys();
			if(genRs.next()){
				car.setId(genRs.getInt(1));
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBCar - InsertCar - Exception");
			e.printStackTrace();
		}
		
		
		return rc;
	}

	@Override
	public int updateCar(Car car) {
		int rc = -1;
		
		try{
			String query = "UPDATE CAR SET "
					+ "CustomerID = ?, Brand = ?, Model = ?, RegNr = ?, "
					+ "Mileage = ?, VIN = ?, Hidden = ?, Year = ? WHERE CarID = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			stmt.setInt(1, car.getOwner().getId());
			if(car.getBrand() == null || car.getBrand().isEmpty()){
				stmt.setNull(2, java.sql.Types.NULL);
			} else {
				stmt.setString(2, car.getBrand());
			}
			
			if(car.getModel() == null || car.getModel().isEmpty()){
				stmt.setNull(3, java.sql.Types.NULL);
			}else{
				stmt.setString(3, car.getModel());
			}
			
			if(car.getRegNr() == null || car.getRegNr().isEmpty()){
				stmt.setNull(4, java.sql.Types.NULL);
			}else{
				stmt.setString(4, car.getRegNr());
			}
			
			if(car.getMileage() == 0){
				stmt.setNull(5, java.sql.Types.NULL);
			}else{
				stmt.setInt(5, car.getMileage());
			}
			
			if(car.getVin() == null || car.getVin().isEmpty()){
				stmt.setNull(6, java.sql.Types.NULL);
			}else{
				stmt.setString(6, car.getVin());
			}
			

			stmt.setBoolean(7, car.isHidden());
			
			if(car.getYear() == 0){
				stmt.setNull(8, java.sql.Types.NULL);
			}else{
				stmt.setInt(8, car.getYear());
			}
			stmt.setInt(9, car.getId());
			
			rc = stmt.executeUpdate();
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBCar - InsertCar - Exception");
			e.printStackTrace();
		}
		
		
		return rc;
	}

	@Override
	public int deleteCar(Car car) {
		int rc = -1;
		
		try{
			String query = "DELETE FROM CAR WHERE CarID = " + car.getId();
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}catch(SQLServerException e){
			// Foreign key error
			if(e.getErrorCode() == 547){
				car.setHidden(true);
				rc = updateCar(car);
				System.out.println("Car " + car.getId() + " is now hidden");
			}else{
				System.out.println("DBCar - deleteCar - Exception");
				e.printStackTrace();
			}
		}catch(Exception e){
			System.out.println("DBCar - deleteCar - Exception");
			e.printStackTrace();
		}
		
		return rc;
	}

	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso) {
		return singleWhere("Hidden = 0 AND RegNR = '" + regNr + "'", retAsso);
	}

	@Override
	public Car getCarByVin(String vin, boolean retAsso) {
		return singleWhere("Hidden = 0 AND VIN = '" + vin + "'", retAsso);
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
					car.setOwner(dbCustomer.getCustomer(car));
				}
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBCar - SingleWhere - Exception");
			e.printStackTrace();
		}
		
		return car;
	}
	
	private ArrayList<Car> miscWhere(String wQuery, boolean retAsso){
		ArrayList<Car> cars = new ArrayList<Car>();
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Car car = buildCar(rs);
				
				if(retAsso){
					IFDBCustomer dbCustomer = new DBCustomer();
					car.setOwner(dbCustomer.getCustomer(car));
				}
				
				cars.add(car);
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBCar - miscWhere - Exception");
			e.printStackTrace();
		}
		
		return cars;
	}

	/**
	 * @param rs
	 * @return
	 */
	private Car buildCar(ResultSet rs) {
		Car car = new Car();
		
		try{
			car.setBrand(rs.getString("Brand"));
			car.setId(rs.getInt("CarID"));
			car.setMileage(rs.getInt("Mileage"));
			car.setModel(rs.getString("Model"));
			car.setRegNr(rs.getString("RegNr"));
			car.setVin(rs.getString("VIN"));
			car.setYear(rs.getInt("Year"));
			car.setHidden(rs.getBoolean("Hidden"));
		}catch(Exception e){
			System.out.println("DBCar - BuildCar - Exception");
			e.printStackTrace();
		}
		
		return car;
	}

	/**
	 * @param wQuery
	 * @return
	 */
	private String buildQuery(String wQuery) {
		String query = "SELECT CarID, CustomerID, Brand, Model, RegNR, Mileage, VIN, Hidden, Year FROM CAR";
		
		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}
		
		return query;
	}

}
