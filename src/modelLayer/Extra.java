package modelLayer;

import java.util.ArrayList;

public class Extra {
	//Vehicle - Køretøj
	private String type; // Art
	private String latestChangeVehicle; // Seneste ændring
	
	//Registration - Registrerings­forhold
	private String firstRegDate; // Første registrerings­dato
	private String use; // Anvendelse
	private String latestChangeReg; // Seneste ændring
	private String status; // Status
	
	//Weight - Vægt
	private String tecTotalWeight; // Teknisk totalvægt
	private String totalWeight; // Totalvægt
	//private String ownWeight; // Egenvægt
	//private String couplingDevice; // Tilkoblingsanordning
	//private String weightOfTrailerWithBrakes; // Vægt af påhængskøretøj med bremser
	//private String weightOfTrailerWithoutBrakes; // Vægt af påhængskøretøj uden bremser
	
	//Motor
	//private String propellant; // Drivkraft
	//private String fuelConsumption; // Brændstofforbrug
	
	//Body - Karrosseri
	//private String bodyType; // Karrosseritype
	private String posOfChassisNumber; // Anbringelse af stelnummer
	
	//inspection - Syn
	private String inspectionFreq; // Frekvens for periodisk syn
	private String calInspectionDate; // Beregnet dato for næste indkaldelse til periodisk syn
	private ArrayList<Inspection> inspections;
	
	//Insurance - Forsikring
	//private boolean isInsured = false;
	//private String insuranceComp; // forsikringsselskab
	//private String insuranceStatus; // forsikringsstatus
	//private String insuranceCreated; // oprettelsedato for forsikring
	
	//Permissions - Dispensationer og tilladelser
	//private HashSet<String> permissions;
	
