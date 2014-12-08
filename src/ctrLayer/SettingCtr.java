package ctrLayer;

import java.util.ArrayList;

import modelLayer.Setting;
import ctrLayer.interfaceLayer.IFSettingCtr;
import dbLayer.DBSettings;
import dbLayer.interfaceLayer.IFDBSettings;
import exceptions.DBException;
import exceptions.DBNotFoundException;
import exceptions.ObjectNotExistException;

/**
 * Class for SettingCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class SettingCtr implements IFSettingCtr {
	
	@Override
	public Setting getSettingByKey(String key) {
		IFDBSettings sCtr = new DBSettings();
		Setting set = sCtr.getSettingByKey(key);
		
		if(set == null){
			throw new NullPointerException("Nøglen findes ikke");
		}
		
		return set;
	}
	
	@Override
	public ArrayList<Setting> getAllSettings() {
		IFDBSettings sCtr = new DBSettings();
		return sCtr.getAllSettings();
	}
	
	@Override
	public void updateSetting(Setting setting) throws ObjectNotExistException, DBException {
		IFDBSettings sCtr = new DBSettings();
		try {
			sCtr.updateSetting(setting);
		} catch (DBNotFoundException e) {
			throw new ObjectNotExistException(e.getMessage());
		}		
	}
	
}
