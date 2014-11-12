package testLayer.dbLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelLayer.UnitType;

import org.junit.Before;
import org.junit.Test;

import dbLayer.DBUnitType;
import dbLayer.interfaceLayer.IFDBUnitType;

/**
 * Class for TestDBUnitType
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class TestDBUnitType {

	private IFDBUnitType dbUT;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dbUT = new DBUnitType();
	}

	/**
	 * Test method for {@link dbLayer.DBUnitType#getUnitType(java.lang.String)}.
	 */
	@Test
	public void testGetUnitType() {
		UnitType ut = dbUT.getUnitType("L");
		assertNotNull(ut);
	}

	/**
	 * Test method for {@link dbLayer.DBUnitType#getUnitTypes()}.
	 */
	@Test
	public void testGetUnitTypes() {
		ArrayList<UnitType> list = dbUT.getUnitTypes();
		assertNotNull(list);
		assertTrue(list.size() != 0);
	}

	/**
	 * Test method for: 
	 * {@link dbLayer.DBUnitType#insertUnitType(modelLayer.UnitType)} 
	 * {@link dbLayer.DBUnitType#updateUnitType(modelLayer.UnitType)}
	 * {@link dbLayer.DBUnitType#deleteUnitType(modelLayer.UnitType)}
	 */
	@Test
	public void testInsertUpdateDeleteUnitType() {
		//TODO
		fail("Not yet implemented");
	}

}
