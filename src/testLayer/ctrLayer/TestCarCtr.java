package testLayer.ctrLayer;

import static org.junit.Assert.*;
import modelLayer.Car;
import modelLayer.Customer;

import org.junit.Before;
import org.junit.Test;

import ctrLayer.CarCtr;
import ctrLayer.exceptionLayer.DeleteException;
import ctrLayer.exceptionLayer.UpdateException;

public class TestCarCtr {

	CarCtr cCtr; 
	
	@Before
	public void setUp() throws Exception {
		cCtr = new CarCtr();
	}
	
	@Test
	public void testCRUD() {
		
		try{
			cCtr.getCarByRegNr("99999999999999999999999999", false);
			fail("No exception thrown - getCarByRegNr");
		}catch(Exception e){
			System.out.println("Exception Test - NullPointer " + e.getMessage());
		}

		try {
			Car car = new Car(9999999);
			car.setOwner(new Customer(1));
			cCtr.updateCar(car);
			fail("No exception thrown - Update");
		} catch (UpdateException e) {
			System.out.println("Exception Test - UpdateException " + e.getMessage());
		}
		
		try {
			cCtr.deleteCar(new Car(999999));
			fail("No exception thrown - Delete");
		} catch (DeleteException e) {
			
			System.out.println("Exception Test - DeleteException " + e.getMessage());
		}

	}

}
