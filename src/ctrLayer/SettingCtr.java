package ctrLayer;

import java.util.ArrayList;

import ctrLayer.interfaceLayer.IFSettingCtr;
import dbLayer.DBSettings;
import dbLayer.interfaceLayer.IFDBSettings;
import dbLayer.interfaceLayer.Setting;

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
	public void insertSetting(String key, String value) {
		IFDBSettings sCtr = new DBSettings();
		sCtr.insertSetting(new Setting(key, value));
	}
	
	@Override
	public void updateSetting(Setting setting) {
		IFDBSettings sCtr = new DBSettings();
		sCtr.updateSetting(setting);
	}
	
}
