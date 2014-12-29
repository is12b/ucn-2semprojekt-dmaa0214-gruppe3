package ctrLayer;

import java.util.ArrayList;

import dbLayer.DBInspection;
import dbLayer.interfaceLayer.IFDBInspection;
import exceptions.DBException;
import modelLayer.Car;
import modelLayer.Inspection;

/**
 * Class for InspectionCtr
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class InspectionCtr {

	/**
	 * Constructor for InspectionCtr objects.
	 *
	 */
	public InspectionCtr() {
		
	}
	
	public ArrayList<Inspection> getInspections(Car car){
		IFDBInspection dbInspec = new DBInspection();
		return dbInspec.getInspections(car);
	}
	
	public void insertInspection(String date, String result, Car car) throws DBException{
		Inspection inspec = new Inspection();
		inspec.setDate(date);
		inspec.setResult(result);
		
		IFDBInspection dbInspec = new DBInspection();
		dbInspec.insertInspection(inspec, car);
	}
	
	

}
