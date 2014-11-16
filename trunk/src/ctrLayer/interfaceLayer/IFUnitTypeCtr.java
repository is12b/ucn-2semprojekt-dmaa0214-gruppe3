package ctrLayer.interfaceLayer;

import java.util.ArrayList;

import dbLayer.exceptions.DBException;
import modelLayer.UnitType;

/**
 * Class for UnitTypeCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFUnitTypeCtr {

	public UnitType getUnitType(String shortDesc);
	
	public ArrayList<UnitType> getUnitTypes();
	
	public UnitType createUnitType(String desc, String shortDesc, boolean decimalAllowed) throws DBException;
	
	public void updateUnitType(UnitType unitType) throws DBException;
	
	public void deleteUnitType(UnitType unitType) throws DBException;
}
