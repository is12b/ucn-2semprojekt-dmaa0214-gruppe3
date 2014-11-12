package ctrLayer;

import dbLayer.interfaceLayer.IFDBCar;
import modelLayer.Car;

public class CarCtr {
	
	public CarCtr() {
		
	}

	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year) {
		Car newCar = new Car(brand, model, regNr, vin, mileage, year);
		//TODO IFDBCar dbCar = new IFDBCar();  
		//TODO IFDBCar.insertCar(newCar);
		return newCar;
	}
	
	
}
