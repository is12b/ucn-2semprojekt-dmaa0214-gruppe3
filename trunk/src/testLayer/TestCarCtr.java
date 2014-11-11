package testLayer;

import static org.junit.Assert.*;
import modelLayer.Car;

import org.junit.Before;
import org.junit.Test;

import ctrLayer.CarCtr;

public class TestCarCtr {

	CarCtr cCtr; 
	
	@Before
	public void setUp() throws Exception {
		cCtr = new CarCtr();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public void testCRUD() {
		
		// Create
		//TODO cCtr.createCar();
		
		// Read
		//TODO Car car1 = cCtr.getCar();
		
		// Update
		//TODO cCtr.updateCar(car1, );
		
		// Delete
		//TODO car1.setHidden(true);
		
		
		
	}

}
