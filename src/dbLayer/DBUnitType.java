package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dbLayer.interfaceLayer.IFDBUnitType;
import modelLayer.UnitType;

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
	public int insertUnitType(UnitType unitType) {
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
		} catch (Exception e) {
			System.out.println("UnitType is not inserted correct");
			e.printStackTrace();
		}
		return rc;
	}

	@Override
	public int updateUnitType(UnitType unitType) {
		int rc = -1;
		
		try {
			//TODO ID? og where betingelser
			String query = "UPDATE UnitType SET Description = ?,"
					+ " ShortDescription = ?, DecimalAllowed = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, unitType.getDescription());
			stmt.setString(2, unitType.getShortDescription());
			stmt.setBoolean(3, unitType.isDecimalAllowed());
			
			stmt.close();
		} catch (Exception e) {
			System.out.println("Update UnitType faild");
			e.printStackTrace();
		}
		return rc;
	}

	@Override
	public int deleteUnitType(UnitType unitType) {
		int rc = -1;
		try {
			//TODO ID?
			String query = "DELETE FROM UnitType WHERE ShortDescription=?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, unitType.getShortDescription());
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Delete UnitType faild");
			e.printStackTrace();
		}
		return rc;
	}

	private UnitType singleWhere(String wQuery) {
		UnitType uT = null;
		
		try {
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				uT = buildUnitType(rs);
			}
			stmt.close();
			
		} catch (Exception e) {
			System.out.println("DBUnitType - exception : singleWhere");
			e.printStackTrace();
		}
		return uT;
	}
	
	private ArrayList<UnitType> miscWhere(String wQuery) {
		ArrayList<UnitType> retList = new ArrayList<UnitType>();
		
		try {
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				UnitType uT = buildUnitType(rs);
				if (uT != null) {
					retList.add(uT);
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
		UnitType uT = new UnitType();
		try {
			uT.setShortDescription(rs.getString("ShortDescription"));
			uT.setDescription(rs.getString("Description"));
			uT.setDecimalAllowed(rs.getBoolean("DecimalAllowed"));
		} catch (Exception e) {
			uT = null;
			System.out.println("Error in building the UnitType object");
		}
		return uT;
	}

	private String buildQuery(String wQuery) {
		String query = "SELECT Description , ShortDescription, DecimalAllowed "
				+ "FROM UnitType";
		if(!wQuery.isEmpty())  {
			query += " WHERE " + wQuery;
		}
		return query;
	}
}
