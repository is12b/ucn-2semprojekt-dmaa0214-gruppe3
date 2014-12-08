package dbLayer.interfaceLayer;

import java.util.ArrayList;

import modelLayer.UnitType;
import exceptions.DBException;

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
	 * @return the unit type or null
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
	 * @throws {@link DBException} If there is an error in inserting
	 */
	public int insertUnitType(UnitType unitType) throws DBException;
	
	/**
	 * Update unit type in the database.
	 *
	 * @param unitType the unit type
	 * @return numbers of affected rows or -1 if it's fail
	 * @throws {@link DBException} If there is an error in updatering or none is updated
	 */
	public int updateUnitType(UnitType unitType) throws DBException;
	
	/**
	 * Delete unit type from the database..
	 *
	 * @param unitType the unit type
	 * @return numbers of affected rows or -1 if it's fail
	 * @throws {@link DBException} If there is an error in deleting or none is deleted
	 */
	public int deleteUnitType(UnitType unitType) throws DBException;

}
