package testLayer.dbLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelLayer.UnitType;

import org.junit.Before;
import org.junit.Test;

import dbLayer.DBUnitType;
import dbLayer.exceptions.DBException;
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
		UnitType ut = new UnitType("Utænkelig");
		ut.setDescription("Utænkelig test enhed");
		ut.setDecimalAllowed(true);
		try {
			
			int insert = dbUT.insertUnitType(ut);
			
			assertTrue(insert == 1);
			System.out.println("insertTest: " + insert);
			
			ut.setDescription("Umuligt at forestille sig - enhed");
			ut.setDecimalAllowed(false);
			ut.setShortDescription("Umuligt");
			
			int update = dbUT.updateUnitType(ut);
			System.out.println("updateValue: " + update);
			assertTrue(update == 1);
			
			int delete = dbUT.deleteUnitType(ut);
			assertTrue(delete == 1);
			System.out.println("deleteTest: " + delete);
		
		} catch (DBException e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
}
