package testLayer.pdf;

import modelLayer.PartSale;
import dbLayer.DBPartSale;
import dbLayer.DBProduct;
import dbLayer.DBSale;
import dbLayer.interfaceLayer.IFDBPartSale;
import dbLayer.interfaceLayer.IFDBProduct;
import dbLayer.interfaceLayer.IFDBSale;
import exceptions.DBException;

/**
 * Class for Test
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class Test {

	/**
	 * Constructor for Test objects.
	 * @throws DBException 
	 *
	 */
	public Test() throws DBException {
		IFDBPartSale dbPartSale = new DBPartSale();
		IFDBProduct dbProd = new DBProduct();
		
		for(int i = 0; i < 5; i++){
			PartSale ps = new PartSale();
			ps.setAmount(2);
			ps.setProduct(dbProd.getProductByID(18 + i));
			ps.setPrice(100);
			
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
				
				e.printStackTrace();
			}
		}
		*/
	}

	/**
	 * @param args
	 * @throws DBException 
	 */
	public static void main(String[] args) throws DBException {
		new Test();
	}

}
