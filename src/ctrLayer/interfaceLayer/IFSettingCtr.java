package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import dbLayer.interfaceLayer.Setting;

/**
 * Class for IFSettingCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFSettingCtr {
	
	public Setting getSettingByKey(String key);
	
	public ArrayList<Setting> getAllSettings();
	
	public void insertSetting(String key, String value);
	
	public void updateSetting(Setting setting);
}
