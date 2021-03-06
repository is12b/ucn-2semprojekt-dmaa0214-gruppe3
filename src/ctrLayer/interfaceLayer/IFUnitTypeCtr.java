package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.UnitType;
import exceptions.DBException;
import exceptions.ObjectNotExistException;

/**
 * Controller for UnitTypeCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFUnitTypeCtr {
	
	public ArrayList<UnitType> getUnitTypes();
	
	public UnitType createUnitType(String desc, String shortDesc, boolean decimalAllowed) throws DBException;
	
	public void updateUnitType(UnitType unitType, String desc, String shortDesc, boolean decimalAllowed) throws DBException, ObjectNotExistException;
	
	public void deleteUnitType(UnitType unitType) throws DBException, ObjectNotExistException;
}
