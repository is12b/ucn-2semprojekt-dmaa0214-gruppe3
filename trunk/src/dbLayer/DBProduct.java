package dbLayer;
//TODO Lau
import java.util.ArrayList;
import modelLayer.Product;
import dbLayer.interfaceLayer.IFDBProduct;

/**
 * Class for DBProduct
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBProduct implements IFDBProduct {

	@Override
	public ArrayList<Product> searchProductsByItemNumber(String ItemNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

}
