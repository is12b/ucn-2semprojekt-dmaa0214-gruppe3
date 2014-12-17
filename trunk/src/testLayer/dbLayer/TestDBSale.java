package testLayer.dbLayer;

import static org.junit.Assert.*;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;

import org.junit.Before;
import org.junit.Test;

import dbLayer.DBProduct;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBProduct;
import dbLayer.interfaceLayer.IFDBSale;
import exceptions.DBException;

public class TestDBSale {
	IFDBSale dbSale;
	IFDBProduct dbProd;

	@Before
	public void setUp() throws Exception {
		dbSale = new DBSale();
		dbProd = new DBProduct();
	}
	
	@Test
	public void testSomething(){
		boolean test = false;
		Sale s = new Sale();
		if(!test){
		
			try {
				Product p1 = dbProd.searchProductsByItemNumber("WQA120423").get(0);
				Product p2 = dbProd.searchProductsByItemNumber("WQA120424").get(0);
				PartSale pSale1 = new PartSale();
				pSale1.setAmount(2);
				pSale1.setPrice(125);
				pSale1.setProduct(p1);
				
				PartSale pSale2 = new PartSale();
				pSale2.setAmount(2);
				pSale2.setPrice(125);
				pSale2.setProduct(p2);
				
				s.addPartSale(pSale1);
				s.addPartSale(pSale2);
				
				dbSale.insertSale(s);
				
				test = true;
			} catch (DBException e) {
				System.out.println("Insert Failed");
				test = false;
			}
		
		}
		
		if(test){
			try {
				dbSale.deleteSale(s);
				test = true;
			} catch (DBException e) {
				System.out.println("Delete Failed");
				test = false;
			}
		}
		
		assertTrue(test);
		
	}

}
