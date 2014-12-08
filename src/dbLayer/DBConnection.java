package dbLayer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

class DBConnection {
	private static final String driver = "jdbc:sqlserver://balder.ucn.dk";
	private static final String dbName = ";databaseName=dmaa0214Projekt_3";

	private static String userName = "; user=dmaa0214Projekt_3";
	private static String password = ";password=Biksemad";

	private DatabaseMetaData dma;
	private static Connection conn;

	private static DBConnection instance;

	/**
	 * Initial Connection to Database
	 *
	 * @throws NullPointerException
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

		// Connection to DB
		try {
			conn = DriverManager.getConnection(url);
			dma = conn.getMetaData();
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
	 * Closing the Connection
	 */
	static void closeConnection() { // NO_UCD (unused code)
		try {
			conn.close();
			System.out.println("Connection closed!");
		} catch (Exception e) {
			System.out.println("Error closing the connection: "
					+ e.getMessage());
		}
	}

	/**
	 * Get the Database Connection
	 *
	 * @return Connection
	 */
	public Connection getDBCon() {
		return conn;
	}

	/**
	 * Get instance of DBConnection
	 *
	 * @return DBConnection
	 */
	static DBConnection getInstance() {
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
	 * Starting Transaction
	 */
	static void startTransaction() {
		try {
			conn.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("Error starting Transaction" + e.getMessage());
		}
	}

	/**
	 * Commiting Transaction
	 */
	static void commitTransaction() {
		try {
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("Error commiting Transaction: "
					+ e.getMessage());
		}
	}

	/**
	 * Rollback Transaction
	 */
	static void rollBackTransaction() {
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("Error rollback Transaction: "
					+ e.getMessage());
		}
	}
}
