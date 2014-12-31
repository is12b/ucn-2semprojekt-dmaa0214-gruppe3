package testLayer.dbLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;

import org.junit.Before;
import org.junit.Test;

import dbLayer.DBCar;
import dbLayer.interfaceLayer.IFDBCar;
import exceptions.DBException;

public class TestDBCar {
	private IFDBCar dbCar;

	@Before
	public void setUp() throws Exception {
		dbCar = new DBCar();
	}

	@Test
	public void test() throws DBException {
		boolean test = true;
		
		Car car = new Car();
		car.setOwner(new Customer(1));
		car.setRegNr("FA21984");
		int rc = dbCar.insertCar(car, true);
		
		if(rc == -1){
			test = false;
			System.out.println("Insert Failed");
		}else{
			test = true;
			System.out.println("Inserted Car: " + car.getId());
		}
		
		Car fetchedCar = dbCar.getCarByRegNr("FA21984", false);
		
		if(fetchedCar == null){
			test = false;
			System.out.println("GetCarByRegNr Failed");
		}else{
			test = true;
			System.out.println("Fetched Car: " + fetchedCar.getId());
		}
		
		
		
		ArrayList<Car> cars = dbCar.getCars(new Customer(1), false);
		
		
		if(cars == null || cars.size() < 1){
			test = false;
			System.out.println("GetCars Failed");
		}else{
			test = true;
			System.out.println("Fetched Cars: " + cars.size());
		}
		
		int deleteRc = dbCar.deleteCar(car);
		
		if(deleteRc == 0 || deleteRc == -1){
			test = false;
			System.out.println("Delete Failed");
		}else{
			test = true;
			System.out.println("Deleted car: " + car.getId());
		}
		
		assertTrue(test);
	}

}
