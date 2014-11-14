package ctrLayer;

import ctrLayer.exceptionLayer.InsertException;
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
			throw new InsertException();
		}
		
		return car;
	}

	@Override
	public Car getCarByRegNr(String regNr, boolean retAsso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCar(Car car) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCar(Car car) {
		// TODO Auto-generated method stub
		
	}
	
	
}
