package dbLayer;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;


public class DBConnection {
	private static final String driver = "jdbc:sqlserver://balder.ucn.dk";
	private static final String dbName = ";databaseName=dmaa0214Projekt_3";

	private static String userName = "; user=dmaa0214TProjekt_3";
	private static String password = ";password=Biksemad";

	private DatabaseMetaData dma;
	private static Connection con;

	private static DBConnection instance;
	
	/**
	 * Initialisering  Connection to Database
	 * @ NullPointer
	 * 
	 */
	public DBConnection() throws NullPointerException {
		String url = driver + dbName + userName + password;

		// Loading driver
		try {
			System.out.println("Loading drivers");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Loading class OK!");
		} catch (Exception e) {
			System.out.println("Driver not found");
			e.printStackTrace();
		}

		/**
		 * connection to DB
		 */
		try {
			con = DriverManager.getConnection(url);
			dma = con.getMetaData();
			System.out.println("Conection to " + dma.getURL());
			System.out.println("Driver " + dma.getDriverName());
			System.out.println("Database product name "
					+ dma.getDatabaseProductName());
			System.out.println("-----");
			System.out.println("Connection Successfully established");
		} catch (Exception e) {
			System.out.println("Error while connecting to the Database");
			e.printStackTrace();
			throw new NullPointerException("Error DBConnection");
		}
	}
	/**
	 * closing connection
	 */
	public static void closeConnection() {
		try {
			con.close();
			System.out.println("Connection closed!");
		} catch (Exception e) {
			System.out.println("Error closing the connection: "
					+ e.getMessage());
		}
	}

  /**
  * get the connection to DB
  * @ connection to DB
  */
	public static DBConnection getInstance() {
		if (instance == null) {
			try {
				instance = new DBConnection();
			} catch (NullPointerException e) {
				System.out.println(e.getMessage());
				instance = null;
			}
		}

		return instance;
	}
	/**
	 * starting of the  transaction
	 */
	public static void startTransaction() {
		try { con.setAutoCommit(false);
	}catch (Exception e) {
		System.out.println("error starting transaction"+ e.getMessage());
	 }
	}
	
	/**
	 * commit transaction
	 */
	public static void commitTransaction() {
		try {
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out
					.println("Error commiting Transaction: " + e.getMessage());
		}
	}

	/**
	 * Rollback Transaction
	 */
	public static void rollBackTransaction() {
		try {
			con.rollback();
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("Error executing Rollback on transaction "
					+ e.getMessage());
		}
	}

}
	

