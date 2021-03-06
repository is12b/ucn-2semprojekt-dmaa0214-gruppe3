package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Inspection;

import dbLayer.interfaceLayer.IFDBInspection;
import exceptions.DBException;
import exceptions.DBNotFoundException;

/**
 * Class for DBCar
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBInspection implements IFDBInspection {
	private Connection conn;
	
	public DBInspection(){
		conn = DBConnection.getInstance().getDBCon();
	}
	

	@Override
	public ArrayList<Inspection> getInspections(Car car) {
		return miscWhere("CarID = " + car.getId());
	}
	
	public void insertInspections(ArrayList<Inspection> inspections, Car car) throws DBException{
		for(Inspection i : inspections){
			insertInspection(i, car);
		}
	}

	@Override
	public int insertInspection(Inspection inspec, Car car) throws DBException {
		int rc = -1;
		
		try{
			String query = "INSERT INTO Inspection" 
						 + " (CarID, Date, Result, Km, Url) VALUES " 
						 + "(?,?,?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			
			stmt.setInt(1, car.getId());
			addToStatement(2, inspec.getDate(), stmt);
			addToStatement(3, inspec.getResult(), stmt);
			addToStatement(4, inspec.getKm(), stmt);
			addToStatement(5, inspec.getUrl(), stmt);
			
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DBException("Inspection", e);
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Inspection", 1);
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

	private ArrayList<Inspection> miscWhere(String wQuery){
		ArrayList<Inspection> inspections = new ArrayList<Inspection>();
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Inspection inspec = buildInspection(rs);
				
				if(inspec != null){
					inspections.add(inspec);
				}
			}
			
			stmt.close();
		}catch(Exception e){ 
			System.out.println("DBInspection - miscWhere - Exception");
			e.printStackTrace();
		}
		
		return inspections;
	}

	/**
	 * @param rs
	 * @return
	 */
	private Inspection buildInspection(ResultSet rs) {
		Inspection inspec = new Inspection();
		
		try{
			inspec.setId(rs.getInt("InspectionID"));
			inspec.setDate(rs.getString("Date"));
			inspec.setResult(rs.getString("Result"));
			inspec.setKm(rs.getString("Km"));
			inspec.setUrl(rs.getString("Url"));
		}catch(Exception e){
			System.out.println("DBInspection - BuildInsection - Exception");
			e.printStackTrace();
		}
		
		return inspec;
	}

	/**
	 * @param wQuery
	 * @return
	 */
	private String buildQuery(String wQuery) {
		String query = "SELECT InspectionID, Date, Result, Km, Url FROM Inspection";
		
		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}
		
		return query;
	}
}
