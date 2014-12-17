package testLayer.ctrLayer;

import modelLayer.Car;
import modelLayer.Customer;

import org.junit.Before;
import org.junit.Test;

import ctrLayer.CarCtr;
import exceptions.DBException;
import exceptions.ObjectNotExistException;

public class TestCarCtr {

	CarCtr cCtr; 
	
	@Before
	public void setUp() throws Exception {
		cCtr = new CarCtr();
	}
	
	@Test(expected=Exception.class)
	public void testRegNr() throws ObjectNotExistException{
		cCtr.getCarByRegNr("99999999999999999999999999", false);
	}
	
	@Test(expected=Exception.class)
	public void testUpdateCar() throws DBException, ObjectNotExistException{
		Car car = new Car(9999999);
		car.setOwner(new Customer(1));
		cCtr.updateCar(car);
	}
	
	@Test(expected=Exception.class)
	public void testDeleteCar() throws DBException, ObjectNotExistException{
		cCtr.deleteCar(new Car(999999));
	}
	

}
