package testLayer.dbLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelLayer.Customer;

import org.junit.Test;

import dbLayer.DBCustomer;
import dbLayer.interfaceLayer.IFDBCustomer;

/**
 * Class for DBCustomerTest
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBCustomerTest {

	/**
	 * Test method for {@link dbLayer.DBCustomer#getCustomersByName(java.lang.String)}.
	 */
	@Test
	public void testGetCustomersByName() {
		IFDBCustomer dbCus = new DBCustomer();
		ArrayList<Customer> customers = dbCus.getCustomersByName("Lau", false);
		System.out.println(customers.get(0).getName());
		
	}

}
