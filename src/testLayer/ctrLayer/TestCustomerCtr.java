package testLayer.ctrLayer;

import static org.junit.Assert.*;
import modelLayer.Customer;

import org.junit.Before;
import org.junit.Test;

import ctrLayer.CustomerCtr;

public class TestCustomerCtr {
	
	CustomerCtr cCtr;

	@Before
	public void setUp() throws Exception {
		cCtr = new CustomerCtr();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public void testCRUD() {
	
		// Create
		//TODO cCtr.createCustomer();
		
		// Read
		//TODO Customer cus1 = cCtr.getCustomer();
		
		// Update
		//TODO cCtr.updateCustomer(cus1, );
		
		// Delete
		//TODO cus1.setHidden(true);
		
	}

}
