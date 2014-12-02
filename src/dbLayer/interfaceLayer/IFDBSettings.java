package dbLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.Setting;

/**
 * Interface for DBSettings.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBSettings {

	public ArrayList<Setting> getAllSettings();
	
	public Setting getSettingByKey(String key);
	
	public int insertSetting(Setting setting);

	public int updateSetting(Setting setting);

}
