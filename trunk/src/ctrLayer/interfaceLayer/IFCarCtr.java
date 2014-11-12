package ctrLayer.interfaceLayer;

import modelLayer.Car;

/**
 * Class for IFCarCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public interface IFCarCtr {
	
	public Car getCarByRegNr(String regNr, boolean retAsso);
	
	public Car createCar(String brand, String model, String regNr, String vin, int mileage, int year);
	
	public void updateCar(Car car);
	
	public void deleteCar(Car car);

}
