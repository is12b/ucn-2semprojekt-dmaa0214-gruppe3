package ctrLayer;

import java.util.ArrayList;

import modelLayer.UnitType;
import ctrLayer.interfaceLayer.IFUnitTypeCtr;
import dbLayer.DBUnitType;

/**
 * Class for UnitTypeCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class UnitTypeCtr implements IFUnitTypeCtr {

	@Override
	public UnitType getUnitType(String shortDesc) {
		DBUnitType dbUnit = new DBUnitType();
		return dbUnit.getUnitType(shortDesc);
	}

	@Override
	public ArrayList<UnitType> getUnitTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnitType createUnitType(String desc, String shortDesc,
			boolean decimalAllowed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUnitType(UnitType unitType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUnitType(UnitType unitType) {
		// TODO Auto-generated method stub
		
	}

}