	public Extra() {
		inspections = new ArrayList<Inspection>();
		//permissions = new HashSet<String>();
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the latestChangeVehicle
	 */
	public String getLatestChangeVehicle() {
		return latestChangeVehicle;
	}
	
	/**
	 * @param latestChangeVehicle the latestChangeVehicle to set
	 */
	public void setLatestChangeVehicle(String latestChangeVehicle) {
		this.latestChangeVehicle = latestChangeVehicle;
	}
	
	/**
	 * @return the firstRegDate
	 */
	public String getFirstRegDate() {
		return firstRegDate;
	}
	
	/**
	 * @param firstRegDate the firstRegDate to set
	 */
	public void setFirstRegDate(String firstRegDate) {
		this.firstRegDate = firstRegDate;
	}
	
	/**
	 * @return the use
	 */
	public String getUse() {
		return use;
	}
	
	/**
	 * @param use the use to set
	 */
	public void setUse(String use) {
		this.use = use;
	}
	
	/**
	 * @return the latestChangeReg
	 */
	public String getLatestChangeReg() {
		return latestChangeReg;
	}
	
	/**
	 * @param latestChangeReg the latestChangeReg to set
	 */
	public void setLatestChangeReg(String latestChangeReg) {
		this.latestChangeReg = latestChangeReg;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the tecTotalWeight
	 */
	public String getTecTotalWeight() {
		return tecTotalWeight;
	}

	/**
	 * @param tecTotalWeight the tecTotalWeight to set
	 */
	public void setTecTotalWeight(String tecTotalWeight) {
		this.tecTotalWeight = tecTotalWeight;
	}

	/**
	 * @return the totalWeight
	 */
	public String getTotalWeight() {
		return totalWeight;
	}

	/**
	 * @param totalWeight the totalWeight to set
	 */
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

//	/**
//	 * @return the ownWeight
//	 */
//	public String getOwnWeight() {
//		return ownWeight;
//	}
//
//	/**
//	 * @param ownWeight the ownWeight to set
//	 */
//	public void setOwnWeight(String ownWeight) {
//		this.ownWeight = ownWeight;
//	}
//
//	/**
//	 * @return the couplingDevice
//	 */
//	public String getCouplingDevice() {
//		return couplingDevice;
//	}
//
//	/**
//	 * @param couplingDevice the couplingDevice to set
//	 */
//	public void setCouplingDevice(String couplingDevice) {
//		this.couplingDevice = couplingDevice;
//	}

//	
//	
//	/**
//	 * @return the weightOfTrailerWithBrakes
//	 */
//	public String getWeightOfTrailerWithBrakes() {
//		return weightOfTrailerWithBrakes;
//	}
//
//	/**
//	 * @param weightOfTrailerWithBrakes the weightOfTrailerWithBrakes to set
//	 */
//	public void setWeightOfTrailerWithBrakes(String weightOfTrailerWithBrakes) {
//		this.weightOfTrailerWithBrakes = weightOfTrailerWithBrakes;
//	}
//
//	/**
//	 * @return the weightOfTrailerWithoutBrakes
//	 */
//	public String getWeightOfTrailerWithoutBrakes() {
//		return weightOfTrailerWithoutBrakes;
//	}
//
//	/**
//	 * @param weightOfTrailerWithoutBrakes the weightOfTrailerWithoutBrakes to set
//	 */
//	public void setWeightOfTrailerWithoutBrakes(String weightOfTrailerWithoutBrakes) {
//		this.weightOfTrailerWithoutBrakes = weightOfTrailerWithoutBrakes;
//	}
//
//	/**
//	 * @return the propellant
//	 */
//	public String getPropellant() {
//		return propellant;
//	}
//
//	/**
//	 * @param propellant the propellant to set
//	 */
//	public void setPropellant(String propellant) {
//		this.propellant = propellant;
//	}
//
//	/**
//	 * @return the fuelConsumption
//	 */
//	public String getFuelConsumption() {
//		return fuelConsumption;
//	}
//
//	/**
//	 * @param fuelConsumption the fuelConsumption to set
//	 */
//	public void setFuelConsumption(String fuelConsumption) {
//		this.fuelConsumption = fuelConsumption;
//	}
//
//	/**
//	 * @return the bodyType
//	 */
//	public String getBodyType() {
//		return bodyType;
//	}
//
//	/**
//	 * @param bodyType the bodyType to set
//	 */
//	public void setBodyType(String bodyType) {
//		this.bodyType = bodyType;
//	}

	/**
	 * @return the posOfChassisNumber
	 */
	public String getPosOfChassisNumber() {
		return posOfChassisNumber;
	}

	/**
	 * @param posOfChassisNumber the posOfChassisNumber to set
	 */
	public void setPosOfChassisNumber(String posOfChassisNumber) {
		this.posOfChassisNumber = posOfChassisNumber;
	}
	
	/**
	 * @return the inspectionFreq
	 */
	public String getInspectionFreq() {
		return inspectionFreq;
	}

	/**
	 * @param inspectionFreq the inspectionFreq to set
	 */
	public void setInspectionFreq(String inspectionFreq) {
		this.inspectionFreq = inspectionFreq;
	}

	/**
	 * @return the calInspectionDate
	 */
	public String getCalInspectionDate() {
		return calInspectionDate;
	}

	/**
	 * @param calInspectionDate the calInspectionDate to set
	 */
	public void setCalInspectionDate(String calInspectionDate) {
		this.calInspectionDate = calInspectionDate;
	}

	/**
	 * @return the inspections
	 */
	public ArrayList<Inspection> getInspections() {
		return inspections;
	}
	
	public Inspection addInspection(Inspection i) {
		inspections.add(i);
		return i;
	}
//
//	/**
//	 * @return the isInsured
//	 */
//	public boolean isInsured() {
//		return isInsured;
//	}
//
//	/**
//	 * @param isInsured the isInsured to set
//	 */
//	public void setIsInsured(boolean isInsured) {
//		this.isInsured = isInsured;
//	}
//
//	/**
//	 * @return the insuranceComp
//	 */
//	public String getInsuranceComp() {
//		return insuranceComp;
//	}
//
//	/**
//	 * @param insuranceComp the insuranceComp to set
//	 */
//	public void setInsuranceComp(String insuranceComp) {
//		this.insuranceComp = insuranceComp;
//	}
//
//	/**
//	 * @return the insuranceStatus
//	 */
//	public String getInsuranceStatus() {
//		return insuranceStatus;
//	}
//
//	/**
//	 * @param insuranceStatus the insuranceStatus to set
//	 */
//	public void setInsuranceStatus(String insuranceStatus) {
//		this.insuranceStatus = insuranceStatus;
//	}
//
//	/**
//	 * @return the insuranceCreated
//	 */
//	public String getInsuranceCreated() {
//		return insuranceCreated;
//	}
//
//	/**
//	 * @param insuranceCreated the insuranceCreated to set
//	 */
//	public void setInsuranceCreated(String insuranceCreated) {
//		this.insuranceCreated = insuranceCreated;
//	}
//
//	/**
//	 * @return the permissions
//	 */
//	public HashSet<String> getPermissions() {
//		return permissions;
//	}
//	
//	/**
//	 * @param permission
//	 */
//	public void addPermission(String permission) {
//		permissions.add(permission);
//	}
}
