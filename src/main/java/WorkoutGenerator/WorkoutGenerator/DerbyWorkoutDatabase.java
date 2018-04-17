package WorkoutGenerator.WorkoutGenerator;

import java.util.*;
import java.sql.*;

/*
 * A database interface that implements the required 
 * WorkoutDatabaseInterface. Used for several purposes:
 * - Creating HashMaps of object data to be turned into
 * 		Exercise Objects
 * - Verifying table and database existence. 
 * - Instantiating base data into tables once
 * 		existence is verified.
 */

public class DerbyWorkoutDatabase implements WorkoutDatabaseInterface {
	
	// The Connection object used to make Statement object. 
	// Closed after each transaction. 
	Connection conn;
	
	/*
	 * (non-Javadoc)
	 * @see WorkoutGenerator.WorkoutGenerator.WorkoutDatabaseInterface#getExerciseData()
	 * 
	 * Method: getExerciseData
	 * Input: Optional, method overloaded, none necessary
	 * Output: HashMap of Exercise-object related data from SQL table
	 * 
	 * Desc: An overloaded method for getting data out of SQL tables. 
	 * Establishes a connection by calling getDBConnection, which assigns
	 * the class Connection "conn." Then, uses a Statement object to get row data, which
	 * is then Hashed. 
	 * 
	 * Overloaded methods distinguish by various fields. 
	 */
	public HashMap getExerciseData() {
		getDBConnection();
		HashMap<String, Object> exMap = new HashMap<String, Object>();
		try {
			
			System.out.println("Getting data for Exercise...");
			System.out.println("Creating statement...");
			Statement stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			
			//Get result set
			System.out.println("Getting ResultSet Object from Exercisetable...");
			ResultSet rs = stmt.executeQuery("SELECT * FROM EXERCISETABLE");
			
			
			//Get number of exercises in query
			int rsSize = getResultSetSize(rs);
			
			//Assign cursor to first place
			rs.next();
			
			// Get a random number
			Random random = new Random();
			int rando = random.nextInt(rsSize);
			
			for (int i = 0; i < rando; i++) {
				rs.next();
			}
			
			populateHashMap(rs, exMap);
			
			
		} catch (SQLException e) {
			System.out.println("SQLException caught in getExerciseData!");
			e.printStackTrace();
		}
		
		System.out.println("ExMap: " + exMap.toString());
		return exMap;
	}
	
	//Gets a connection to the DB using the Derby Embedded Driver
	private void getDBConnection() {
		try {	
		this.conn = DriverManager.getConnection("jdbc:derby:workoutdb;create=true");
		verifyTables();
		} catch (SQLException e) {
			System.out.println("SQLException caught when trying to connect to DB!");
			e.printStackTrace();
		}
	}
	
	/*
	 * Method: verifyTables
	 * Input: Class Connection instance, conn, but no args
	 * Output: One of three options:
	 * - None, if no exceptions are thrown and tables have already been
	 * 		created.
	 * - New tables with base data, if no exceptions are thrown and tables
	 * 		have not yet been created. 
	 * - A SQLException, if something goes horribly awry. 
	 */
	private void verifyTables() {
		System.out.println("Checking table status...");
		
		try {
			System.out.println("Getting meta data... ");
			DatabaseMetaData meta = this.conn.getMetaData();
			
			System.out.println("Getting ResultSet object....");
			ResultSet metaRS = meta.getTables(null, null, null, new String[] {"TABLE"});
			
			//Checks tables for existence, calling create method if not
			if (!metaRS.next()) {
				createNewTables();
			}
		
		} catch (Exception e) {
			System.out.println("SQLException caught when verifying tables...");
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Method: createNewTables
	 * Input: The Class instance of the Connection, conn
	 * Output: New data tables in a database with an established connection.
	 * 
	 * Desc: Called only when tables have not yet been instantiated in a new 
	 * database. 
	 */
	private void createNewTables() {
		try {
			//Creates basic table
			System.out.println("Creating new tables...");
			Statement stmt = this.conn.createStatement();
			
			//For testing, drops tables on instantiation
			stmt.executeUpdate("DROP TABLE EXERCISETABLE");
			
			stmt.execute("CREATE TABLE EXERCISETABLE (ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
					+ "NAME VARCHAR(100), WEIGHT INT, "
					+ "SETS INT, REPS INT, NOTES VARCHAR(255), TYPE VARCHAR(100))");
			System.out.println("New tables created.");
			
			//Populates table with basic data
			System.out.println("Populating table with basic data...");
			stmt.executeUpdate("INSERT INTO EXERCISETABLE (NAME, WEIGHT, SETS, REPS, NOTES, TYPE)"
					+ " VALUES ('Push-ups', 0, 5, 10, 'Keep elbows in!', 'Push')");
			stmt.executeUpdate("INSERT INTO EXERCISETABLE (NAME, WEIGHT, SETS, REPS, NOTES, TYPE)"
					+ " VALUES ('Pull-ups', 0, 5, 5, 'Smooth and easy!', 'Pull')");
			stmt.executeUpdate("INSERT INTO EXERCISETABLE (NAME, WEIGHT, SETS, REPS, NOTES, TYPE)"
					+ " VALUES ('Squats', 120, 5, 8, 'Keep booty out!!', 'Legs')");
			stmt.executeUpdate("INSERT INTO EXERCISETABLE (NAME, WEIGHT, SETS, REPS, NOTES, TYPE)"
					+ " VALUES ('Two- Handed Curls', 40, 5, 10, '', 'Pull')");
			stmt.executeUpdate("INSERT INTO EXERCISETABLE (NAME, WEIGHT, SETS, REPS, NOTES, TYPE)"
					+ " VALUES ('Deadlifts', 120, 5, 8, 'Watch form!', 'Legs')");
			
		} catch (SQLException e) {
			System.out.println("SQLException caught when instantiating new tables.");
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Method: populateHashMap
	 * Input: A ResultSet object rs and HashMap hm
	 * Output: a populated HashMap
	 * 
	 * Desc: Pairs the keys necessary for exercises to be made with the 
	 * values stored in the ResultSet. 
	 * 
	 * ASSUMES THE RESULTSET CURSOR IS ON THE DESIRED ROW
	 */
	private void populateHashMap(ResultSet rs, HashMap<String, Object> hm) throws SQLException {
		hm.put("name", rs.getString(2));
		hm.put("weight", rs.getInt(3));
		hm.put("sets", rs.getInt(4));
		hm.put("reps", rs.getInt(5));
		hm.put("notes", rs.getString(6));
		hm.put("type", rs.getString(7));
	}
	
	
	/*
	 * Method: getResultSetSize
	 * Input: ResultSet rs
	 * Output: int size of the ResultSet
	 * 
	 * Desc: 
	 */
	private int getResultSetSize(ResultSet rs) throws SQLException {
		rs.last();
		int size = rs.getRow();
		rs.beforeFirst();
		return size;
		
	}

}
