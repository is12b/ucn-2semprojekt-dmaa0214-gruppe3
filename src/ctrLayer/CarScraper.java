package ctrLayer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Inspection;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import modelLayer.CarExtra;

public class CarScraper {
	private HtmlPage finalPage;
	private WebClient webClient;

	public CarScraper(){

	}

	public CarExtra getExtra(Car car) throws Exception {
		String regNr = car.getRegNr();

		CarExtra ext = new CarExtra();
	    webClient = new WebClient();
	    finalPage = getExecutedDMRPage(true, regNr, "https://motorregister.skat.dk/dmr-front/appmanager/skat/dmr?_nfpb=true&_nfpb=true&_pageLabel=vis_koeretoej_side&_nfls=false");
	    
	    if(getSpanValueByKey("Stelnummer:") != "Ukendt") {
	    
		    writeVehicleData(ext);
		    
		    finalPage = getExecutedDMRPage(false, regNr, "https://motorregister.skat.dk/dmr-front/appmanager/skat/dmr?_nfpb=true&_windowLabel=kerne_vis_koeretoej&kerne_vis_koeretoej_actionOverride=%2Fdk%2Fskat%2Fdmr%2Ffront%2Fportlets%2Fkoeretoej%2Fnested%2FvisKoeretoej%2FselectTab&kerne_vis_koeretoejdmr_tabset_tab=1&_pageLabel=vis_koeretoej_side");
		    writeTechnicalData(ext);
		    
		    finalPage = getExecutedDMRPage(false, regNr, "https://motorregister.skat.dk/dmr-front/appmanager/skat/dmr?_nfpb=true&_windowLabel=kerne_vis_koeretoej&kerne_vis_koeretoej_actionOverride=%2Fdk%2Fskat%2Fdmr%2Ffront%2Fportlets%2Fkoeretoej%2Fnested%2FvisKoeretoej%2FselectTab&kerne_vis_koeretoejdmr_tabset_tab=2&_pageLabel=vis_koeretoej_side");
		    writeInspectionData(ext, car);
		    
		    /*
		    finalPage = getExecutedDMRPage(false, regNr, "https://motorregister.skat.dk/dmr-front/appmanager/skat/dmr?_nfpb=true&_windowLabel=kerne_vis_koeretoej&kerne_vis_koeretoej_actionOverride=%2Fdk%2Fskat%2Fdmr%2Ffront%2Fportlets%2Fkoeretoej%2Fnested%2FvisKoeretoej%2FselectTab&kerne_vis_koeretoejdmr_tabset_tab=3&_pageLabel=vis_koeretoej_side");
		    writeInsuranceData(ext);
		    
		    finalPage = getExecutedDMRPage(false, regNr, "https://motorregister.skat.dk/dmr-front/appmanager/skat/dmr?_nfpb=true&_windowLabel=kerne_vis_koeretoej&kerne_vis_koeretoej_actionOverride=%2Fdk%2Fskat%2Fdmr%2Ffront%2Fportlets%2Fkoeretoej%2Fnested%2FvisKoeretoej%2FselectTab&kerne_vis_koeretoejdmr_tabset_tab=5&_pageLabel=vis_koeretoej_side");
		    writePermissions(ext);
		    */
		    
	    }
	   
	    webClient.closeAllWindows();
		
	
	    return ext;
	}

	private HtmlPage getExecutedDMRPage(boolean firstLoad, String regNr, String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlPage page = webClient.getPage(url);
		String executeStr;
		if (firstLoad) {
			executeStr = "document.getElementById('regnr').checked=true;"
	                +"document.getElementById('soegeord').value='"+regNr+"';"
	                +"document.getElementById('searchForm').submit();"
	                +"DMR.WaitForLoad.on();";
		} else {
			executeStr = "DMR.WaitForLoad.on();";
		}
		ScriptResult result = page.executeJavaScript(executeStr);
    	return (HtmlPage) result.getNewPage();
	}

	private String getSpanValueByKey(String key){
		String retS = "";
		DomElement dE = (DomElement) finalPage.getFirstByXPath("//span[contains(., '" + key + "')]");
	    try{
			retS = dE.getNextElementSibling().asText();;
		}catch(NullPointerException e){
			retS = "Ukendt";
		}
	    return retS;
	}
	
