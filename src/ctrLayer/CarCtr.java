package ctrLayer;

import java.sql.SQLException;

import modelLayer.Car;
import modelLayer.CarExtra;
import modelLayer.Customer;
import ctrLayer.interfaceLayer.IFCarCtr;
import dbLayer.DBCar;
import dbLayer.DBCarExtra;
import dbLayer.DBInspection;
import dbLayer.interfaceLayer.IFDBCar;
import dbLayer.interfaceLayer.IFDBCarExtra;
import dbLayer.interfaceLayer.IFDBInspection;
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
	public void updateCar(Car car) throws ObjectNotExistException, DBException { // NO_UCD (test only)
		IFDBCar dbCar = new DBCar();
		
		try {
			dbCar.updateCar(car);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Bilen blev ikke fundet");
		}
	}

	@Override
	public void deleteCar(Car car) throws ObjectNotExistException, DBException { // NO_UCD (test only)
		IFDBCar dbCar = new DBCar();
		try {
			dbCar.deleteCar(car);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Bilen blev ikke fundet");
		}
	}
	
	public CarExtra getCarExtra(Car car) throws ObjectNotExistException{
		IFDBCarExtra dbExtra = new DBCarExtra();
		CarExtra ext = dbExtra.getCarExtra(car);
		
		if(ext == null){
			ext = updateExtra(car);
		}
		
		car.setExtra(ext);
		
		return ext;
	}
	
	public CarExtra updateExtra(Car car) throws ObjectNotExistException{
		CarScraper scraper = new CarScraper();
		CarExtra ext = null;
		try {
			ext = scraper.getExtra(car);
			if(ext != null){
				IFDBCarExtra dbExtra = new DBCarExtra();
				dbExtra.insertCarExtra(ext, car);
				
				if(car.getInspections() != null || car.getInspections().size() > 0){
					IFDBInspection dbInspec = new DBInspection();
					dbInspec.insertInspections(car.getInspections(), car);
				}
			}
		} catch(Exception e){
			throw new ObjectNotExistException("Ekstra information om bilen blev ikke fundet");
		}
		
		return ext;
	}
	
	public void updateCarExtra(CarExtra ext, Car car, String carType,
			String latestChangeVehicle, String firstRegDate, String carUse,
			String latestChangeReg, String status, String tecTotalWeight,
			String totalWeight, String posOfChassisNumber,
			String inspectionFreq, String callInspectionDate) {
		
		CarExtra clone = null;
		
		try {
			clone = ext.clone();

			ext.setType(carType);
			ext.setLatestChangeVehicle(latestChangeVehicle);
			ext.setFirstRegDate(firstRegDate);
			ext.setUse(carUse);
			ext.setLatestChangeReg(latestChangeReg);
			ext.setStatus(status);
			ext.setTecTotalWeight(tecTotalWeight);
			ext.setTotalWeight(totalWeight);
			ext.setPosOfChassisNumber(posOfChassisNumber);
			ext.setInspectionFreq(inspectionFreq);
			ext.setCalInspectionDate(callInspectionDate);
			
			IFDBCarExtra dbExtra = new DBCarExtra();
			dbExtra.updateCarExtra(ext, car);

		} catch (Exception e) {
			ext.setToClone(clone);
		}
	}
	
}
