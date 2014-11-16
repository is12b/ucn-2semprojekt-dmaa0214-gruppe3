package ctrLayer;

import java.util.ArrayList;

import modelLayer.UnitType;
import ctrLayer.interfaceLayer.IFUnitTypeCtr;
import dbLayer.DBUnitType;
import dbLayer.exceptions.DBException;

/**
 * Controller for UnitTypes
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class UnitTypeCtr implements IFUnitTypeCtr {
	
	private DBUnitType dbUnit;

	/**
	 * Constructor for UnitTypeCtr.
	 *
	 */
	public UnitTypeCtr() {
		dbUnit = new DBUnitType();
	}
	
	@Override
	public UnitType getUnitType(String shortDesc) {
		return dbUnit.getUnitType(shortDesc);
	}

	@Override
	public ArrayList<UnitType> getUnitTypes() {
		return dbUnit.getUnitTypes();
	}

	@Override
	public UnitType createUnitType(String desc, String shortDesc,
			boolean decimalAllowed) throws DBException {
		UnitType unitType = new UnitType(shortDesc, desc, decimalAllowed);
		
		int rc = dbUnit.insertUnitType(unitType);
				
		return unitType;
	}

	@Override
	public void updateUnitType(UnitType unitType) throws DBException {
		int rc = dbUnit.updateUnitType(unitType);
	}

	@Override
	public void deleteUnitType(UnitType unitType) throws DBException {
		int rc = dbUnit.deleteUnitType(unitType);
	}

}
