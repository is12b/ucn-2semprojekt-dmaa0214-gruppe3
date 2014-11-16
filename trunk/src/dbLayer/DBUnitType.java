package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.UnitType;
import dbLayer.exceptions.DBException;
import dbLayer.interfaceLayer.IFDBUnitType;

/**
 * Class for DBUnitType
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBUnitType implements IFDBUnitType {

	private Connection conn;

	public DBUnitType() {
		conn = DBConnection.getInstance().getDBCon();
	}
	
	@Override
	public UnitType getUnitType(String shortDesc) {
		return singleWhere("ShortDescription = '" + shortDesc + "'");
	}

	@Override
	public ArrayList<UnitType> getUnitTypes() {
		return miscWhere("");
	}


	@Override
	public int insertUnitType(UnitType unitType) throws DBException {
		int rc = -1;
		
		try {
			String query = "INSERT INTO UnitType"
					+ " (Description , ShortDescription, DecimalAllowed)"
					+ " VALUES (?,?,?)";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			stmt.setString(1, unitType.getDescription());
			stmt.setString(2, unitType.getShortDescription());
			stmt.setBoolean(3, unitType.isDecimalAllowed());
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			//System.out.println("DBUnitType: UnitType is not inserted correct");
			//e.printStackTrace();
			throw new DBException("Enhedstypen", e);
		}
		return rc;
	}

	@Override
	public int updateUnitType(UnitType unitType) throws DBException {
		int rc = -1;
		
		try {
			String query = "UPDATE UnitType SET Description = ?,"
					+ " ShortDescription = ?, DecimalAllowed = ?"
					+ " WHERE ShortDescription = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			
			stmt.setString(1, unitType.getDescription());
			stmt.setString(2, unitType.getShortDescription());
			stmt.setBoolean(3, unitType.isDecimalAllowed());
			String oldShortDesc = unitType.getOldShortDescription();
			if(oldShortDesc == null) {
				oldShortDesc = unitType.getShortDescription();
			}
			stmt.setString(4, oldShortDesc);
			rc = stmt.executeUpdate();
			
			unitType.setOldShortDescriptionToNull();
			
			stmt.close();
		} catch (SQLException e) {
			System.out.println("DBUnitType: Update UnitType faild");
			//e.printStackTrace();
			throw new DBException("Enhedstypen", e);
		}
		
		if (rc == 0) {
			throw new DBException("Enhedstypen", 2); //TODO Håndtering i UI? hvordan?
		}
		
		return rc;
	}

	@Override
	public int deleteUnitType(UnitType unitType) throws DBException {
		int rc = -1;
		try {
			String query = "DELETE FROM UnitType WHERE ShortDescription=?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			
			stmt.setString(1, unitType.getShortDescription());
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			//System.out.println("DBUnitType: Delete UnitType faild");
			//e.printStackTrace();
			throw new DBException("Enhedstypen", e);
		}
		
		
		
		return rc;
	}

	private UnitType singleWhere(String wQuery) {
		UnitType ut = null;
		
		try {
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				ut = buildUnitType(rs);
			}
			stmt.close();
			
		} catch (Exception e) {
			System.out.println("DBUnitType - exception : singleWhere");
			e.printStackTrace();
		}
		return ut;
	}
	
	private ArrayList<UnitType> miscWhere(String wQuery) {
		ArrayList<UnitType> retList = new ArrayList<UnitType>();
		
		try {
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				UnitType ut = buildUnitType(rs);
				if (ut != null) {
					retList.add(ut);
				}
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("DBUnitType - Exception : miscWhere");
			e.printStackTrace();
		}
		return retList;
	}
	
	private UnitType buildUnitType(ResultSet rs) {
		UnitType ut = null;
		try {
			ut = new UnitType(rs.getString("ShortDescription"));
			ut.setDescription(rs.getString("Description"));
			ut.setDecimalAllowed(rs.getBoolean("DecimalAllowed"));
		} catch (Exception e) {
			System.out.println("Error in building the UnitType object");
		}
		return ut;
	}

	private String buildQuery(String wQuery) {
		String query = "SELECT Description , ShortDescription, DecimalAllowed"
				+ " FROM UnitType";
		if(!wQuery.isEmpty())  {
			query += " WHERE " + wQuery;
		}
		query += " ORDER BY Description";
		return query;
	}
}
