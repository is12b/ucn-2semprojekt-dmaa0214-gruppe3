package ctrLayer;

import modelLayer.Car;
import modelLayer.Customer;
import ctrLayer.interfaceLayer.IFCarCtr;
import dbLayer.DBCar;
import dbLayer.interfaceLayer.IFDBCar;
import exceptions.DBException;
import exceptions.DBNotFoundException;
import exceptions.ObjectNotExistException;

public class CarCtr implements IFCarCtr {
	
	public CarCtr() {
		
	}

	@Override
	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year, Customer owner) throws DBException{
		Car car = new Car(brand, model, regNr, vin, mileage, year, owner);
		
		IFDBCar dbCar = new DBCar(); 
		dbCar.insertCar(car);

		return car;
	}

	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso) throws ObjectNotExistException{
		IFDBCar dbCar = new DBCar(); 
		
		Car car = dbCar.getCarByRegNr(regNr, retAsso);
		
		if(car == null){
			throw new ObjectNotExistException("Bilen blev ikke fundet");
		}
		
		return car;
	}
	

	@Override
	public Car getCarByVin(String vin, boolean retAsso)
			throws ObjectNotExistException {
		IFDBCar dbCar = new DBCar(); 
		
		Car car = dbCar.getCarByVin(vin, retAsso);
		
		if(car == null){
			throw new ObjectNotExistException("Bilen blev ikke fundet");
		}
		
		return car;
	}

	@Override
	public void updateCar(Car car) throws ObjectNotExistException, DBException {
		IFDBCar dbCar = new DBCar();
		
		try {
			dbCar.updateCar(car);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Bilen blev ikke fundet");
		}
	}

	@Override
	public void deleteCar(Car car) throws ObjectNotExistException, DBException {
		IFDBCar dbCar = new DBCar();
		try {
			dbCar.deleteCar(car);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Bilen blev ikke fundet");
		}
	}	
	
}