	private String getLabelValueByKey(String key){
		String retS = "";
		DomElement dE = (DomElement) finalPage.getFirstByXPath("//label[contains(., '" + key + "')]");
		try{
			retS = dE.getParentNode().getNextSibling().getChildNodes().get(0).asText();
		}catch(NullPointerException e){
			retS = "Ukendt";
		}
	    return retS;
	}
	/*
	private String getIndentedLabelValueByKey(String key){
		String retS = "";
		DomElement dE = (DomElement) finalPage.getFirstByXPath("//label[contains(., '" + key + "')]");
		try{
			retS = dE.getParentNode().getParentNode().getNextSibling().getChildNodes().get(0).asText();
		}catch(NullPointerException e){
			retS = "Ukendt";
		}
	    return retS;
	}
	*/
	private void writeVehicleData(CarExtra ext){
		ext.setType(getSpanValueByKey("Art:"));
		ext.setLatestChangeVehicle(getSpanValueByKey("Seneste ændring:"));
		ext.setFirstRegDate(getSpanValueByKey("Første registrerings­dato:"));
		ext.setUse(getSpanValueByKey("Anvendelse:"));
		ext.setLatestChangeReg(getSpanValueByKey("Seneste ændring:"));
		ext.setStatus(getLabelValueByKey("Status:"));
	}
	

	private void writeTechnicalData(CarExtra ext) {
		ext.setTecTotalWeight(getLabelValueByKey("Teknisk totalvægt:"));
		ext.setTotalWeight(getLabelValueByKey("Totalvægt:"));
//		ext.setOwnWeight(getLabelValueByKey("Egenvægt:"));
//		ext.setCouplingDevice(getLabelValueByKey("Tilkoblingsanordning:"));
//		ext.setWeightOfTrailerWithBrakes(getIndentedLabelValueByKey("Med bremser:"));
//		ext.setWeightOfTrailerWithoutBrakes(getIndentedLabelValueByKey("Uden bremser:"));
//		ext.setPropellant(getLabelValueByKey("Drivkraft:"));
//		ext.setFuelConsumption(getLabelValueByKey("Brændstofforbrug:"));
//		ext.setBodyType(getLabelValueByKey("Karrosseritype:"));
		ext.setPosOfChassisNumber(getLabelValueByKey("Anbringelse af stelnummer:"));
	}
	
	private void writeInspectionData(CarExtra ext, Car car) {
		ext.setInspectionFreq(getLabelValueByKey("Frekvens for periodisk syn:"));
		ext.setCalInspectionDate(getLabelValueByKey("Beregnet dato for næste indkaldelse til periodisk syn:"));
		DomElement dE = (DomElement) finalPage.getFirstByXPath("//p[contains(., 'Køretøjet har aldrig været synet.')]");
		if(dE == null) {
			addInspections(car, ext);
		}
	}

	private void addInspections(Car car, CarExtra ext) {
		String url = "http://selvbetjening.trafikstyrelsen.dk/Sider/resultater.aspx?Reg=" + car.getRegNr();
		ArrayList<Inspection> inspecs = new ArrayList<Inspection>();
		try {
			WebClient webClient = new WebClient();
		    HtmlPage page = webClient.getPage(url);
		    HtmlTableBody table = (HtmlTableBody) page.getByXPath("//table[@id='tblInspections']/tbody").get(0);
			
		    for (HtmlTableRow row : table.getRows()) {
		    	Inspection inspec = new Inspection();
		    	String[] data = new String[row.getChildElementCount()];
		    	int i = 0;
		    	for(HtmlTableCell td : row.getCells()){
		    		if(!td.asXml().contains("<a")){
		    			data[i] = td.asText();
		    		}else{
		    			HtmlAnchor a = (HtmlAnchor) td.getFirstByXPath("//a[@class='saveIcon']");
		    			data[i] = "http://selvbetjening.trafikstyrelsen.dk" + a.getAttribute("href");
		    		}
		    		i++;
		    	}
		    	inspec.setDate(data[0]);
		    	inspec.setResult(data[1]);
		    	inspec.setKm(data[2]);
		    	inspec.setRegNr(data[3]);
		    	inspec.setUrl(data[4]);
		    	
		    	inspecs.add(inspec);
		    }
		    
		    car.setInspections(inspecs);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	/*
	private void writeInsuranceData(Extra ext) {
		DomElement dE = (DomElement) finalPage.getFirstByXPath("//span[contains(., 'Ingen forsikring:')]");
		if(dE == null) {
			ext.setIsInsured(true);
			ext.setInsuranceComp(getSpanValueByKey("Selskab:"));
			ext.setInsuranceStatus(getSpanValueByKey("Status:"));
			ext.setInsuranceCreated(getSpanValueByKey("Oprettet:"));
		}
	}
	
	
	private void writePermissions(Extra ext) {
		HtmlTable table = (HtmlTable)  finalPage.getFirstByXPath("//table[@class='stripes']");
		if(table != null) {
			int index = 1;
			List<HtmlTableRow> rows = table.getRows();
			while(index < rows.size() && rows.get(0).getCell(0).asText().equals("Tilladelse")) {
				ext.addPermission(rows.get(index).getCell(0).asText());
				index++;
			}
		}
				
	}
	*/

}
