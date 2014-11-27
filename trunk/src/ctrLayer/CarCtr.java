package ctrLayer;

import ctrLayer.exceptionLayer.DeleteException;
import ctrLayer.exceptionLayer.InsertException;
import ctrLayer.exceptionLayer.UpdateException;
import ctrLayer.interfaceLayer.IFCarCtr;
import dbLayer.DBCar;
import dbLayer.interfaceLayer.IFDBCar;
import modelLayer.Car;
import modelLayer.Customer;

public class CarCtr implements IFCarCtr {
	
	public CarCtr() {
		
	}

	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year, Customer owner) throws InsertException{
		Car car = new Car(brand, model, regNr, vin, mileage, year, owner);
		
		IFDBCar dbCar = new DBCar();  
		if(dbCar.insertCar(car) == -1){
			throw new InsertException("bilen");
		}
		
		return car;
	}

	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso) throws NullPointerException{
		IFDBCar dbCar = new DBCar(); 
		
		Car car = dbCar.getCarByRegNr(regNr, retAsso);
		
		if(car == null){
			throw new NullPointerException("Bilen blev ikke fundet");
		}
		
		return car;
	}
	

	@Override
	public Car getCarByVin(String vin, boolean retAsso)
			throws NullPointerException {
		IFDBCar dbCar = new DBCar(); 
		
		Car car = dbCar.getCarByVin(vin, retAsso);
		
		if(car == null){
			throw new NullPointerException("Bilen blev ikke fundet");
		}
		
		return car;
	}

	@Override
	public void updateCar(Car car) throws UpdateException {
		IFDBCar dbCar = new DBCar();
		int rc = dbCar.updateCar(car);
		
		if(rc == -1){
			throw new UpdateException("Bilen", true);
		}else if(rc == 0){
			throw new UpdateException("Bilen", false);
		}
	}

	@Override
	public void deleteCar(Car car) throws DeleteException{
		IFDBCar dbCar = new DBCar();
		int rc = dbCar.deleteCar(car);
		
		if(rc == -1){
			throw new DeleteException("Bilen", true);
		}else if(rc == 0){
			throw new DeleteException("Bilen", false);
		}
	}	
	
}
