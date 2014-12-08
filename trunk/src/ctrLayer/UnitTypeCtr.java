package ctrLayer;

import java.util.ArrayList;

import modelLayer.UnitType;
import ctrLayer.exceptionLayer.ObjectNotExistException;
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
	public ArrayList<UnitType> getUnitTypes() {
		return dbUnit.getUnitTypes();
	}

	@Override
	public UnitType createUnitType(String desc, String shortDesc,
			boolean decimalAllowed) throws DBException {
		UnitType unitType = new UnitType(shortDesc, desc, decimalAllowed);
		
		dbUnit.insertUnitType(unitType);
				
		return unitType;
	}
	
	@Override
	public void updateUnitType(UnitType unitType, String desc, String shortDesc, boolean decimalAllowed) throws DBException, ObjectNotExistException {
		if (unitType != null) {
			//System.out.println("før i ctr: " + unitType);
			UnitType tempObj = null;
			try {
				tempObj = unitType.clone();
				
				unitType.setDescription(desc);
				unitType.setShortDescription(shortDesc);
				unitType.setDecimalAllowed(decimalAllowed);
				
				dbUnit.updateUnitType(unitType);
			} catch (CloneNotSupportedException e) {
				System.out.println("UnitType: CloneNotSupportedException: "+ e.getMessage());
				//e.printStackTrace();
			} catch (DBNotFoundException e) {
				unitType.setToClone(tempObj);
				throw new ObjectNotExistException(e.getMessage());
			} catch (DBException e) {
				unitType.setToClone(tempObj);
				throw new DBException(e.getMessage());
			}
			//System.out.println("efter i ctr: " + unitType);
		} else {
			throw new ObjectNotExistException("Enhedstypen er ikke angivet");
		}
	}

	@Override
	public void deleteUnitType(UnitType unitType) throws DBException, ObjectNotExistException {
		if (unitType != null) {
			try {
				dbUnit.deleteUnitType(unitType);
			} catch (DBNotFoundException e) {
				throw new ObjectNotExistException(e.getMessage());
			}
		} else {
			throw new ObjectNotExistException("Enhedstypen er ikke angivet");
		}
	}

}
