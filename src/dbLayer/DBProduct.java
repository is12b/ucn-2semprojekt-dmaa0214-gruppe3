package dbLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Product;
import modelLayer.UnitType;
import dbLayer.exceptions.DBException;
import dbLayer.exceptions.DBNotFoundException;
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
		return miscWhere("ItemNumber LIKE '%" + itemNumber + "%' AND Hidden=0");
	}

	@Override
	public ArrayList<Product> searchProductsByName(String name) {
		return miscWhere("Name LIKE '%" + name + "%' AND Hidden=0");
	}

	@Override
	public Product getProductByID(int id) throws NullPointerException {
		return singleWhere("ProductID = " + id);
	}

	@Override
	public int insertProduct(Product product) throws DBException {
		int rc = -1;
		
		try{
			String query = "INSERT INTO PRODUCT "
					+ "(ItemNumber, Brand, Name, Description, Price, UnitType) "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setQueryTimeout(5);
			
			if(product.getItemNumber() == null || product.getItemNumber().isEmpty()){
				stmt.setNull(1, java.sql.Types.NULL);
			}else{
				stmt.setString(1, product.getItemNumber());
			}
			
			if(product.getBrand() == null || product.getBrand().isEmpty()){
				stmt.setNull(2, java.sql.Types.NULL);
			}else{
				stmt.setString(2, product.getBrand());
			}

			stmt.setString(3, product.getName());
			
			if(product.getDescription() == null || product.getDescription().isEmpty()){
				stmt.setNull(4, java.sql.Types.NULL);
			}else{
				stmt.setString(4, product.getDescription());
			}
			
			stmt.setDouble(5, product.getPrice());
			stmt.setString(6, product.getUnitType().getShortDescription());
			rc = stmt.executeUpdate();
			
			ResultSet genRs = stmt.getGeneratedKeys();
			if(genRs.next()){
				product.setId(genRs.getInt(1));
			}
			
			stmt.close();
		} catch(SQLException e) {
			//System.out.println("DBProduct - insertProduct - Exception");
			//e.printStackTrace();
			throw new DBException("Produktet", e);
		}
		
		return rc;
	}

	@Override
	public int updateProduct(Product product) throws DBException {
		int rc = -1;
		
		try{ //(ProductID, ItemNumber, Brand, Name, Description, Price, Hidden, UnitType)
			String query = "UPDATE PRODUCT SET " 
					+ "ItemNumber = ?, Brand = ?, Name = ?, Description = ?, " 
					+ "Price = ?, Hidden = ?, UnitType = ? WHERE ProductID = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);

			if(product.getItemNumber() == null || product.getItemNumber().isEmpty()){
				stmt.setNull(1, java.sql.Types.NULL);
			}else{
				stmt.setString(1, product.getItemNumber());
			}
			
			if(product.getBrand() == null || product.getBrand().isEmpty()){
				stmt.setNull(2, java.sql.Types.NULL);
			}else{
				stmt.setString(2, product.getBrand());
			}

			stmt.setString(3, product.getName());
			
			if(product.getDescription() == null || product.getDescription().isEmpty()){
				stmt.setNull(4, java.sql.Types.NULL);
			}else{
				stmt.setString(4, product.getDescription());
			}
			
			stmt.setDouble(5, product.getPrice());
			stmt.setBoolean(6, product.isHidden());
			stmt.setString(7, product.getUnitType().getShortDescription());
			stmt.setInt(8, product.getId());
			
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch(SQLException e) {
			System.out.println("DBProduct - updateProduct - Exception");
			//e.printStackTrace();
			throw new DBException("Produktet", e);
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Enhedstypen", 2);
		}
		
		return rc;
	}

	@Override
	public int deleteProduct(Product product) throws DBException {
		int rc = -1;
		
		try{
			String query = "DELETE FROM PRODUCT WHERE ProductID = " + product.getId();
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}catch(SQLException e){ //TODO edited from SQLServerException
			//Foreign key error
			if(e.getErrorCode() == 547){
				product.setHidden(true);
				rc = updateProduct(product);
				System.out.println("Product " + product.getId() + " is now hidden");
			}else{
				//System.out.println("DBProduct - deleteProduct - Exception");
				//e.printStackTrace();
				throw new DBException("Produktet", e);
			}
		}
		
		if (rc == 0) {
			throw new DBNotFoundException("Enhedstypen", 3);
		}
		
		return rc;
	}
	
	private ArrayList<Product> miscWhere(String wQuery){
		ArrayList<Product> products = new ArrayList<Product>();
		
		try{
			String query = buildQuery(wQuery);
			System.out.println(query);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Product product = buildProduct(rs);
				if (product != null) {
					products.add(product);
				}
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
		Product product = null;
		
		try{
			product = new Product(rs.getInt("ProductID"));
			product.setBrand(rs.getString("Brand"));
			product.setDescription(rs.getString("Description"));
			product.setHidden(rs.getBoolean("Hidden"));
			product.setItemNumber(rs.getString("ItemNumber"));
			product.setName(rs.getString("Name"));
			product.setPrice(rs.getDouble("Price"));
			
			IFDBUnitType dbUT = new DBUnitType();
			UnitType ut = dbUT.getUnitType(product.getUnitType().getShortDescription());
			if(ut == null) {
				throw new NullPointerException("Enhedstypen blev ikke fundet");
			}
			product.setUnitType(ut);
						
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
