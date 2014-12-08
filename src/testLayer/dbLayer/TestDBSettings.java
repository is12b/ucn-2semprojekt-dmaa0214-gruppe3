package testLayer.dbLayer;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import modelLayer.Setting;

import org.junit.Before;
import org.junit.Test;

import dbLayer.DBSettings;
import dbLayer.exceptions.DBException;
import dbLayer.interfaceLayer.IFDBSettings;

/**
 * Class for TestDBSettings
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class TestDBSettings {
	private IFDBSettings dbSet;
	/**
	 * @throws java.lang.Exception
	 */
	
	/*
	@Before
	public void setUp() throws Exception {
		dbSet = new DBSettings();
	}
	
	@Test
	public void test() throws DBException {
		Setting setting = new Setting("INVOICE_NAME", "TestNameSetting");
		{
			int rc = dbSet.insertSetting(setting);
			
			if(rc == -1 || rc == 0){
				fail("InsertFailed");
			}
		}
		
		{
			setting.setValue("TestNameSetting1");
			int rc = dbSet.updateSetting(setting);
			
			if(rc == -1 || rc == 0){
				fail("updateFailed");
			}
		}
		
		{
			ArrayList<Setting> settings = dbSet.getAllSettings();
			
			if(settings == null || settings.size() == 0){
				fail("getAllFailed");
			}
		}
		
		{
			Setting set = dbSet.getSettingByKey("INVOICE_NAME");
			
			if(set == null || !set.getKey().equals("INVOICE_NAME")){
				fail("getSettingByKeyFailed");
			}
		}
		
	}
	*/
	
}
