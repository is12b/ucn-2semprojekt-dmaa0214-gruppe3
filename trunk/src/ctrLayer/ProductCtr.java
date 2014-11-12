package ctrLayer;

import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;
import ctrLayer.interfaceLayer.IFProductCtr;

public class ProductCtr implements IFProductCtr {

	@Override
	public Product getProductByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product createProduct(String brand, String name, String description,
			String itemNumber, double price, boolean hidden, UnitType unitType) {
		// TODO Auto-generated method stub
		return null;
	}

}
