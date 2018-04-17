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
	
	
	public HashMap getExerciseData() {
		getDBConnection();
		HashMap exMap = new HashMap();
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
	
	//Verifies that the DB contains data, and instantiates some if not
	private void verifyTables() {
		System.out.println("Checking table status...");
		try {
			DatabaseMetaData meta = this.conn.getMetaData();
			ResultSet metaRS = meta.getTables(null, null, null, new String[] {"TABLE"});
			if (!metaRS.next()) {
				createNewTables();
			}
		
		} catch (SQLException e) {
			System.out.println("SQLException caught when verifying tables...");
			e.printStackTrace();
		}
		
	}
	
	//Method to create new tables if older ones don't exist. 
	private void createNewTables() {
		try {
			//Creates basic table
			System.out.println("Creating new tables...");
			Statement stmt = this.conn.createStatement();
			stmt.execute("CREATE TABLE EXERCISETABLE (ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY "
					+ "(START WITH 1, INCREMENT BY 1), "
					+ "NAME VARCHAR(100), WEIGHT INT, "
					+ "SETS INT, REPS INT, NOTES TEXT, TYPE VARCHAR(100))");
			System.out.println("New tables created.");
			
			//Populates table with basic data
			System.out.println("Populating table with basic data...");
			stmt.executeUpdate("INSERT INTO EXERCISETABLE VALUES ('Push-ups', 0, 5, 10, 'Keep elbows in!', 'Push')");
			stmt.executeUpdate("INSERT INTO EXERCISETABLE VALUES ('Pull-ups', 0, 5, 5, 'Smooth and easy!', 'Pull')");
			
			
		} catch (SQLException e) {
			System.out.println("SQLException caught when instantiating new tables.");
			e.printStackTrace();
		}
		
	}
	

}
