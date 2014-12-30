package ctrLayer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelLayer.Car;
import modelLayer.Customer;
import ctrLayer.interfaceLayer.IFCarCtr;
import ctrLayer.interfaceLayer.IFCustomerCtr;
import dbLayer.DBCustomer;
import dbLayer.interfaceLayer.IFDBCustomer;
import exceptions.DBException;
import exceptions.DBNotFoundException;
import exceptions.ObjectNotExistException;
import exceptions.SubmitException;

public class CustomerCtr implements IFCustomerCtr {

	@Override
	public Customer updateCustomer(Customer customer, String name,
			String phoneNumber, String address, String city, int postalCode,
			int cvr, String email, boolean hidden)
			throws ObjectNotExistException, DBException {

		IFDBCustomer dbCus = new DBCustomer();
		Customer tempCus = null;
		try {
			tempCus = customer.clone();


			final boolean setCity = !city.trim().isEmpty();
			final boolean setName = !name.trim().isEmpty();
			final boolean setPhoneNumber = !phoneNumber.trim().isEmpty();
			final boolean setAddress = !address.trim().isEmpty();
			final boolean setPostalCode = postalCode != 0;
			final boolean setCvr = cvr != 0;
			final boolean setEmail = !email.trim().isEmpty();

			if(setCity) {
				customer.setCity(city);
			}

			if(setName) {
				customer.setName(name);
			}
			if(setPhoneNumber) {
				customer.setPhoneNumber(phoneNumber);
			}
			if(setAddress) {
				customer.setAddress(address);
			}
			if(setPostalCode) {
				customer.setPostalCode(postalCode);
			}
			if(setCvr) {
				customer.setCvr(cvr);
			}
			if(setEmail) {
				customer.setEmail(email);
			}

			customer.setHidden(hidden);

			dbCus.updateCustomer(customer);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Kunden blev ikke fundet");
		} catch(DBException e) {
			customer.setToClone(tempCus);
			throw new DBException(e.getMessage());
		} catch (CloneNotSupportedException e) {
			System.out.println("CustomerCtr: CloneNotSupportedException: "+ e.getMessage());
			//e.printStackTrace();
		}

		return customer;
	}

	@Override
	public void deleteCustomer(Customer customer) throws ObjectNotExistException, DBException {
		IFDBCustomer dbCus = new DBCustomer();
		try {
			dbCus.deleteCustomer(customer);
		} catch(DBNotFoundException e){
			throw new ObjectNotExistException("Kunden blev ikke fundet");
		}
	}

	@Override
	public Customer getCustomerByCvr(String cvr, boolean retAsso) throws ObjectNotExistException{
		IFDBCustomer dbCus = new DBCustomer();
		Customer foundCustomer = dbCus.getCustomerByCvr(cvr, retAsso);
		if(foundCustomer == null ){
			throw new ObjectNotExistException("Ingen kunder fundet");
		}
		return foundCustomer;
	}

	@Override
	public ArrayList<Customer> searchCustomersByName(String name, boolean retAsso) throws ObjectNotExistException{
		ArrayList<Customer> foundCustomers = null;
		IFDBCustomer dbCus = new DBCustomer();
		foundCustomers = dbCus.getCustomersByName(name, retAsso);
		if(foundCustomers == null || foundCustomers.size() == 0){
			throw new ObjectNotExistException("Ingen kunder fundet");
		}
		return foundCustomers;
	}

	@Override
	public ArrayList<Customer> searchCustomersByPhone(String phone, boolean retAsso) throws ObjectNotExistException{
		ArrayList<Customer> foundCustomers = null;
		IFDBCustomer dbCus = new DBCustomer();
		foundCustomers = dbCus.getCustomersByPhone(phone, retAsso);
		if(foundCustomers == null || foundCustomers.size() == 0){
			throw new ObjectNotExistException("Ingen kunder fundet");
		}
		return foundCustomers;
	}

	@Override
	public Customer getCustomerByRegNr(String regNr) throws ObjectNotExistException{
		IFDBCustomer dbCus = new DBCustomer();
		Customer cust = dbCus.getCustomerByRegNr(regNr);
		if(cust == null){
			throw new ObjectNotExistException("Ingen kunde fundet");
		}
		return cust;
	}

	
	// Iteration 2
	@Override
	public Car getCarData(String regOrVin) throws ObjectNotExistException {
		IFCarCtr cCtr = new CarCtr();
		return cCtr.getCarData(regOrVin);
	}

	@Override
	public Customer createCustomer(String name, String phoneNumber,
			String address, int postalCode, String city, int cvr, String email,
			boolean hidden, Car car, String carRegNr, String carVIN,
			String carBrand, String carModel, int carMileage, int carYear)
			throws SubmitException, DBException {
		
		name = getFixedString(name, "Kundenavnet");
		phoneNumber = getFixedString(phoneNumber, "Tlf-nummeret");
		address = getFixedString(address, "Adressen");
		postalCode = getFixedInt(postalCode, 1, "Postnummeret");
		email = getEmail(email);
		
		Customer cus = new Customer(name, phoneNumber, address, postalCode, city, cvr, email, hidden);
		
		car = getFixedCar(car, carRegNr, carVIN, carBrand, carModel, carMileage, carYear, cus);
		if (car != null) {
			System.out.println("bil tilføjet til kunde");
			cus.addCar(car);
		}
		
		IFDBCustomer dbCus = new DBCustomer();
		dbCus.insertCustomer(cus);
		
		return cus;
	}
	
	private Car getFixedCar(Car car, String regNr, String vin,
			String brand, String model, int mileage, int year, Customer owner) throws SubmitException {
		regNr = getFixedString(regNr);
		vin = getFixedString(vin);
		brand = getFixedString(brand);
		model = getFixedString(model);
		if (car != null) {
			if (regNr == null || vin == null) {
				throw new SubmitException("Hvis der skal oprettes en bil sammen med kunden"
						+ "\nskal RegNr. eller Stelnr. udfyldes");
			}
			car.setRegNr(regNr);
			car.setVin(vin);
			car.setBrand(brand);
			car.setModel(model);
			car.setMileage(mileage);
			car.setYear(year);
			
		} else {
			if (regNr != null || vin != null) {
				car = new Car(brand, model, regNr, vin, mileage, year, owner);
			} else {
				if (brand != null || model != null || mileage != -1 || year != -1) {
					throw new SubmitException("Hvis der skal oprettes en bil sammen med kunden"
							+ "\nskal RegNr. eller Stelnr. udfyldes");
				}
			}
		}
		
		return car;
	}
		
	private int getFixedInt(int i, int minValue, String reqDescription) throws SubmitException {
		if (i < minValue) {
			throw new SubmitException(reqDescription + " skal udfyldes");
		}
		return i;
	}
	
	private String getFixedString(String str) {
		String ret = null;
		try {
			ret = getFixedString(str, null);
		} catch (SubmitException e) {
			//Don't happen 
			//e.printStackTrace();
		}
		return ret;
	}
	
	private String getFixedString(String str, String reqDescription) throws SubmitException {
		String ret = null;
		if (str != null && !str.trim().isEmpty()) {
			ret = str;
		}
		if (reqDescription != null && ret == null) {
			throw new SubmitException(reqDescription + " skal udfyldes");
		}
		return ret;
	}

	private String getEmail(String email) throws SubmitException {
		if(!email.trim().isEmpty()) {
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				throw new SubmitException("Emailen er ikke angivet i korrekt format");
			}
		} else {
			email = null;
		}
		return email;
	}

}

