package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.PartSale;
import modelLayer.Product;
import modelLayer.Sale;
import dbLayer.interfaceLayer.IFDBPartSale;
import dbLayer.interfaceLayer.IFDBProduct;
import exceptions.DBException;
import exceptions.DBNotFoundException;

/**
 * Class for DBPartSale
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBPartSale implements IFDBPartSale {

	private Connection conn;

	public DBPartSale() {
		conn = DBConnection.getInstance().getDBCon();
	}
	
	@Override
	public ArrayList<PartSale> getPartSales(Sale sale, boolean retAsso) {
		return miscWhere("SaleID = " + sale.getId(), retAsso);
	}

	@Override
	public int insertPartSale(Sale sale, PartSale partSale) throws DBException {
		int rc = -1;

		try {
			String query = "INSERT INTO PartSale (SaleID, ProductID,"
					+ " Amount, Price) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setQueryTimeout(5);
			
			stmt.setInt(1, sale.getId());
			stmt.setInt(2, partSale.getProduct().getId());
			stmt.setDouble(3, partSale.getAmount());
			stmt.setDouble(4, partSale.getUnitPrice());
			
			rc = stmt.executeUpdate();
			
			ResultSet genRs = stmt.getGeneratedKeys();
			if(genRs.next()) {
				partSale.setId(genRs.getInt(1));
				System.out.println("Generated PartSale ID: " + genRs.getInt(1));
			}
			
			stmt.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DBException("DelSalg", e);
		}
	
		return rc;
	}

	@Override
	public int updatePartSale(PartSale partSale) throws DBException {
		int rc = -1;
		
		try {
			String query = "UPDATE PartSale SET Amount = ?,"
					+ " Price = ? WHERE PartSaleID = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			stmt.setDouble(1, partSale.getAmount());
			stmt.setDouble(2, partSale.getUnitPrice());
			stmt.setInt(3, partSale.getId());
			
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DBException("DelSalg", e);
		}
		
		if(rc == 0){
			throw new DBNotFoundException("DelSalg", 2);
		}
		
		return rc;
	}

	@Override
	public int deletePartSale(PartSale partSale) throws DBException {
		int rc = -1;
		
		try {
			String query = "DELETE FROM PartSale WHERE"
					+ " PartSaleID=" + partSale.getId();
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			
			rc = stmt.executeUpdate(query);
			
			stmt.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DBException("DelSalg", e);
		}
		
		if(rc == 0){
			throw new DBNotFoundException("DelSalg", 3);
		}
		
		return rc;
	}

	private ArrayList<PartSale> miscWhere(String wQuery, boolean retAsso) {
		ArrayList<PartSale> retList = new ArrayList<PartSale>();
		
		try {
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				PartSale ps = buildPartSale(rs);
				if (retAsso) {
					IFDBProduct dbP = new DBProduct();
					Product p = dbP.getProductByID(ps.getProduct().getId());
					ps.setProduct(p);
				}
				retList.add(ps);
			}
			
			stmt.close();
		} catch(Exception e) {
			System.out.println("DBPartSale - Exception : miscWhere");
			e.printStackTrace();
		}
		
		return retList;
	}

	private PartSale buildPartSale(ResultSet rs) {
		PartSale ps = null;
		try {
			ps = new PartSale();
			ps.setId(rs.getInt("PartSaleID"));
			ps.setAmount(rs.getDouble("Amount"));
			ps.setUnitPrice(rs.getDouble("Price"));
			ps.setProduct(new Product(rs.getInt("ProductID")));
		} catch(Exception e) {
			System.out.println("Error in building PartSale object.");
		}
		return ps;
	}

	private String buildQuery(String wQuery) {
		String query = "SELECT PartSaleID, SaleID, ProductID,"
				+ " Amount, Price FROM PartSale";
		if(!wQuery.isEmpty()) {
			query += " WHERE " + wQuery;
		}
		return query;
	}

}
