package dbLayer.interfaceLayer;

import java.util.ArrayList;

import dbLayer.exceptions.DBException;
import modelLayer.Setting;

/**
 * Interface for DBSettings.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBSettings {

	public ArrayList<Setting> getAllSettings();
	
	public Setting getSettingByKey(String key);
	
	public int insertSetting(Setting setting) throws DBException;

	public int updateSetting(Setting setting) throws DBException;

}
