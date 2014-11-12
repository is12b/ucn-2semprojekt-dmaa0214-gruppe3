package dbLayer;

import java.util.ArrayList;

import modelLayer.UnitType;

// TODO: Auto-generated Javadoc
/**
 * Class for IFDBUnitType.
 *
 * @author Group 3, dmaa0214, UCN
 */
public interface IFDBUnitType {
	
	/**
	 * Gets the unit type.
	 *
	 * @param shortDesc the short desc
	 * @return the unit type
	 */
	public UnitType getUnitType(String shortDesc);
	
	/**
	 * Gets the unit types.
	 *
	 * @return the unit types
	 */
	public ArrayList<UnitType> getUnitTypes();
}
