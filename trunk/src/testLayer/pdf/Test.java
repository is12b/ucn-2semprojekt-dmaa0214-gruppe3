package testLayer.pdf;

import dbLayer.DBPartSale;
import dbLayer.DBProduct;
import dbLayer.DBSale;
import dbLayer.DBUnitType;
import dbLayer.exceptions.DBException;
import dbLayer.interfaceLayer.IFDBPartSale;
import dbLayer.interfaceLayer.IFDBProduct;
import dbLayer.interfaceLayer.IFDBSale;
import dbLayer.interfaceLayer.IFDBUnitType;
import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.UnitType;

/**
 * Class for Test
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class Test {

	/**
	 * Constructor for Test objects.
	 *
	 */
	public Test() {
		IFDBPartSale dbPartSale = new DBPartSale();
		IFDBProduct dbProd = new DBProduct();
		
		for(int i = 0; i < 5; i++){
			PartSale ps = new PartSale();
			ps.setAmount(2);
			ps.setProduct(dbProd.getProductByID(18 + i));
			ps.setUnitPrice(100);
			
			IFDBSale dbSale = new DBSale();
			
			dbPartSale.insertPartSale(dbSale.getSale(7), ps);
		}
		
		
		/*
		IFDBUnitType dbUnit = new DBUnitType();
		UnitType ut = dbUnit.getUnitType("stk");
		IFDBProduct dbProd = new DBProduct();
		for(int i = 0; i < 50; i++){
			Product p = new Product();
			p.setBrand("TestBrand");
			p.setName("TestProdukt" + (i + 20));
			p.setDescription("En Beskrivelse af test produktet");
			p.setHidden(false);
			p.setItemNumber("ItemNumber" + i);
			p.setPrice(i + 1000);
			p.setUnitType(ut);
			
			try {
				dbProd.insertProduct(p);
			} catch (DBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}

}
