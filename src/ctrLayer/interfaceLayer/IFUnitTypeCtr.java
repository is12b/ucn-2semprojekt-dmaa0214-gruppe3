package ctrLayer.interfaceLayer;

import java.util.ArrayList;

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
	
	public UnitType createUnitType(String desc, String shortDesc, boolean decimalAllowed);
	
	public void updateUnitType(UnitType unitType);
	
	public void deleteUnitType(UnitType unitType);
}
