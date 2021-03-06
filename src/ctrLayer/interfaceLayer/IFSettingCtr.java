package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import exceptions.DBException;
import exceptions.ObjectNotExistException;
import modelLayer.Setting;

/**
 * Class for IFSettingCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFSettingCtr {
	
	public Setting getSettingByKey(String key);
	
	public ArrayList<Setting> getAllSettings();
	
	public void updateSetting(Setting setting) throws ObjectNotExistException, DBException;
}
