package ctrLayer;

import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;
import ctrLayer.interfaceLayer.IFProductCtr;
import ctrLayer.interfaceLayer.IFUnitTypeCtr;
import dbLayer.DBProduct;
import dbLayer.exceptions.DBException;
import dbLayer.interfaceLayer.IFDBProduct;

public class ProductCtr implements IFProductCtr {
	
	@Override
	public Product getProductByID(int id) {
		IFDBProduct dbProd = new DBProduct();
		Product product = dbProd.getProductByID(id);
		
		return product;
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		IFDBProduct dbProd = new DBProduct();
		return dbProd.searchProductsByName(name);
	}

	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) {
		IFDBProduct dbProd = new DBProduct();
		return dbProd.searchProductsByItemNumber(itemNumber);
	}

	@Override
	public void updateProduct(Product product, String brand, String name, String description,
			String itemNumber, double price, UnitType unitType) throws DBException, NullPointerException  {
		IFDBProduct dbProd = new DBProduct();
		if(product != null) {
			dbProd.updateProduct(product);
		} else {
			throw new NullPointerException("Produktet er ikke angivet");
		}
	}

	@Override
	public void deleteProduct(Product product) throws DBException, NullPointerException {
		IFDBProduct dbProd = new DBProduct();
		if(product != null) {
			dbProd.deleteProduct(product);
		} else {
			throw new NullPointerException("Produktet er ikke angivet");
		}
	}
	
	@Override
	public Product createProduct(String brand, String name, String description,
			String itemNumber, double price, UnitType unitType) throws DBException {
		
		IFDBProduct dbProd = new DBProduct();
		Product product = new Product(brand, name, description, itemNumber, price, unitType);
		dbProd.insertProduct(product);
		return product;
	}

	@Override
	public ArrayList<UnitType> getAllUnitTypes() {
		IFUnitTypeCtr utCtr = new UnitTypeCtr();
		return utCtr.getUnitTypes();
	}
}
