package dbLayer;

import java.util.ArrayList;

import modelLayer.UnitType;

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
	
	/**
	 * Insert unit type into the database.
	 *
	 * @param unitType the unit type
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int insertUnitType(UnitType unitType);
	
	/**
	 * Update unit type in the database.
	 *
	 * @param unitType the unit type
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int updateUnitType(UnitType unitType);
	
	/**
	 * Delete unit type from the database..
	 *
	 * @param unitType the unit type
	 * @return numbers of affected rows or -1 if it's fail
	 */
	public int deleteUnitType(UnitType unitType);
}
