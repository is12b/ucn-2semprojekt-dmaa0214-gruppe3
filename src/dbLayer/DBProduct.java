package dbLayer;
//TODO Lau
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;
import dbLayer.interfaceLayer.IFDBProduct;
import dbLayer.interfaceLayer.IFDBUnitType;

/**
 * Class for DBProduct
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBProduct implements IFDBProduct {
	private Connection conn;
	
	public DBProduct(){
		conn = DBConnection.getInstance().getDBCon();
	}

	@Override
	public ArrayList<Product> searchProductsByItemNumber(String itemNumber) {
		return miscWhere("ItemNumber = '" + itemNumber + "'");
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		return miscWhere("Name = '" + name + "'");
	}

	@Override
	public Product getProductByID(int id) {
		return singleWhere("ProductID = " + id);
	}

	@Override
	public int insertProduct(Product product) {
		int rc = -1;
		
		try{
			String query = "INSERT INTO PRODUCT "
					+ "(ProductID, ItemNumber, Brand, Name, Description, Price, Hidden, UnitType) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setQueryTimeout(5);
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getItemNumber());
			stmt.setString(3, product.getBrand());
			stmt.setString(4, product.getName());
			stmt.setString(5, product.getDescription());
			stmt.setDouble(6, product.getPrice());
			stmt.setBoolean(7, product.isHidden());
			stmt.setString(8, product.getUnitType().getShortDescription());
			rc = stmt.executeUpdate();
			
			ResultSet genRs = stmt.getGeneratedKeys();
			if(genRs.next()){
				product.setId(genRs.getInt(1));
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBProduct - insertProduct - Exception");
			e.printStackTrace();
		}
		
		return rc;
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
	
	private ArrayList<Product> miscWhere(String wQuery){
		ArrayList<Product> products = new ArrayList<Product>();
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Product product = buildProduct(rs);
				
				IFDBUnitType dbUnitType = new DBUnitType();
				product.setUnitType(dbUnitType.getUnitType(product.getUnitType().getShortDescription()));
				
				products.add(product);
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBProduct - MiscWhere - Exception");
			e.printStackTrace();
		}
		
		return products;
	}
	
	private Product singleWhere(String wQuery){
		Product product = null;
		
		try{
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				product = buildProduct(rs);
				
				IFDBUnitType dbUnitType = new DBUnitType();
				product.setUnitType(dbUnitType.getUnitType(product.getUnitType().getShortDescription()));
			}
			
			stmt.close();
		}catch(Exception e){
			System.out.println("DBProduct - SingleWhere - Exception");
			e.printStackTrace();
		}
		
		return product;
	}

	/**
	 * @param rs
	 * @return
	 */
	private Product buildProduct(ResultSet rs) {
		Product product = new Product();
		
		try{
			product.setBrand(rs.getString("Brand"));
			product.setDescription(rs.getString("Description"));
			product.setHidden(rs.getBoolean("Hidden"));
			product.setId(rs.getInt("ProductID"));
			product.setItemNumber(rs.getString("ItemNumber"));
			product.setName(rs.getString("Name"));
			product.setPrice(rs.getDouble("Price"));
			product.setUnitType(new UnitType(rs.getString("UnitType")));
		}catch(Exception e){
			System.out.println("DBProduct - buildProduct - Exception");
			e.printStackTrace();
		}
		
		return product;
	}

	/**
	 * @param wQuery
	 * @return
	 */
	private String buildQuery(String wQuery) {
		String query = "SELECT ProductID, ItemNumber, Brand, Name, Description, Price, Hidden, UnitType FROM Product";
		
		if(!wQuery.isEmpty()){
			query += " WHERE " + wQuery;
		}
		
		return query;
	}
}
