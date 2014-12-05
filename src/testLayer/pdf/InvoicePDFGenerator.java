package testLayer.pdf;

import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Sale;
import guiLayer.exceptions.BuildingPDFException;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

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
import dbLayer.DBSale;
import dbLayer.DBSettings;
import dbLayer.interfaceLayer.IFDBSale;
import dbLayer.interfaceLayer.IFDBSettings;

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
	
	private final int FONT_SIZE = 8;
	
	//POSITIONS
	private final int HEADER_Y_START = 775;
	
	private final float xStart = 20;
	private final float xEnd = 590;
	private final float AMOUNT_X = 22;
	private final float ITEM_X = 52;
	private final float DESCRIPTION_X = 152;
	private final float PRICE_X = 432;
	
	//SALE
	private float totalPriceX;
	private Sale sale;
	
	//TOTAL
	private double totalPrice = 0;
	private float yTotalPos;
	private float xTotalStart;
	//private float xTotalEnd;
	
	//STRING
	private final String AMOUNT = "Antal";
	private final String ITEMNUMBER = "Varenummer";
	private final String DESCRIPTION = "Beskrivelse";
	private final String PRICE = "Pris";
	private final String TOTAL_PRICE = "Total Pris";
	
	public static void main(String[] args) throws BuildingPDFException {
		
		IFDBSale dbSale = new DBSale();
		Sale s = dbSale.getSale(2);

		InvoicePDFGenerator i = new InvoicePDFGenerator(s);
		i.createPDF();
		try {

		File pdfFile = new File("tmpInvoice.pdf");
		if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
					System.out.println("Awt Desktop is not supported!");
			}

			} else {
					System.out.println("File is not exists!");
				}
	
				System.out.println("Done");
	
			  } catch (Exception ex) {
				ex.printStackTrace();
		  }
		}

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
					y += 4;
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
			y -= 20;
			
			if((y - 36) < 65){
				doc.newPage();
				y = generateHeader(doc, cb);
				generateLogo(doc);
				generateCustomer(doc, cb);
				y = generateLayout(doc, cb, y, false);
				printPageNumber(cb);
				
				y -= 8;
			}
			
			IFDBSettings dbSet = new DBSettings();
			String reg = dbSet.getSettingByKey("INVOICE_REG").getValue();
			String acc = dbSet.getSettingByKey("INVOICE_ACC").getValue();
			
			Date deadlineDate = sale.getPaymentDeadline();
			String payment = "Betalingsbetingelser: Kontant - forfaldt " + new SimpleDateFormat("dd-MM-yyyy").format(deadlineDate);
			
			createBoldHeadings(cb, xStart, y, payment);
			
			if(!reg.trim().isEmpty() && !acc.trim().isEmpty()){
				String paymentMethod = "Brug følgende information til indbetaling gennem vor bank. - Regnr.: " + reg + " / Kontonr.: " + acc; 
				y -= 8;
				createBoldHeadings(cb, xStart, y, paymentMethod);
			}
		}
	}

	/*
	 * Description
	 */
	
	private float generateDesctiption(Document doc, PdfContentByte cb, String description, float y) throws IOException, DocumentException {
		ArrayList<String> descs = breakDescription(description, 75);
		y -= 35;
		
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
			createContent(cb, DESCRIPTION_X, y,
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
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Customer
	 */

	private void generateCustomerInformation(Document doc, PdfContentByte cb) {
		Customer c = sale.getCustomer();
		String[] cust = {c.getName(), c.getAddress(), c.getPostalCode() + " " + c.getCity()};
		float i = doc.top() - 30;
				
		for(String s : cust){
			i -= 8;
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
		x -= getTextWidth(total[0], bf, FONT_SIZE);
		float yT = y - 20;
		for(String s : total){
			createContent(cb, x, yT, s,
					PdfContentByte.ALIGN_LEFT);
			yT -= 8;
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
			yT -= 8;
			float l = getTextWidth(s, bf, FONT_SIZE);
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
		float y = HEADER_Y_START;
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
		sets.add("Faktura: " + sale.getId());

		return sets;
	}

	private void createHeadings(PdfContentByte cb, float x, float y, String text) {
		cb.beginText();
		cb.setFontAndSize(bf, 8);
		cb.setTextMatrix(x, y);
		cb.showText(text.trim());
		cb.endText();
	}
	
	private void createBoldHeadings(PdfContentByte cb, float x, float y, String text) {
		cb.beginText();
		cb.setFontAndSize(bfBold, FONT_SIZE);
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

			createContent(cb, AMOUNT_X+getTextWidth(AMOUNT, bf, FONT_SIZE), y, amount,
					PdfContentByte.ALIGN_RIGHT);
			
			createContent(cb, ITEM_X, y, String.valueOf(pS.getProduct().getItemNumber()),
					PdfContentByte.ALIGN_LEFT);
			
			createContent(cb, DESCRIPTION_X, y,
					String.valueOf(pS.getProduct().getDescription()),
					PdfContentByte.ALIGN_LEFT);
			
			String unitPrice = moneyFormat.format(pS.getUnitPrice());
			
			createContent(cb, PRICE_X+getTextWidth(PRICE, bf, FONT_SIZE), y, unitPrice,
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
		cb.setFontAndSize(bf, FONT_SIZE);
		cb.showTextAligned(align, text.trim(), x, y, 0);
		cb.endText();
	}

	private void printPageNumber(PdfContentByte cb) {

		cb.beginText();
		cb.setFontAndSize(bfBold, FONT_SIZE);
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
		createBoldHeadings(cb, AMOUNT_X, yText, AMOUNT);
		createBoldHeadings(cb, ITEM_X, yText, ITEMNUMBER);
		createBoldHeadings(cb, DESCRIPTION_X, yText, DESCRIPTION);
		createBoldHeadings(cb, PRICE_X, yText, PRICE);
		
		totalPriceX = (xEnd - getTextWidth(TOTAL_PRICE, bfBold, FONT_SIZE));
		createBoldHeadings(cb, totalPriceX, yText, TOTAL_PRICE);
	}
	
	private void generateLogo(Document doc) throws IOException, DocumentException{
		InputStream logo1Is = this.getClass().getClassLoader().getResourceAsStream("3.png");
		InputStream logo2Is = this.getClass().getClassLoader().getResourceAsStream("2.png");
		
		 Image logo2 = PngImage.getImage(logo1Is);
		 Image logo1 = PngImage.getImage(logo2Is);

		 logo1.setAbsolutePosition(25,doc.top());
		 logo1.scalePercent(15);
		 doc.add(logo1);
		 
		 float offset = logo1.getPlainWidth();
		 
		 logo2.scalePercent(15);
		 logo2.setAbsolutePosition(25+offset,doc.top());
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
				float f = getTextWidth(s, bf, 8);
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
