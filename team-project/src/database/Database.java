package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Arjun Lotay
 * Database Class with Insert, Update and Select methods
 *
 */
public class Database {

	String database;
	String driver;
	
	public Database(String database, String driver){
		this.database = database;
		this.driver = driver;
	}
	
	//Inserts value into player table
	public void insertIntoPlayer(String username, String password,int admin){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO player (USERNAME,PASSWORD,ADMIN) VALUES ('" + username +"','" + password + "'," + admin + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	//Inserts values into offline table
	public void insertIntoOffline(int pid, int gamesPlayed,int wins, int draws, int losses){
		int score = (wins * 3) + (draws * 1) + (losses * 0);
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO offline (PID, GAMESPLAYED,WINS,DRAWS,LOSSES,SCORE) VALUES "
					+ "(" + pid + "," + gamesPlayed + "," + wins + "," + draws + "," + losses + "," + score + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}
	
	//Inserts Values into online table
	public void insertIntoOnline(int pid, int gamesPlayed,int wins, int draws, int losses){
		int score = (wins * 3) + (draws * 1) + (losses * 0);
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO online (PID, GAMESPLAYED,WINS,DRAWS,LOSSES,SCORE) VALUES "
					+ "(" + pid + "," + gamesPlayed + "," + wins + "," + draws + "," + losses + "," + score + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}
	
	//Updates players password using their username given
	public void updatePlayerPassword(String username, String password){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE player set PASSWORD = '" + password + "' WHERE USERNAME = '" + username + "';" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records updated successfully");
	}
	
	//Updates players password using their id
	public void updatePlayerPassword(int pid, String password){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE player set PASSWORD = '" + password + "' WHERE PID = " + pid + ";" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records updated successfully");
	}
	
	public void updatePlayerPrivilege(String username, int admin){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE player set ADMIN = '" + admin + "' WHERE USERNAME = '" + username + "';" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records updated successfully");
	}
	
	public void updatePlayerPrivilege(int pid, int admin){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE player set ADMIN = '" + admin + "' WHERE PID = " + pid + ";" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records updated successfully");
	}
	
	//Updates players record in offline table
	public void updateOfflineTable(int pid, int gamesPlayed, int wins, int draws, int losses){
		int score = (wins * 3) + (draws * 1) + (losses * 0);
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE offline SET gamesPlayed = " + gamesPlayed + ", wins = " + wins + ", draws = " + draws + ", losses = " 
			+ losses + ", score = " + score + " WHERE pid = " + pid + ";" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records updated successfully");
	}
	
	//Updates players record in online table
	public void updateOnlineTable(int pid, int gamesPlayed, int wins, int draws, int losses){
		int score = (wins * 3) + (draws * 1) + (losses * 0);
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "UPDATE online SET gamesPlayed = " + gamesPlayed + ", wins = " + wins + ", draws = " + draws + ", losses = " 
			+ losses + ", score = " + score + " WHERE pid = " + pid + ";" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records updated successfully");
	}
	
	//Returns players username given an id
	public String getUsername(int pid){
		Connection c = null;
		Statement stmt = null;
		String username = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT username FROM PLAYER WHERE pid = " + pid +" ;" );
			while(rs.next()){
				username = rs.getString("username");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return username;
	}

	//Returns players id given their name
	public int getID(String username){
		Connection c = null;
		Statement stmt = null;
		int id = 0;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT pid FROM PLAYER WHERE username = '" + username +"' ;" );
			while(rs.next()){
				id = rs.getInt("pid");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return id;
	}

	//Checks if a given username is taken
	public boolean usernameTaken(String username){
		boolean taken = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT username FROM PLAYER;" );
			while(rs.next()){
				String current = rs.getString("username");	    	  
				if(current.equals(username)){
					taken = true;
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return taken;
	}

	//Gets players record for online and offline, table name needs to be passed as parameter
	public String selectRecord(String table, String username){
		int pid = getID(username);
		String record = username ;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM " + table + " WHERE pid = " + pid + ";");
			while(rs.next()){
				record = record + "," + rs.getInt("gamesPlayed");
				record = record + "," + rs.getInt("wins");
				record = record + "," + rs.getInt("draws");
				record = record + "," + rs.getInt("losses");
				record = record + "," + rs.getInt("score");

			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		return record;
	}


	//Returns the offline leaderboard in order of score descending
	public String selectOfflineLeaderboard(){
		String leaderboard = "";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT offline.gamesPlayed,offline.wins,offline.draws,offline.losses, "
					+ "offline.score,player.username "
					+ "FROM player JOIN offline ON player.pid = offline.pid ORDER BY offline.score DESC;" );
			while(rs.next()){
				leaderboard = leaderboard + rs.getString("username") + ",";
				leaderboard = leaderboard + rs.getString("gamesPlayed") + ",";
				leaderboard = leaderboard + rs.getString("wins") + ",";
				leaderboard = leaderboard + rs.getString("draws") + ",";
				leaderboard = leaderboard + rs.getString("losses") + ",";
				leaderboard = leaderboard + rs.getString("score") + ",";

			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		return leaderboard;
	}

	//Returns the offline leaderboard in order of score descending
	public String selectOnlineLeaderboard(){
		String leaderboard = "";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT online.gamesPlayed,online.wins,online.draws,online.losses, "
					+ "online.score,player.username "
					+ "FROM player JOIN online ON player.pid = online.pid ORDER BY online.score DESC;" );
			while(rs.next()){
				leaderboard = leaderboard + rs.getString("username") + ",";
				leaderboard = leaderboard + rs.getString("gamesPlayed") + ",";
				leaderboard = leaderboard + rs.getString("wins") + ",";
				leaderboard = leaderboard + rs.getString("draws") + ",";
				leaderboard = leaderboard + rs.getString("losses") + ",";
				leaderboard = leaderboard + rs.getString("score") + ",";

			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return leaderboard;

	}
	
	public String getAdmins(){
		String admins = "";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM player WHERE ADMIN = 1;" );
			while(rs.next()){
				admins = admins + rs.getString("pid") + ",";
				admins = admins + rs.getString("username") + ",";

			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return admins;
	}
	
	public String getUsers(){
		String users = "";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM player WHERE ADMIN = 0;" );
			while(rs.next()){
				users = users + rs.getString("pid") + ",";
				users = users + rs.getString("username") + ",";

			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return users;
	}
	
	public boolean loginSafe(String username, String password){
		boolean userThere = false;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			c = DriverManager.getConnection(database);
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM player ;" );
			while(rs.next()){
				if( (rs.getString("username").equals(username) && rs.getString("password").equals(password))){
					userThere = true;
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		return userThere;
	}
}
