package HelloWorld;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

	public static void main(String[] args) {
		
		//Use the main() method just for testing DBConnection methods
		
		//Testing insertIntoDB method
		boolean inserted = insertUserIntoDB("JoeyOggie", "Youssef Wagieh", "201099824282", "8920022041007579187", "unknown");

		if(inserted == true)
		{
			System.out.println("User info inserted!");
		}
		else
		{
			System.out.println("User info NOT inserted, already in DB!");
		}

		//Testing getRegisterationID method
		String ID = null;
		ID = getRegisterationID("JoeyOggie");
		System.out.println("ID = " + ID);
		
		//Testing getInfo method
		System.out.println(getInfo("8920022041007579187"));
	}

	public static String getRegisterationID(String userName) {
		String regID = null;
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/project1";
		// Database credentials
		String USER = "joey";
		String PASS = "abcd1234";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			
			prepstmt = conn.prepareStatement("SELECT regID FROM users WHERE userName=?");
			prepstmt.setString(1, userName);
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				regID = rs.getString("regID");
			}
			// STEP 6: Clean-up environment
			rs.close();
			prepstmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (prepstmt != null)
					prepstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return regID;
	}
	
	public static String getUserName(String deviceID) {
		String userName = null;
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/project1";
		// Database credentials
		String USER = "joey";
		String PASS = "abcd1234";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			
			prepstmt = conn.prepareStatement("SELECT userName FROM users WHERE deviceID=?");
			prepstmt.setString(1, deviceID);
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				userName = rs.getString("userName");
			}
			// STEP 6: Clean-up environment
			rs.close();
			prepstmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (prepstmt != null)
					prepstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return userName;
	}

	public static boolean insertUserIntoDB(String userName, String name, String phoneNumber, String deviceID,
			String regID) {
		boolean canInsert = true;

		//JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/project1";
		//Database credentials
		String USER = "joey";
		String PASS = "abcd1234";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			prepstmt = conn.prepareStatement("Select * from users WHERE phoneNumber=?");
			prepstmt.setString(1, phoneNumber);

			ResultSet rs = prepstmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("phoneNumber")!=null)
				{		
					canInsert = false;
				}
				else
				{
					canInsert = true;
				}
			}
			prepstmt.close();
			
			if(canInsert == true)
			{
				//STEP 4: Execute a query
				prepstmt = conn.prepareStatement("INSERT INTO users VALUES (0, ?, ?, ?, ?, ?)");
				prepstmt.setString(1, userName);
				prepstmt.setString(2, name);
				prepstmt.setString(3, phoneNumber);
				prepstmt.setString(4, deviceID);
				prepstmt.setString(5, regID);

				prepstmt.executeUpdate();
				prepstmt.close();

			}

			// STEP 6: Clean-up environment
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (prepstmt != null)
					prepstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return canInsert;
	}
	
	public static String getInfo(String deviceID) {
		String userName = null;
		String name = null;
		String phoneNumber = null;
		String regID = null;
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/project1";
		// Database credentials
		String USER = "joey";
		String PASS = "abcd1234";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			
			prepstmt = conn.prepareStatement("SELECT userName, name, phoneNumber, deviceID, regID FROM users WHERE deviceID=?");
			prepstmt.setString(1, deviceID);
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				userName = rs.getString("userName");
				name = rs.getString("name");
				phoneNumber = rs.getString("phoneNumber");
				regID = rs.getString("regID");
			}
			// STEP 6: Clean-up environment
			rs.close();
			prepstmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (prepstmt != null)
					prepstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		return "User info:\nUsername: "+userName+"\nName: "+name+"\nPhone number: "+phoneNumber+"\nDeviceID: "+deviceID+"\nRegisteration ID: "+regID;
	}
	
	public static void insertMessageIntoDB(String senderUserName, String recepientRegID, String message) {

		//JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/project1";
		//Database credentials
		String USER = "joey";
		String PASS = "abcd1234";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			
			//STEP 4: Execute a query
			prepstmt = conn.prepareStatement("INSERT INTO messagestemp VALUES (0, ?, ?, ?, 0)");
			prepstmt.setString(1, senderUserName);
			prepstmt.setString(2, recepientRegID);
			prepstmt.setString(3, message);

			prepstmt.executeUpdate();
			prepstmt.close();
			
			
			// STEP 6: Clean-up environment
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (prepstmt != null)
					prepstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
	public static List<Message> selectMessagesToSend()
	{
		//Preferably use Select For Update to prevent them from being selected again
		//until they are set as sent in the DB
		List<Message> messages = new ArrayList<Message>();
		//Select the first unsent (processed=0) message from the DB
		int messageID = -1;
		String senderUserName = null;
		String recepientRegID = null;
		String message = null;
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/project1";
		// Database credentials
		String USER = "joey";
		String PASS = "abcd1234";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			
			prepstmt = conn.prepareStatement("SELECT * FROM messagestemp WHERE state=0");
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				messageID = rs.getInt("messageID");
				senderUserName = rs.getString("senderUserName");
				recepientRegID = rs.getString("recepientRegID");
				message = rs.getString("content");
				//Add message to a new Message object and insert it into a list to be returned
				Message msg = new Message(messageID, senderUserName, recepientRegID, message);
				messages.add(msg);
				//System.out.println("Message ID: "+Integer.toString(messageID)+"\nSender: "+senderUserName);
				//System.out.println("Message ID: "+Integer.toString(messageID)+"\nSender: "+senderUserName+"\nRecepient: "+recepientRegID+"\nMessage: "+message);
			}
			// STEP 6: Clean-up environment
			rs.close();
			prepstmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (prepstmt != null)
					prepstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return messages;
		//return "Message ID: "+Integer.toString(messageID)+"\nSender: "+senderUserName+"\nRecepient: "+recepientRegID+"\nMessage: "+message;
	}
	
	public static void updateMessagesState(List<Message> messages)
	{
		//Update the DB with the received messages list
		
		Message message = new Message();
		
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/project1";
		// Database credentials
		String USER = "joey";
		String PASS = "abcd1234";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			
			//Iterate through the list and update the DB where state=1
			conn.setAutoCommit(false);
			int i = 0;
			prepstmt = conn.prepareStatement("Update messagestemp Set state=? WHERE messageID=?");
			while(i < messages.size())
			{
				message = messages.get(i);
				prepstmt.setInt(1, message.getState());		
				prepstmt.setInt(2, message.getMessageID());
				prepstmt.addBatch();
				i++;
			}
			
			int [] updateCounts = prepstmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);

			// STEP 6: Clean-up environment
			prepstmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (prepstmt != null)
					prepstmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
	}
	
}
