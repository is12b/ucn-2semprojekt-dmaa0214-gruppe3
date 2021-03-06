package ctrLayer;

import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;
import ctrLayer.interfaceLayer.IFProductCtr;
import ctrLayer.interfaceLayer.IFUnitTypeCtr;
import dbLayer.DBProduct;
import dbLayer.interfaceLayer.IFDBProduct;
import exceptions.DBException;
import exceptions.DBNotFoundException;
import exceptions.ObjectNotExistException;

public class ProductCtr implements IFProductCtr {
	
	@Override
	public Product getProductByID(int id) throws ObjectNotExistException{
		IFDBProduct dbProd = new DBProduct();
		Product product = dbProd.getProductByID(id);
		
		if(product == null){
			throw new ObjectNotExistException("Intet produkt fundet");
		}
		
		return product;
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) throws ObjectNotExistException{
		IFDBProduct dbProd = new DBProduct();
		ArrayList<Product> products = dbProd.searchProductsByName(name);
		
		if(products == null || products.size() == 0){
			throw new ObjectNotExistException("Ingen produkter fundet");
		}
		
		return products;
	}

	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) throws ObjectNotExistException{
		IFDBProduct dbProd = new DBProduct();
		ArrayList<Product> products = dbProd.searchProductsByItemNumber(itemNumber);
		
		if(products == null || products.size() == 0){
			throw new ObjectNotExistException("Ingen produkter fundet");
		}
		
		return products;
	}

	@Override
	public void updateProduct(Product product, String brand, String name, String description,
			String itemNumber, double price, UnitType unitType) throws DBException, ObjectNotExistException  {
		IFDBProduct dbProd = new DBProduct();
		Product tempObj = null;
		if(product != null) {
			try {
				tempObj = product.clone();
				
				product.setBrand(brand);
				product.setName(name);
				product.setDescription(description);
				product.setItemNumber(itemNumber);
				product.setPrice(price);
				product.setUnitType(unitType);
				
				dbProd.updateProduct(product);
			} catch (CloneNotSupportedException e) {
				System.out.println("ProductCtr: CloneNotSupportedException: "+ e.getMessage());
				//e.printStackTrace();
			} catch (DBNotFoundException e) {
				product.setToClone(tempObj);
				throw new ObjectNotExistException(e.getMessage());
			}	
		} else {
			throw new ObjectNotExistException("Produktet er ikke angivet");
		}
	}

	@Override
	public void deleteProduct(Product product) throws DBException, ObjectNotExistException {
		IFDBProduct dbProd = new DBProduct();
		if(product != null) {
			try {
				dbProd.deleteProduct(product);
			} catch (DBNotFoundException e) {
				throw new ObjectNotExistException(e.getMessage());
			}
		} else {
			throw new ObjectNotExistException("Produktet er ikke angivet");
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
