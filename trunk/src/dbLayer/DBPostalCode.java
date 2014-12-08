package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import dbLayer.exceptions.DBException;
import dbLayer.interfaceLayer.IFDBPostalcode;

/**
 * Class for DBPostalCode
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBPostalCode implements IFDBPostalcode { // NO_UCD (use default)
	private Connection conn;

	public DBPostalCode(){
		conn = DBConnection.getInstance().getDBCon();
	}

	@Override
	public String getCity(int postCode) {
		return singleWhere("PostalCode = " + postCode);
	}

	@Override
	public int insertPostalCode(int postCode, String city) throws DBException {
		int rc = -1;

		try{
			String query = "INSERT INTO PostCode (City, PostalCode) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			stmt.setString(1, city);
			stmt.setInt(2, postCode);
			rc = stmt.executeUpdate();
			stmt.close();
		}catch(SQLServerException e){			
			//Duplicate key Error code
			if(e.getErrorCode() == 2627){
				rc = updatePostalCode(postCode, city);
			}else{
				e.printStackTrace();
			}
		}catch(SQLException e){
			System.out.println("DBPostalCode - insertPostalCode - Exception");
			e.printStackTrace();
			throw new DBException("Kunden", e);
		}
		
		return rc;
	}

	/**
	 * @param postCode
	 * @param city
	 */
	private int updatePostalCode(int postCode, String city) {
		int rc = -1;

		try{
			String query = "UPDATE PostCode SET City = ? WHERE PostalCode = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setInt(2, postCode);
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate();
			stmt.close();
		}catch(Exception e){
			System.out.println("DBPostalCode - updatePostalCode - Exception");
			e.printStackTrace();
		}

		return rc;
	}

	/**
	@Override
	public int deletePostalCode(int postCode) {
		int rc = -1;

		try{
			String query = "DELETE FROM POSTCODE WHERE PostalCode = " + postCode;
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}catch(Exception e){
			System.out.println("DBPostalCode - deletePostalCode - Exception");
			e.printStackTrace();
		}

		return rc;
	}
	 */

	private String singleWhere(String wQuery){
		String result = "";

		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				result = rs.getString("City");
			}

			stmt.close();
		}catch(Exception e){
			System.out.println("DBPostalCode - singleWhere - Exception");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @param wQuery
	 * @return
	 */
	private String buildQuery(String wQuery) {
		String query = "SELECT City FROM PostCode";

		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}

		return query;
	}

}
