package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelLayer.Car;
import modelLayer.CarExtra;
import dbLayer.interfaceLayer.IFDBCarExtra;
import exceptions.DBException;
import exceptions.DBNotFoundException;

/**
 * Class for DBCar
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBCarExtra implements IFDBCarExtra {
	private Connection conn;
	
	public DBCarExtra(){
		conn = DBConnection.getInstance().getDBCon();
	}
	

	@Override
	public CarExtra getCarExtra(Car car) {
		return singleWhere("CarID = " + car.getId());
	}

	@Override
	public int insertCarExtra(CarExtra ext, Car car) throws DBException {
		int rc = -1;
		
		try{
			String query = "INSERT INTO CarExtra" 
						 + " (CarID, carType, LatestChangedVehicle, firstRegDate, carUse, latestChangeReg, status, tecTotalWeight"
						 + ", totalWeight, posOfChassisNumber, inspectionFreq, calInspectionDate) VALUES " 
						 + "(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			stmt.setInt(1, car.getId());
			
			addToStatement(2, ext.getType(), stmt);
			addToStatement(3, ext.getLatestChangeVehicle(), stmt);
			addToStatement(4, ext.getFirstRegDate(), stmt);
			addToStatement(5, ext.getUse(), stmt);
			addToStatement(6, ext.getLatestChangeReg(), stmt);
			addToStatement(7, ext.getStatus(), stmt);
			addToStatement(8, ext.getTecTotalWeight(), stmt);
			addToStatement(9, ext.getTotalWeight(), stmt);
			addToStatement(10, ext.getPosOfChassisNumber(), stmt);
			addToStatement(11, ext.getInspectionFreq(), stmt);
			addToStatement(12, ext.getCalInspectionDate(), stmt);
			
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DBException("Bil", e);
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Bil", 1);
		}
		
		return rc;
	}

	@Override
	public int updateCarExtra(CarExtra ext, Car car) throws DBException {
		int rc = -1;
		
		try{
			String query = "UPDATE CarExtra SET " 
						 + "carType = ?, LatestChangedVehicle = ?, firstRegDate = ?, carUse = ?, latestChangeReg = ?, status = ?, tecTotalWeight = ?"
						 + ", totalWeight = ?, posOfChassisNumber = ?, inspectionFreq = ?, calInspectionDate = ? WHERE CarID = ? ";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			
			
			addToStatement(1, ext.getType(), stmt);
			addToStatement(2, ext.getLatestChangeVehicle(), stmt);
			addToStatement(3, ext.getFirstRegDate(), stmt);
			addToStatement(4, ext.getUse(), stmt);
			addToStatement(5, ext.getLatestChangeReg(), stmt);
			addToStatement(6, ext.getStatus(), stmt);
			addToStatement(7, ext.getTecTotalWeight(), stmt);
			addToStatement(8, ext.getTotalWeight(), stmt);
			addToStatement(9, ext.getPosOfChassisNumber(), stmt);
			addToStatement(10, ext.getInspectionFreq(), stmt);
			addToStatement(11, ext.getCalInspectionDate(), stmt);
			stmt.setInt(12, car.getId());
			
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DBException("Bil", e);
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Bil", 2);
		}
		
		return rc;
	}
	
	private void addToStatement(int index, String value, PreparedStatement stmt) throws SQLException{
		if(value == null || value.isEmpty()){
			stmt.setNull(index, java.sql.Types.VARCHAR);
		}else{
			stmt.setString(index, value);
		}
		
	}
	
	private CarExtra singleWhere(String wQuery){
		CarExtra ext = null;
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				ext = buildCarExtra(rs);
			}
			
			stmt.close();
		}catch(Exception e){ 
			System.out.println("DBCarExtra - SingleWhere - Exception");
			e.printStackTrace();
		}
		
		return ext;
	}

	/**
	 * @param rs
	 * @return
	 */
	private CarExtra buildCarExtra(ResultSet rs) {
		CarExtra ext = new CarExtra();
		
		try{
			ext.setType(rs.getString("CarType"));
			ext.setLatestChangeVehicle(rs.getString("LatestChangedVehicle"));
			ext.setFirstRegDate(rs.getString("firstRegDate"));
			ext.setUse(rs.getString("carUse"));
			ext.setLatestChangeReg(rs.getString("latestChangeReg"));
			ext.setStatus(rs.getString("status"));
			ext.setTecTotalWeight(rs.getString("tecTotalWeight"));
			ext.setTotalWeight(rs.getString("totalWeight"));
			ext.setPosOfChassisNumber(rs.getString("posOfChassisNumber"));
			ext.setInspectionFreq(rs.getString("inspectionFreq"));
			ext.setCalInspectionDate(rs.getString("calInspectionDate"));
		}catch(Exception e){
			System.out.println("DBCar - BuildCar - Exception");
			e.printStackTrace();
		}
		
		return ext;
	}

	/**
	 * @param wQuery
	 * @return
	 */
	private String buildQuery(String wQuery) {
		String query = "SELECT carType, LatestChangedVehicle, firstRegDate, carUse, latestChangeReg, status, tecTotalWeight, " 
				     + "totalWeight, posOfChassisNumber, inspectionFreq, calInspectionDate FROM CAREXTRA";
		
		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}
		
		return query;
	}
}
