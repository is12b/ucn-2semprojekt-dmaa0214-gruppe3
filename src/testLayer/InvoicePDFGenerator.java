package testLayer;

import modelLayer.Sale;
import modelLayer.Setting;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
 



import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import ctrLayer.SettingCtr;
import ctrLayer.interfaceLayer.IFSettingCtr;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBSale;

/**
 * Class for InvoicePDFGenerator
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class InvoicePDFGenerator {
	private BaseFont bfBold;
	private BaseFont bf;
	private final int HEADER_Y_START = 775;
	private final int HEADER_X_START = 600;
	private final int HEADER_FONT_SIZE = 8;
	private int pageNumber = 0;
	
	public static void main(String[] args) {
		IFDBSale dbSale = new DBSale();
		Sale s = dbSale.getSale(4);
		
		InvoicePDFGenerator i = new InvoicePDFGenerator(s); 
	}
	
	public InvoicePDFGenerator(Sale sale) {
		Document doc = new Document();
		PdfWriter docWriter = null;
		initializeFonts();
		
		try{
			String path = "tmpInvoice.pdf";
			docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.addAuthor("Gruppe 3");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("Gruppe 3");
			doc.addTitle("Faktura");
			doc.setPageSize(PageSize.LETTER);
			
			doc.open();
			
			PdfContentByte cb = docWriter.getDirectContent();
			
			boolean newPage = true;
			float y = 0;
			
			for(int i = 0; i < 100; i++){
				
				if(newPage){
					newPage = false;
					y = generateHeader(doc, cb);
					y = generateLayout(doc, cb, y);
					y += 4; 
				}
				
				y = y - 12;
				generateDetail(doc, cb, i, y);
				
			    if(y < 65){
			     printPageNumber(cb);
			     doc.newPage();
			     newPage = true;
			    }
			    
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (doc != null){
				doc.close();
			}
			if (docWriter != null){
				docWriter.close();
			}
		}
	}
	
	/**
	 * Text Manipulation
	 */
	
	/**
	 * Details
	 */
	
	private float generateLayout(Document doc, PdfContentByte cb, float yStart) {
		try {
			yStart -= 20;
			float yEnd = 50;
			float yText = yStart + 2;
			float xStart = 20;
			float xEnd = 590;
			
			cb.setLineWidth(1f);

			// Invoice Detail
			cb.moveTo(xStart, yStart);
			cb.lineTo(xEnd, yStart);
			cb.moveTo(xStart, yEnd);
			cb.lineTo(xEnd, yEnd);
			cb.stroke();

			// Invoice Detail
			createHeadings(cb, 22, yText, "Antal");
			createHeadings(cb, 52, yText, "Varenummer");
			createHeadings(cb, 152, yText, "Beskrivelse");
			createHeadings(cb, 432, yText, "Pris");
			createHeadings(cb, (xEnd - getTextWidth("Total Pris", bfBold, HEADER_FONT_SIZE)), yText, "Total Pris");
			
			
			// add the images
			/*
			 * Image companyLogo =
			 * Image.getInstance("images/olympics_logo.gif");
			 * companyLogo.setAbsolutePosition(25,700);
			 * companyLogo.scalePercent(25); doc.add(companyLogo);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return yStart;
	}
	
	/**
	 * Header
	 */
	
	private float generateHeader(Document doc, PdfContentByte cb){
		ArrayList<Setting> sets = getHeaderValues();
		float y = HEADER_Y_START;
		float x = getHeaderXPos(sets);
		
		for(Setting s : sets){
			if(s.getValue() != null && !s.getValue().trim().isEmpty()){
				createHeadings(cb, x, y, s.getValue());
			}
			y -= 15;
		}
		
		return y;

	}
	
	private ArrayList<Setting> getHeaderValues(){
		ArrayList<Setting> sets = new ArrayList<Setting>();
		IFSettingCtr sCtr = new SettingCtr();
		
		sets.add(sCtr.getSettingByKey("INVOICE_CVR"));
		sets.add(sCtr.getSettingByKey("INVOICE_NAME"));
		sets.add(sCtr.getSettingByKey("INVOICE_ADDRESS"));
		sets.add(sCtr.getSettingByKey("INVOICE_POST")); 
		sets.add(sCtr.getSettingByKey("INVOICE_CITY"));
		sets.add(sCtr.getSettingByKey("INVOICE_EMAIL"));
		sets.add(sCtr.getSettingByKey("INVOICE_PHONE"));
		sets.add(sCtr.getSettingByKey("INVOICE_FAX"));
		sets.add(sCtr.getSettingByKey("INVOICE_WEBSITE"));
		
		return sets;
	}
	
	
	private void createHeadings(PdfContentByte cb, float x, float y, String text){
	  cb.beginText();
	  cb.setFontAndSize(bfBold, HEADER_FONT_SIZE);
	  cb.setTextMatrix(x,y);
	  cb.showText(text.trim());
	  cb.endText(); 
	}
	
	/**
	 * Misc
	 */
	
	private float getHeaderXPos(ArrayList<Setting> sets){
		float retF = 0;
		for(Setting s : sets){
			if(s.getValue() != null && !s.getValue().trim().isEmpty()){
				float f = getTextWidth(s.getValue(), bfBold, HEADER_FONT_SIZE);
				if(f > retF){
					retF = f;
				}
			}
		}
		return (HEADER_X_START - retF);
	}
	
	private float getTextWidth(String text, BaseFont font, int fontSize){
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
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * CP
	 */
	
	private void generateDetail(Document doc, PdfContentByte cb, int index, float y)  {
		  DecimalFormat df = new DecimalFormat("0,00");
		   
		  try {
		 
		   createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
		   createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
		   createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
		    
		   double price = Double.valueOf(df.format(Math.random() * 10));
		   double extPrice = price * (index+1) ;
		   createContent(cb,498,y, df.format(price),PdfContentByte.ALIGN_RIGHT);
		   createContent(cb,568,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
		    
		  }
		 
		  catch (Exception ex){
		   ex.printStackTrace();
		  }
		 
		 }
	
	 private void createContent(PdfContentByte cb, float x, float y, String text, int align){
		 
		 
		  cb.beginText();
		  cb.setFontAndSize(bf, 8);
		  cb.showTextAligned(align, text.trim(), x , y, 0);
		  cb.endText(); 
		 
		 }
	 
	 private void printPageNumber(PdfContentByte cb){
		 
		 
		  cb.beginText();
		  cb.setFontAndSize(bfBold, 8);
		  cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
		  cb.endText(); 
		   
		  pageNumber++;
		   
		 }
	
}
