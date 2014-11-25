package ctrLayer;

import java.util.ArrayList;

import modelLayer.UnitType;
import ctrLayer.interfaceLayer.IFUnitTypeCtr;
import dbLayer.DBUnitType;
import dbLayer.exceptions.DBException;
import dbLayer.exceptions.DBNotFoundException;

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
	public void updateUnitType(UnitType unitType, String desc, String shortDesc, boolean decimalAllowed) throws NullPointerException, DBException {
		if (unitType != null) {
			System.out.println("før i ctr: " + unitType);
			try {
				
				unitType.setDescription(desc);
				unitType.setShortDescription(shortDesc);
				unitType.setDecimalAllowed(decimalAllowed);
				
				int rc = dbUnit.updateUnitType(unitType);
			} catch (DBNotFoundException e) {
				throw new DBNotFoundException(e.getMessage());
			} catch (DBException e) {
				throw new DBException(e.getMessage());
			}
			System.out.println("efter i ctr: " + unitType);
		} else {
			throw new NullPointerException("Enhedstypen er ikke angivet");
		}
	}

	@Override
	public void deleteUnitType(UnitType unitType) throws NullPointerException, DBException {
		if (unitType != null) {
			int rc = dbUnit.deleteUnitType(unitType);
		} else {
			throw new NullPointerException("Enhedstypen er ikke angivet");
		}
	}

}
