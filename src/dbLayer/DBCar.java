package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.CarExtra;
import modelLayer.Customer;
import modelLayer.Inspection;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import dbLayer.interfaceLayer.IFDBCar;
import dbLayer.interfaceLayer.IFDBCarExtra;
import dbLayer.interfaceLayer.IFDBCustomer;
import dbLayer.interfaceLayer.IFDBInspection;
import exceptions.DBException;
import exceptions.DBNotFoundException;

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
		return miscWhere("Hidden = 0 AND CustomerID = " + customer.getId(), retAsso);
	}

	@Override
	public Car getCar(int id, boolean retAsso) {
		return singleWhere("CarID = " + id, retAsso);
	}
	

	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso) {
		return singleWhere("Hidden = 0 AND RegNR = '" + regNr + "'", retAsso);
	}

	@Override
	public Car getCarByVin(String vin, boolean retAsso) {
		return singleWhere("Hidden = 0 AND VIN = '" + vin + "'", retAsso);
	}

	@Override
	public int insertCar(Car car, boolean selfControlTransaction) throws DBException { //TODO �ndre, s� hvis bilen eksisterer skal den gemmes? plus evt. advarsel i gui?
		int rc = -1;
		
		try{
			if (selfControlTransaction) {
				DBConnection.startTransaction();
			}
			
			String query = "INSERT INTO CAR"
					+ " (CustomerID, Brand, Model, RegNR, Mileage, VIN, Hidden, Year) VALUES "
					+ "(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setQueryTimeout(5);
			stmt.setInt(1, car.getOwner().getId());
			
			if(car.getBrand() == null || car.getBrand().isEmpty()){
				stmt.setNull(2, Types.VARCHAR);
			} else {
				stmt.setString(2, car.getBrand());
			}
			
			if(car.getModel() == null || car.getModel().isEmpty()){
				stmt.setNull(3, Types.VARCHAR);
			}else{
				stmt.setString(3, car.getModel());
			}
			
			if(car.getRegNr() == null || car.getRegNr().isEmpty()){
				stmt.setNull(4, Types.VARCHAR);
			}else{
				stmt.setString(4, car.getRegNr());
			}
			
			if(car.getMileage() == 0 || car.getMileage() == -1){
				stmt.setNull(5, Types.INTEGER);
			}else{
				stmt.setInt(5, car.getMileage());
			}
			
			if(car.getVin() == null || car.getVin().isEmpty()){
				stmt.setNull(6, Types.VARCHAR);
			}else{
				stmt.setString(6, car.getVin());
			}
			
			stmt.setBoolean(7, car.isHidden());
			
			if(car.getYear() == 0 || car.getYear() == -1){
				stmt.setNull(8, Types.INTEGER);
			}else{
				stmt.setInt(8, car.getYear());
			}
			
			rc = stmt.executeUpdate();
			
			ResultSet genRs = stmt.getGeneratedKeys();
			if(genRs.next()){
				car.setId(genRs.getInt(1));
			}
			
			stmt.close();
			
			insertExtra(car);
			insertInspections(car);
			
			if (selfControlTransaction) {
				DBConnection.commitTransaction();
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			if (selfControlTransaction) {
				DBConnection.rollBackTransaction();
			}
			throw new DBException("Bilen", e);
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Bilen", 1);
		}
		
		return rc;
	}

	private void insertInspections(Car car) throws DBException {
		ArrayList<Inspection> inspecs = car.getInspections();
		if (inspecs != null && inspecs.size() != 0) {
			IFDBInspection dbInspec = new DBInspection();
			dbInspec.insertInspections(inspecs, car);
		}
	}

	private void insertExtra(Car car) throws DBException {
		CarExtra ext = car.getExtra();
		if (car.getExtra() != null) {
			IFDBCarExtra dbExtra = new DBCarExtra();
			dbExtra.insertCarExtra(ext, car);
		}
	}

	@Override
	public int updateCar(Car car, boolean updateAsso) throws DBException {
		int rc = -1;
		
		try{
			DBConnection.startTransaction();
			
			String query = "UPDATE CAR SET "
					+ "CustomerID = ?, Brand = ?, Model = ?, RegNr = ?, "
					+ "Mileage = ?, VIN = ?, Hidden = ?, Year = ? WHERE CarID = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			stmt.setInt(1, car.getOwner().getId());
			if(car.getBrand() == null || car.getBrand().isEmpty()){
				stmt.setNull(2, Types.VARCHAR);
			} else {
				stmt.setString(2, car.getBrand());
			}
			
			if(car.getModel() == null || car.getModel().isEmpty()){
				stmt.setNull(3, Types.VARCHAR);
			}else{
				stmt.setString(3, car.getModel());
			}
			
			if(car.getRegNr() == null || car.getRegNr().isEmpty()){
				stmt.setNull(4, Types.VARCHAR);
			}else{
				stmt.setString(4, car.getRegNr());
			}
			
			if(car.getMileage() == 0){
				stmt.setNull(5, Types.INTEGER);
			}else{
				stmt.setInt(5, car.getMileage());
			}
			
			if(car.getVin() == null || car.getVin().isEmpty()){
				stmt.setNull(6, Types.VARCHAR);
			}else{
				stmt.setString(6, car.getVin());
			}
			

			stmt.setBoolean(7, car.isHidden());
			
			if(car.getYear() == 0){
				stmt.setNull(8, Types.INTEGER);
			}else{
				stmt.setInt(8, car.getYear());
			}
			
			stmt.setInt(9, car.getId());
			
			rc = stmt.executeUpdate();
			
			if(updateAsso){
				IFDBCarExtra dbCarExtra = new DBCarExtra();
				dbCarExtra.updateCarExtra(car.getExtra(), car);
			}
			
			stmt.close();
			
			
			DBConnection.commitTransaction();
		} catch (SQLException e) {
			DBConnection.rollBackTransaction();
			
			throw new DBException("Bilen", e);
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Bilen", 2);
		}
		
		
		return rc;
	}

	@Override
	public int deleteCar(Car car) throws DBException {
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
				rc = updateCar(car, false);
				System.out.println("Car " + car.getId() + " is now hidden");
			}else{
				System.out.println("DBCar - deleteCar - Exception");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DBException("Bilen", e);
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Bil", 3);
		}
		
		return rc;
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
					car.setOwner(dbCustomer.getCustomerByCar(car));
					
					IFDBInspection dbInspec = new DBInspection();
					car.setInspections(dbInspec.getInspections(car));
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
					car.setOwner(dbCustomer.getCustomerByCar(car));
					
					IFDBInspection dbInspec = new DBInspection();
					car.setInspections(dbInspec.getInspections(car));
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
			Customer customer = new Customer(rs.getInt("CustomerID"));
			car.setOwner(customer);
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
