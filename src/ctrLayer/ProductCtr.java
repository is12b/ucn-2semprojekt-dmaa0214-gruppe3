package ctrLayer;

import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;
import ctrLayer.interfaceLayer.IFProductCtr;
import dbLayer.DBProduct;

public class ProductCtr implements IFProductCtr {

	@Override
	public Product getProductByID(int id) {
		DBProduct dbProd = new DBProduct();
		return dbProd.getProductByID(id);
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		DBProduct dbProd = new DBProduct();
		return dbProd.searchProductsByName(name);
	}

	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) {
		DBProduct dbProd = new DBProduct();
		return dbProd.searchProductsByItemNumber(itemNumber);
	}

	@Override
	public void updateProduct(Product product) {
		DBProduct dbProd = new DBProduct();
		dbProd.updateProduct(product);
	}

	@Override
	public void deleteProduct(Product product) {
		DBProduct dbProd = new DBProduct();
		dbProd.deleteProduct(product);
	}

	@Override
	public Product createProduct(String brand, String name, String description,
			String itemNumber, double price, boolean hidden, UnitType unitType) {
		DBProduct dbProd = new DBProduct();
		Product product = new Product(brand, name, description, itemNumber, price, hidden, unitType);
		dbProd.insertProduct(product);
		return product;
	}

}
