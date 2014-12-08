package testLayer.dbLayer;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;

import org.junit.Before;
import org.junit.Test;

import dbLayer.DBCar;
import dbLayer.interfaceLayer.IFDBCar;

public class TestDBCar {
	private IFDBCar dbCar;

	@Before
	public void setUp() throws Exception {
		dbCar = new DBCar();
	}

	@Test
	public void test() {
		Car car = new Car();
		car.setOwner(new Customer(1));
		car.setRegNr("FA21984");
		int rc = dbCar.insertCar(car);
		
		if(rc == -1){
			fail("Insert Failed!");
		}else{
			System.out.println("Inserted Car: " + car.getId());
		}
		
		Car fetchedCar = dbCar.getCarByRegNr("FA21984", false);
		
		if(fetchedCar == null){
			fail("Single Select Failed");
		}else{
			System.out.println("Fetched Car: " + fetchedCar.getId());
		}
		
		
		
		ArrayList<Car> cars = dbCar.getCars(new Customer(1), false);
		
		
		if(cars == null || cars.size() < 1){
			fail("Misc Select Failed");
		}else{
			System.out.println("Fetched Cars: " + cars.size());
		}
		
		int deleteRc = dbCar.deleteCar(car);
		
		if(deleteRc == 0){
			fail("Delete failed");
		}else{
			System.out.println("Deleted car: " + car.getId());
		}
	}

}
