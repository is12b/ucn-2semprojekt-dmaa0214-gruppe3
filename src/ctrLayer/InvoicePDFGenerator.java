package ctrLayer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Sale;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;

import ctrLayer.SettingCtr;
import ctrLayer.interfaceLayer.IFSettingCtr;
import dbLayer.DBSettings;
import dbLayer.interfaceLayer.IFDBSettings;
import exceptions.BuildingPDFException;

/**
 * Class for InvoicePDFGenerator
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class InvoicePDFGenerator {
	//INIT
	private BaseFont bfBold;
	private BaseFont bf;
	private int pageNumber = 0;
	private NumberFormat moneyFormat;
	private boolean newPage = true;
	
	private final int font_size = 10;
	
	//POSITIONS
	private final int header_y_start = 760; //775
	
	private final float xStart = 20;
	private final float xEnd = 590;
	private final float amount_x = 22;
	private final float unittype_x = 52;
	private final float item_x = 90; //52
	private final float description_x = 190; //152
	private final float price_x = 470; // 432
	
	//SALE
	private float totalPriceX;
	private Sale sale;
	
	//TOTAL
	private double totalPrice = 0;
	private float yTotalPos;
	private float xTotalStart;
	//private float xTotalEnd;
	
	//STRING
	private final String amount_headText = "Antal";
	private final String unittype_headText = "Enhed";
	private final String itemnumber_headText = "Varenummer";
	private final String description_headText = "Beskrivelse";
	private final String price_headText = "Pris";
	private final String total_price_headText = "Total Pris";
	
	public InvoicePDFGenerator(Sale sale) throws BuildingPDFException {	
		if(sale == null){
			throw new BuildingPDFException("Det ønskede salg eksistere ikke længere?");
		}else if(sale.getPartSales() == null || sale.getPartSales().size() == 0){
			throw new BuildingPDFException("Det ønskede salg er tomt");
		}
		
		this.sale = sale;
	}
	
	
	public ByteArrayOutputStream createPDF() throws BuildingPDFException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		generateMoneyFormat();
		Document doc = new Document();
		PdfWriter docWriter = null;
		initializeFonts();
		ArrayList<PartSale> partSales = sale.getPartSales();

		try {
			docWriter = PdfWriter.getInstance(doc, baos);
			doc.addAuthor("Gruppe 3");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("Gruppe 3");
			doc.addTitle("Faktura");
			doc.setPageSize(PageSize.LETTER);
			doc.open();
			PdfContentByte cb = docWriter.getDirectContent();

			float y = 0;

			System.out.println(partSales.size());
			
			for (PartSale pS : sale.getPartSales()) {
				if (newPage) {
					newPage = false;
					y = generateHeader(doc, cb);
					generateLogo(doc);
					generateCustomer(doc, cb);
					y = generateLayout(doc, cb, y, true);
					//y += 6;
				}

				y = y - 12;
				generateDetail(doc, cb, pS, y);

				if (y < 65) {
					generateBotLine(cb, 50);
					printPageNumber(cb);
					doc.newPage();
					newPage = true;
				}
			}
			
			if(newPage){
				y = generateHeader(doc, cb);
				generateLogo(doc);
				generateCustomer(doc, cb);
				printPageNumber(cb);
				y += 4;
			}
			
			if (y > 65) {
				y -= 2;
				generateBotLine(cb, y);
				generateTotal(cb, y);
				printPageNumber(cb);
			}
			
			if(sale.getDescription() != null && !sale.getDescription().trim().isEmpty()){
				y = generateDesctiption(doc, cb, sale.getDescription(), y);
			}
			
			generateTerms(doc, cb, y);
			
		} catch (Exception e) {
			throw new BuildingPDFException("PDF filen kunne ikke genereres");
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}
		
		return baos;
	}
	
	/**
	 * Terms 
	 */
	
	private void generateTerms(Document doc, PdfContentByte cb, float y) throws IOException, DocumentException {
		if(!sale.isPaid()){
			y -= 45;
			
			if((y - 36) < 65){
				doc.newPage();
				y = generateHeader(doc, cb);
				generateLogo(doc);
				generateCustomer(doc, cb);
				y = generateLayout(doc, cb, y, false);
				printPageNumber(cb);
				
				y -= 10;
			}
			
			IFDBSettings dbSet = new DBSettings();
			String reg = dbSet.getSettingByKey("INVOICE_REG").getValue();
			String acc = dbSet.getSettingByKey("INVOICE_ACC").getValue();
			
			Date deadlineDate = sale.getPaymentDeadline();
			String payment = "Betalingsbetingelser: Kontant - forfaldt " + new SimpleDateFormat("dd-MM-yyyy").format(deadlineDate);
			
			createBoldHeadings(cb, xStart, y, payment);
			
			if(!reg.trim().isEmpty() && !acc.trim().isEmpty()){
				String paymentMethod = "Brug følgende information til indbetaling gennem vor bank. - Regnr.: " + reg + " / Kontonr.: " + acc; 
				y -= 10;
				createBoldHeadings(cb, xStart, y, paymentMethod);
			}
		}
	}

	/*
	 * Description
	 */
	
	private float generateDesctiption(Document doc, PdfContentByte cb, String description, float y) throws IOException, DocumentException {
		ArrayList<String> descs = breakDescription(description, 75);
		y -= 40;
		
		if((y - (descs.size() * 8)) < 65){
			printPageNumber(cb);
			doc.newPage();
			newPage = true;
		}
		
		for(String s : descs){
			if (newPage) {
				newPage = false;
				y = generateHeader(doc, cb);
				generateLogo(doc);
				generateCustomer(doc, cb);
				y = generateLayout(doc, cb, y, false);
			}
			
			y -= 8;
			createContent(cb, description_x, y,
					s,
					PdfContentByte.ALIGN_LEFT);
			/*
			if (y < 65) {
				generateBotLine(cb, 50);
				printPageNumber(cb);
				doc.newPage();
				newPage = true;
			}
			*/
		}
			
		return y;
	}

	/*
	 * Car
	 */
	
	private void generateCarInformation(Document doc, PdfContentByte cb) {
		Car c = sale.getCar();
		ArrayList<String> car = new ArrayList<String>();
		
		if(c.getRegNr() != null && !c.getRegNr().isEmpty()){
			car.add("Regnr.: " + c.getRegNr());
		}
		
		if(c.getVin() != null && !c.getVin().isEmpty()){
			car.add("Stelnr.: " +c.getVin());
		}

		if(sale.getMileage() != 0){
			car.add("Km.stand: " +String.valueOf(sale.getMileage()));
		}
		
		float i = doc.top() - 30;
				
		for(String s : car){
			i -= 10;
			createContent(cb, 200, i, s, PdfContentByte.ALIGN_LEFT);
		}
	}
	
	/*
	 * Customer
	 */

	private void generateCustomerInformation(Document doc, PdfContentByte cb) {
		Customer c = sale.getCustomer();
		String[] cust = {c.getName(), c.getAddress(), c.getPostalCode() + " " + c.getCity()};
		float i = doc.top() - 30;
				
		for(String s : cust){
			i -= 10;
			createContent(cb, 50, i, s, PdfContentByte.ALIGN_LEFT);
		}
	}
	
	private void generateCustomer(Document doc, PdfContentByte cb) {
		if(sale.getCustomer() != null){
			generateCustomerInformation(doc, cb);
		}
		
		if(sale.getCar() != null){
			generateCarInformation(doc, cb);
		}
		
	}

	/*
	 * Money Format
	 */
	
	private void generateMoneyFormat(){
		moneyFormat = NumberFormat.getCurrencyInstance();
		DecimalFormatSymbols dc = ((DecimalFormat) moneyFormat).getDecimalFormatSymbols();
		dc.setCurrencySymbol("");
		((DecimalFormat) moneyFormat).setDecimalFormatSymbols(dc);
	}

	/*
	 * Total
	 */
	
	private void generateTotal(PdfContentByte cb, float y){
		float longX = generateTotalValue(cb, y);
		generateTotalText(cb, y, totalPriceX-longX);
		
		cb.moveTo(xTotalStart, yTotalPos);
		cb.lineTo(xEnd, yTotalPos);
		cb.stroke();
	}
	
	private void generateTotalText(PdfContentByte cb, float y, float x){
		String[] total = {"SubTotal:", "Moms:", "Total:"};
		x -= getTextWidth(total[0], bf, font_size);
		float yT = y - 20;
		for(String s : total){
			createContent(cb, x, yT, s,
					PdfContentByte.ALIGN_LEFT);
			yT -= 10;
		}
		
		xTotalStart = x;
	}
	
	private float generateTotalValue(PdfContentByte cb, float y){
		float longest = 0;
		float yT = y - 20;
		String[] total = {moneyFormat.format(totalPrice), moneyFormat.format(totalPrice * 0.25), moneyFormat.format(totalPrice * 1.25)};
		for(String s : total){
			createContent(cb, xEnd, yT, s,
					PdfContentByte.ALIGN_RIGHT);
			yT -= 10;
			float l = getTextWidth(s, bf, font_size);
			if(l > longest){
				longest = l;
			}
		}
		
		yTotalPos = yT+6;
		
		
		return longest;
	}

	/**
	 * Header
	 */

	private float generateHeader(Document doc, PdfContentByte cb) {
		ArrayList<String> sets = getHeaderValues();
		float y = header_y_start;
		float x = getHeaderXPos(sets);

		for (String s : sets) {
			if (s != null && !s.trim().isEmpty()) {
				createHeadings(cb, x, y, s);
			}
			y -= 10;
		}

		return y;

	}

	private ArrayList<String> getHeaderValues() {
		ArrayList<String> sets = new ArrayList<String>();
		IFSettingCtr sCtr = new SettingCtr();

		sets.add("CVR: " + sCtr.getSettingByKey("INVOICE_CVR").getValue());
		sets.add(sCtr.getSettingByKey("INVOICE_NAME").getValue());
		sets.add(sCtr.getSettingByKey("INVOICE_ADDRESS").getValue());
		sets.add(sCtr.getSettingByKey("INVOICE_POST").getValue() + " - " + sCtr.getSettingByKey("INVOICE_CITY").getValue());
		sets.add("Mail: " + sCtr.getSettingByKey("INVOICE_EMAIL").getValue());
		sets.add("TLF: " + sCtr.getSettingByKey("INVOICE_PHONE").getValue());
		sets.add("FAX: " + sCtr.getSettingByKey("INVOICE_FAX").getValue());
		sets.add(sCtr.getSettingByKey("INVOICE_WEBSITE").getValue());
		sets.add("Fakturanr: " + sale.getId());
		sets.add("Fakturadato: " + new SimpleDateFormat("dd-MM-yyyy").format(sale.getDate()));

		return sets;
	}

	private void createHeadings(PdfContentByte cb, float x, float y, String text) {
		cb.beginText();
		cb.setFontAndSize(bf, 10);
		cb.setTextMatrix(x, y);
		cb.showText(text.trim());
		cb.endText();
	}
	
	private void createBoldHeadings(PdfContentByte cb, float x, float y, String text) {
		cb.beginText();
		cb.setFontAndSize(bfBold, font_size);
		cb.setTextMatrix(x, y);
		cb.showText(text.trim());
		cb.endText();
	}

	/*
	 * Detail
	 */

	private void generateDetail(Document doc, PdfContentByte cb, PartSale pS,
			float y) {

		try {
			String amount = String.valueOf(pS.getAmount());

			createContent(cb, amount_x+getTextWidth(amount_headText, bf, font_size), y, amount,
					PdfContentByte.ALIGN_RIGHT);
			
			createContent(cb, unittype_x, y, pS.getProduct().getUnitType().getShortDescription(), PdfContentByte.ALIGN_LEFT);
			
			createContent(cb, item_x, y, String.valueOf(pS.getProduct().getItemNumber()),
					PdfContentByte.ALIGN_LEFT);
			
			createContent(cb, description_x, y,
					String.valueOf(pS.getProduct().getDescription()),
					PdfContentByte.ALIGN_LEFT);
			
			String unitPrice = moneyFormat.format(pS.getUnitPrice());
			
			createContent(cb, price_x+getTextWidth(price_headText, bf, font_size), y, unitPrice,
					PdfContentByte.ALIGN_RIGHT);
			
			double totalP = (pS.getAmount() * pS.getUnitPrice());
			totalPrice += totalP;
			
			String total = moneyFormat.format(totalP);
			createContent(cb, xEnd, y, total,
					PdfContentByte.ALIGN_RIGHT);

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void createContent(PdfContentByte cb, float x, float y,
			String text, int align) {
		cb.beginText();
		cb.setFontAndSize(bf, font_size);
		cb.showTextAligned(align, text.trim(), x, y, 0);
		cb.endText();
	}

	private void printPageNumber(PdfContentByte cb) {

		cb.beginText();
		cb.setFontAndSize(bfBold, font_size);
		cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Side "
				+ (pageNumber + 1), 570, 25, 0);
		cb.endText();

		pageNumber++;

	}

	private float generateLayout(Document doc, PdfContentByte cb, float yStart, boolean withText) {
		yStart -= 40;
		cb.setLineWidth(1f);
		// Invoice Detail
		cb.moveTo(xStart, yStart);
		cb.lineTo(xEnd, yStart);
		cb.stroke();

		if(withText){
			generateDetailHeader(cb, yStart);
		}

		return yStart;
	}
	
	private void generateDetailHeader(PdfContentByte cb, float yStart){
		float yText = yStart + 2;
		createBoldHeadings(cb, amount_x, yText, amount_headText);
		createBoldHeadings(cb, unittype_x, yText, unittype_headText);
		createBoldHeadings(cb, item_x, yText, itemnumber_headText);
		createBoldHeadings(cb, description_x, yText, description_headText);
		createBoldHeadings(cb, price_x, yText, price_headText);
		
		totalPriceX = (xEnd - getTextWidth(total_price_headText, bfBold, font_size));
		createBoldHeadings(cb, totalPriceX, yText, total_price_headText);
	}
	
	private void generateLogo(Document doc) throws IOException, DocumentException{
		InputStream logo1Is = this.getClass().getClassLoader().getResourceAsStream("3.png");
		InputStream logo2Is = this.getClass().getClassLoader().getResourceAsStream("2.png");
		
		 Image logo2 = PngImage.getImage(logo1Is);
		 Image logo1 = PngImage.getImage(logo2Is);

		 logo1.setAbsolutePosition(25,doc.top()-15); //doc.top()
		 logo1.scalePercent(15);
		 doc.add(logo1);
		 
		 float offset = logo1.getPlainWidth();
		 
		 logo2.scalePercent(15);
		 logo2.setAbsolutePosition(25+offset,doc.top()-15);
		 doc.add(logo2);
	}
	
	private void generateBotLine(PdfContentByte cb, float y){
		cb.moveTo(xStart, y);
		cb.lineTo(xEnd, y);
		cb.stroke();
	}

	/*
	 * Misc
	 */
	
	private ArrayList<String> breakDescription(String desc, int maxLength){
		ArrayList<String> descs = new ArrayList<String>();
		descs.add("Beskrivelse:");
		StringTokenizer stk = new StringTokenizer(desc, " ");
		String line = "";
		while(stk.hasMoreTokens()){
			String token = stk.nextToken();
			
			if((line.length() + token.length()) > maxLength){
				descs.add(line);
				line = new String();
				line = token;
			}else{
				line += " " + token;
			}
		}
		descs.add(line);
		
		return descs;
	}

	private float getHeaderXPos(ArrayList<String> sets) {
		float retF = 0;
		for (String s : sets) {
			if (s != null && !s.trim().isEmpty()) {
				float f = getTextWidth(s, bf, 10);
				if (f > retF) {
					retF = f;
				}
			}
		}
		return (xEnd - retF);
	}

	private float getTextWidth(String text, BaseFont font, int fontSize) {
		return font.getWidthPoint(text, fontSize);
	}

	private void initializeFonts() {
		try {
			bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD,
					BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
