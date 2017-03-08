package database;

public class DatabaseTest {

	public static void main(String[] args) {
		String driver = "org.sqlite.JDBC";
		//Need to change path for your system. Trying to work around this so that it'll recognise it, in the networking directory
		String database = "jdbc:sqlite:C:/Users/arjybarji/Desktop/Uni/Year 2/g3/team-project/src/database/armigerWarsCompleteTest.db";

		Database test = new Database(database,driver);

//		test.insertIntoPlayer("user1", "password1",1);
//		test.insertIntoPlayer("admin1", "password2",0);
//		test.insertIntoOnline(1, 7, 4, 2, 1);
//		test.insertIntoOnline(2, 9, 4, 3, 2);
//		test.insertIntoOffline(1, 10, 3, 4, 3);
//		test.insertIntoOffline(2, 4, 3, 1, 0);
		
//		test.updateOfflineTable(2, 5, 3, 1, 1);
//		test.updateOnlineTable(1, 8, 5, 2, 1);
//		test.updatePlayerPassword(1, "password3");
//		test.updatePlayerPrivilege(1, 0);
//		test.updatePlayerPrivilege("admin1", 1);
//		test.updatePlayerPassword("user2", "password4");
		
		System.out.println(test.getID("user2"));
		System.out.println(test.getUsername(2));
		System.out.println(test.selectOfflineLeaderboard());
		System.out.println(test.selectOnlineLeaderboard());
		System.out.println(test.usernameTaken("user1"));
		System.out.println(test.selectRecord("offline", "user1"));
		System.out.println(test.selectRecord("online", "user2"));		
		System.out.println(test.getAdmins());
		System.out.println(test.getUsers());
		
	}

}
