package dbLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Setting;
import dbLayer.interfaceLayer.IFDBSettings;

/**
 * Class for DBSettings
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBSettings implements IFDBSettings {
	private Connection conn;
	
	public DBSettings(){
		conn = DBConnection.getInstance().getDBCon();
	}
	
	@Override
	public ArrayList<Setting> getAllSettings() {
		return miscWhere("");
	}
	
	@Override
	public Setting getSettingByKey(String key) {
		return singleWhere("SETTING = '" + key + "'");
	}
	
	@Override
	public int insertSetting(Setting setting) {
		int rc = -1;
		
		try{
			String query = "INSERT INTO SETTINGS (Setting, value) VALUES ('" + setting.getKey() + "', '" + setting.getValue() + "')";
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}catch(Exception e){
			System.out.println("DBSetting - insertSetting - Exception");
			e.printStackTrace();
		}
		
		return rc;
	}
	
	@Override
	public int updateSetting(Setting setting) {
		int rc = -1;
		
		try{
			String query = "UPDATE SETTINGS SET value = '" + setting.getValue() + "' WHERE Setting = '" + setting.getKey() + "'";
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}catch(Exception e){
			System.out.println("DBSettings - updateSetting");
			e.printStackTrace();
		}
		
		return rc;
	}
	
	private ArrayList<Setting> miscWhere(String wQuery){
		ArrayList<Setting> settings = new ArrayList<Setting>();
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Setting set = buildSetting(rs);
				
				settings.add(set);
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBSettings - miscWhere - Exception");
			e.printStackTrace();
		}
		
		return settings;
	}
	
	private Setting singleWhere(String wQuery){
		Setting set = null;
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				set = buildSetting(rs);
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBSetting - singleWhere - Exception");
			e.printStackTrace();
		}
		
		return set;
	}
	
	private Setting buildSetting(ResultSet rs){
		Setting set = new Setting();
		
		try{
			set.setKey(rs.getString("Setting"));
			set.setValue(rs.getString("Value"));
		}catch(Exception e){
			System.out.println("DBSettings - BuildSetting - Exception");
			e.printStackTrace();
		}
		
		return set;
	}
	
	private String buildQuery(String wQuery){
		String query = "SELECT Setting, Value FROM SETTINGS";
		
		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}
		
		return query;
	}
	
}
