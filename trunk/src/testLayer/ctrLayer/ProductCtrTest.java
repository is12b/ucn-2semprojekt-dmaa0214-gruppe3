package testLayer.ctrLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;

import org.junit.Before;
import org.junit.Test;

import ctrLayer.ProductCtr;
import ctrLayer.UnitTypeCtr;
import ctrLayer.exceptionLayer.ObjectNotExistException;
import dbLayer.exceptions.DBException;

/**
 * Class for ProductCtrTest
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class ProductCtrTest {

	ProductCtr pCtr;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pCtr = new ProductCtr();
	}

	/**
	 * Test method for {@link ctrLayer.ProductCtr#getProductByID(int)}.
	 * @throws ObjectNotExistException 
	 * 
	 */
	@Test
	public void testGetProductByID() throws ObjectNotExistException {
		Product product = pCtr.getProductByID(1);
			System.out.println(product.getName());	
	}

	/**
	 * Test method for {@link ctrLayer.ProductCtr#searchProductsByName(java.lang.String)}.
	 * @throws ObjectNotExistException 
	 */
	@Test
	public void testSearchProductsByName() throws ObjectNotExistException {
		ArrayList<Product> products = pCtr.searchProductsByName("for");
		for(Product p : products) {
			System.out.println(p.getName());
		}	
	}

	/**
	 * Test method for {@link ctrLayer.ProductCtr#searchProductsByItemNumber(java.lang.String)}.
	 * @throws ObjectNotExistException 
	 */
	@Test
	public void testSearchProductsByItemNumber() throws ObjectNotExistException {
		ArrayList<Product> products1 = pCtr.searchProductsByItemNumber("WQ");
		for(Product p : products1) {
			System.out.println(p.getItemNumber());
		}
	}

	@Test
	public void testCreateDelete() throws DBException, ObjectNotExistException {
		UnitTypeCtr uCtr = new UnitTypeCtr();
		UnitType unitType = uCtr.getUnitType("L");
		Product createdProduct = pCtr.createProduct("Brand", "Name", "Description", "itemNumber", 111.1, unitType);
		System.out.println(createdProduct.getName());

		pCtr.deleteProduct(createdProduct);
		assertNotNull(createdProduct);

	}
}