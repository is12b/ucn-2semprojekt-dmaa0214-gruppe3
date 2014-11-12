package ctrLayer;

import ctrLayer.interfaceLayer.IFCarCtr;
import dbLayer.interfaceLayer.IFDBCar;
import modelLayer.Car;

public class CarCtr implements IFCarCtr {
	
	public CarCtr() {
		
	}

	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year) {
		Car newCar = new Car(brand, model, regNr, vin, mileage, year);
		//TODO IFDBCar dbCar = new IFDBCar();  
		//TODO IFDBCar.insertCar(newCar);
		return newCar;
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
