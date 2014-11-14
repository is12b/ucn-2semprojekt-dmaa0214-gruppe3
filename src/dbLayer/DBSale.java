package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Car;
import modelLayer.Customer;
import modelLayer.PartSale;
import modelLayer.Sale;
import dbLayer.interfaceLayer.IFDBCar;
import dbLayer.interfaceLayer.IFDBCustomer;
import dbLayer.interfaceLayer.IFDBPartSale;
import dbLayer.interfaceLayer.IFDBSale;

/**
 * Class for DBSale
 *
 * @author Group 3, dmaa0214, UCN
 *
 */
public class DBSale implements IFDBSale {
	
	private Connection conn;

	public DBSale() {
		conn = DBConnection.getInstance().getDBCon();
	}
	
	@Override
	public ArrayList<Sale> getSales(Car car) {
		return miscWhere("CarID = " + car.getId());
	}

	@Override
	public ArrayList<Sale> getSales(Customer customer) {
		return miscWhere("CustomerID = " + customer.getId());
	}

	@Override
	public Sale getSale(int id) {
		return singleWhere("SaleID = " + id);
	}

	@Override
	public ArrayList<Sale> getAllSales() {
		return miscWhere("");
	}

	@Override
	public int insertSale(Sale sale) {
		int rc = -1;
		
		try {
			DBConnection.startTransaction();
			
			String query = "INSERT INTO Sale (CarID,"
					+ " CustomerID, Paid, Description,"
					+ " Mileage) VALUES (?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, 
					Statement.RETURN_GENERATED_KEYS);
			stmt.setQueryTimeout(5);
			
			if (sale.getCar() != null) {
				stmt.setInt(1, sale.getCar().getId());
			} else {
				stmt.setNull(1, java.sql.Types.INTEGER);
			}
			
			if (sale.getCustomer() != null) {
				stmt.setInt(2, sale.getCustomer().getId());
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			
			stmt.setBoolean(3, sale.isPaid());
			stmt.setString(4, sale.getDescription());
			stmt.setInt(5, sale.getMileage());
			
			rc = stmt.executeUpdate();
			
			ResultSet genRs = stmt.getGeneratedKeys();
			if (genRs.next()) {
				sale.setId(genRs.getInt(1));
				System.out.println("Generated SaleID: " + genRs.getInt(1));
			}
			
			IFDBPartSale dbPS = new DBPartSale();
			for (PartSale ps : sale.getPartSales()) {
				int rows = dbPS.insertPartSale(sale, ps);
				if (rows <= 0) {
					throw new NullPointerException("DBSale: Insert partsale");
				}
			}
			
			stmt.close();
			
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollBackTransaction();
			System.out.println("DBSale insert exception: ");
			e.printStackTrace();
		}
		
		return rc;
	}

	@Override
	public int updateSale(Sale sale) {
		int rc = -1;
		
		try {
			String query = "UPDATE Sale SET CarID = ?, CustomerID = ?,"
				+ " Date = ?, PaymentDeadline = ?, Paid = ?,"
				+ " Description = ?, Mileage = ? WHERE SaleID = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setQueryTimeout(5);
			
			if (sale.getCar() != null) {
				stmt.setInt(1, sale.getCar().getId());
			} else {
				stmt.setNull(1, java.sql.Types.INTEGER);
			}
			
			if (sale.getCustomer() != null) {
				stmt.setInt(2, sale.getCustomer().getId());
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			
			if (sale.getDate() != null) {
				stmt.setDate(3, new java.sql.Date(sale.getDate()
						.getTime()));
			} else {
				stmt.setNull(3, java.sql.Types.DATE);
			}
			
			if (sale.getPaymentDeadline() != null) {
				stmt.setDate(4, new java.sql.Date(sale.getPaymentDeadline()
						.getTime()));
			} else {
				stmt.setNull(4, java.sql.Types.DATE);
			}
			
			stmt.setBoolean(5, sale.isPaid());
			stmt.setString(6, sale.getDescription());
			stmt.setInt(7, sale.getMileage());
			stmt.setInt(8, sale.getId());
			
			rc = stmt.executeUpdate();
			
			stmt.close();
		} catch (Exception e) {
			System.out.println("DBSale update exception: ");
			e.printStackTrace();
		}
		
		return rc;
	}

	@Override
	public int deleteSale(Sale sale) {
		int rc = -1;
		
		try {
			String query = "DELETE FROM Sale WHERE SaleID=" + sale.getId();
			
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			
			rc = stmt.executeUpdate(query);
			
			stmt.close();
			
		} catch (Exception e) {
			System.out.println("DBSale delete exception: ");
			e.printStackTrace();
		}
		
		return rc;
	}

	private Sale singleWhere(String wQuery) {
		Sale sale = null;
		
		try {
			String query = buildQuery(wQuery);
			
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				sale = buildSale(rs);
			} 
			
			stmt.close();
		} catch (Exception e) {
			System.out.println("DBSale singleWhere exception: ");
			e.printStackTrace();
		}
		
		return sale;
	}

	private ArrayList<Sale> miscWhere(String wQuery) {
		ArrayList<Sale> retList = new ArrayList<Sale>();
		
		try {
			String query = buildQuery(wQuery);
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Sale s = buildSale(rs);
				
				if (s != null) {
					retList.add(s);
				}
			}
			
			stmt.close();
		} catch (Exception e) {
			System.out.println("DBSale micsWhere exception: ");
			e.printStackTrace();
		}
		
		return retList;
	}

	private Sale buildSale(ResultSet rs) {
		Sale s = null;
		
		try {
			s = new Sale();
			s.setId(rs.getInt("SaleID"));
			
			int carID = rs.getInt("CarID");
			Customer cus = null;
			if(carID != 0) {
				IFDBCar dbCar = new DBCar();
				Car car = dbCar.getCar(s.getCar().getId(), true);
				s.setCar(car);
				cus = car.getOwner();
			}
			
			int cusID = rs.getInt("CustomerID");
			if(cusID != 0) {
				if (cus == null) {
					IFDBCustomer dbCus = new DBCustomer();
					cus = dbCus.getCustomerByID(s.getCustomer().getId(), false);
				}
				s.setCustomer(cus);
			}
			
			s.setDate(rs.getDate("Date"));
			s.setPaymentDeadline(rs.getDate("PaymentDeadline"));
			s.setPaid(rs.getBoolean("Paid"));
			s.setDescription(rs.getString("Description"));
			s.setMileage(rs.getInt("Mileage"));
		} catch (Exception e) {
			System.out.println("Building Sale failed: ");
			e.printStackTrace();
		}
		
		return s;
	}

	private String buildQuery(String wQuery) {
		String query = "SELECT SaleID, CarID, CustomerID,"
				+ " Date, PaymentDeadline, Paid, Description,"
				+ " Mileage FROM Sale";
		if (!wQuery.isEmpty()) {
			query += " WHERE " + wQuery;
		}
		
		return query;
	}
}
