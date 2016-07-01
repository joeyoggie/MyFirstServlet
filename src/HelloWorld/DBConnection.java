package HelloWorld;

import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.*;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

//This class is responsible for handling all the database connections
//from the application server to the database server
//TODO implement C3PO connection pooling
public class DBConnection {

	//JDBC driver name and database URL
	static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/project1?useSSL=false";
	//Database credentials
	static String USER = "Joey";
	static String PASS = "abcd1234";

	public static void main(String[] args) {

		//Use the main() method just for testing DBConnection methods
		/*
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
		 */
	}

	public static int getUserID(String userName) {

		int userID = -1;

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query

			prepstmt = conn.prepareStatement("SELECT id FROM users WHERE userName=?");
			prepstmt.setString(1, userName);
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				userID = rs.getInt("id");
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

		return userID;
	}

	public static User getUserInfo(String userName) {
		User user = new User();
		String phoneNumber = null;
		String status = null;
		String name = null;
		//Get the profile picture from its table
		//Add online status to the database

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			prepstmt = conn.prepareStatement("SELECT *, status.latestStatus FROM users LEFT JOIN status ON (users.id = status.status_userID) WHERE project1.users.userName=?");
			prepstmt.setString(1, userName);
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				phoneNumber = rs.getString("phoneNumber");
				status = rs.getString("latestStatus");
				name = rs.getString("name");
			}
			user.setPhoneNumber(phoneNumber);
			user.setName(name);
			user.setStatus(status);
			user.setUserName(userName);
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

		return user;
	}

	public static String getRegisterationID(String userName) {
		String regID = null;

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

	public static String insertUserIntoDB(String userName, String name, String phoneNumber, String deviceID,
			String regID) {
		String response = "success";

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Check for phone number
			prepstmt = conn.prepareStatement("Select * from users WHERE phoneNumber=?");
			prepstmt.setString(1, phoneNumber);

			ResultSet rs = prepstmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("phoneNumber")!=null)
				{		
					response = "phoneNumber already registered";
				}
			}
			prepstmt.close();

			//Check for username
			prepstmt = conn.prepareStatement("Select * from users WHERE userName=?");
			prepstmt.setString(1, userName);

			rs = prepstmt.executeQuery();

			while (rs.next()) {
				if(rs.getString("userName")!=null)
				{		
					response = "userName already registered";
				}
			}
			prepstmt.close();

			if(response.equals("success"))
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
		return response;
	}

	public static String getInfo(String deviceID) {
		String userName = null;
		String name = null;
		String phoneNumber = null;
		String regID = null;

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

	public static void insertMessageIntoDB(String senderUserName, String recepientUserName, String recepientRegID, String message, String timestamp) {

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			prepstmt = conn.prepareStatement("INSERT INTO messagestemp VALUES (0, ?, ?, ?, ?, 0, ?)");
			prepstmt.setString(1, senderUserName);
			prepstmt.setString(2, recepientUserName);
			prepstmt.setString(3, recepientRegID);
			prepstmt.setString(4, message);
			prepstmt.setString(5, timestamp);

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
		//Select the first unsent (state=0) message from the DB
		int messageID = -1;
		String senderUserName = null;
		String recepientUserName = null;
		String recepientRegID = null;
		String message = null;
		String timestamp = null;
		Message msg;

		Connection conn = null;
		Statement stmt = null;

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			//prepstmt = conn.prepareStatement("SELECT * FROM messagestemp WHERE state=0");
			ResultSet rs = stmt.executeQuery("SELECT * FROM messagestemp WHERE state=0");

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				messageID = rs.getInt("messageID");
				senderUserName = rs.getString("senderUserName");
				recepientUserName = rs.getString("recepientUserName");
				recepientRegID = rs.getString("recepientRegID");
				message = rs.getString("content");
				timestamp = rs.getString("timestamp");
				//Add message to a new Message object and insert it into a list to be returned
				msg = new Message(messageID, senderUserName, recepientUserName, recepientRegID, message, timestamp);
				messages.add(msg);

				//Set state as processing (state=2) in DB
				rs.updateInt("state", 2);
				rs.updateRow();

				//System.out.println("Message ID: "+Integer.toString(messageID)+"\nSender: "+senderUserName);
				//System.out.println("Message ID: "+Integer.toString(messageID)+"\nSender: "+senderUserName+"\nRecepient: "+recepientRegID+"\nMessage: "+message);
			}

			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
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
				if (stmt != null)
					stmt.close();
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
		Message msg = new Message();

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
				msg = messages.get(i);
				prepstmt.setInt(1, msg.getState());		
				prepstmt.setInt(2, msg.getMessageID());
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

	public static List<Contact> getRegisteredNumbers(List<String> receivedNumbers) {

		List<String> allNumbers = new ArrayList<String>();
		List<Contact> allContacts = new ArrayList<>();
		List<Contact> registeredContacts = new ArrayList<>();
		Contact tempContact = new Contact();
		String phoneNumber;
		String userName;
		String name;
		int i;
		int tempIndex;
		int sizeOfReceivedList = receivedNumbers.size();
		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query

			prepstmt = conn.prepareStatement("SELECT phoneNumber, userName, name FROM users");
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				phoneNumber = rs.getString("phoneNumber");
				userName = rs.getString("userName");
				name = rs.getString("name");
				allNumbers.add(phoneNumber);
				tempContact = new Contact(phoneNumber, userName, name);
				allContacts.add(tempContact);
			}
			for(i = 0; i < sizeOfReceivedList; i++){
				phoneNumber = receivedNumbers.get(i);
				if(allNumbers.contains(phoneNumber)){
					tempIndex = allNumbers.indexOf(phoneNumber);
					tempContact = new Contact();
					tempContact = allContacts.get(tempIndex);
					registeredContacts.add(tempContact);
				}
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

		return registeredContacts;
	}
	
	public static void insertServiceProviderIntoDB(String name, String phoneNumber, String userName, Double latitude,
			double longitude, String serviceCategory, String address) {

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			prepstmt = conn.prepareStatement("INSERT INTO serviceproviders"
					+ " (name, phoneNumber, userName, latitude, longitude, serviceCategory, address)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)"
					+ " ON DUPLICATE KEY"
					+ " UPDATE name=?, userName=?, latitude=?, longitude=?, serviceCategory=?, address=?");
			prepstmt.setString(1, name);
			prepstmt.setString(2, phoneNumber);
			prepstmt.setString(3, userName);
			prepstmt.setDouble(4, latitude);
			prepstmt.setDouble(5, longitude);
			prepstmt.setString(6, serviceCategory);
			prepstmt.setString(7, address);
			prepstmt.setString(8, name);
			prepstmt.setString(9, userName);
			prepstmt.setDouble(10, latitude);
			prepstmt.setDouble(11, longitude);
			prepstmt.setString(12, serviceCategory);
			prepstmt.setString(13, address);

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

	public static Set<String> getAvailableServiceProviderCategories()
	{
		Set<String> categories = new HashSet<String>();

		Connection conn = null;
		PreparedStatement prepstmt = null;

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			prepstmt = conn.prepareStatement("SELECT * FROM serviceproviders");
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				categories.add(rs.getString("serviceCategory"));
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
		return categories;
	}

	public static List<ServiceProvider> getNearbyServiceProviders(String serviceCategory, Double userLongitude, Double userLatitude)
	{
		List<ServiceProvider> allServiceProviders = new ArrayList<>();
		List<ServiceProvider> nearbyServiceProviders = new ArrayList<>();
		ServiceProvider tempServiceProvider = new ServiceProvider();
		String phoneNumber;
		String userName;
		String name;
		Double longitude;
		Double latitude;
		int rating;
		String address;
		int maxDistance = 5;
		int distance = 1000;
		int i;

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query

			prepstmt = conn.prepareStatement("SELECT * FROM serviceproviders where serviceCategory=?");
			prepstmt.setString(1, serviceCategory);
			ResultSet rs = prepstmt.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				phoneNumber = rs.getString("phoneNumber");
				userName = rs.getString("userName");
				name = rs.getString("name");
				longitude = rs.getDouble("longitude");
				latitude = rs.getDouble("latitude");
				rating = rs.getInt("rating");
				address = rs.getString("address");
				tempServiceProvider = new ServiceProvider();
				tempServiceProvider.setUserName(userName);
				tempServiceProvider.setName(name);
				tempServiceProvider.setPhoneNumber(phoneNumber);
				tempServiceProvider.setLongitude(longitude);
				tempServiceProvider.setLatitude(latitude);
				tempServiceProvider.setRating(rating);
				tempServiceProvider.setAddress(address);
				tempServiceProvider.setServiceCategory(serviceCategory);
				allServiceProviders.add(tempServiceProvider);
			}

			for(i = 0; i< allServiceProviders.size(); i++){
				tempServiceProvider = allServiceProviders.get(i);
				distance = 1;
				if(distance <= maxDistance){
					nearbyServiceProviders.add(tempServiceProvider);
				}
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

		return nearbyServiceProviders;
	}
	
	public static void updateStatus(int userID, String status) {

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//STEP 4: Execute a query
			prepstmt = conn.prepareStatement("INSERT INTO status (status_userID, latestStatus) VALUES (?, ?) ON DUPLICATE KEY UPDATE latestStatus=?");
			prepstmt.setInt(1, userID);
			prepstmt.setString(2, status);
			prepstmt.setString(3, status);

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

	public static void OnlineState(int userID, String state , String timestamp)
	{	 
		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			prepstmt = conn.prepareStatement("INSERT INTO online_state (state_userID, state, timestamp) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE state=?, timestamp=?");
			prepstmt.setInt(1, userID);
			prepstmt.setString(2, state);
			prepstmt.setString(3, timestamp);
			prepstmt.setString(4, state);
			prepstmt.setString(5, timestamp);

			prepstmt.executeUpdate();
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
			} 
		}

	}

	public static void insertAnImage(String senderUserName, String recepientUserName, String recepientRegID, String timestamp, String imageFileName, InputStream image)
	{

		Connection conn = null;
		PreparedStatement prepstmt = null;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			prepstmt = conn.prepareStatement("INSERT INTO images VALUES (0, ?, ?, ?, 0, ?, ?, ?)");
			prepstmt.setString(1, senderUserName);
			prepstmt.setString(2, recepientUserName);
			prepstmt.setString(3, recepientRegID);
			prepstmt.setString(4, timestamp);
			prepstmt.setString(5, imageFileName);
			prepstmt.setBlob(6, image);

			prepstmt.executeUpdate();
			prepstmt.close();

			// STEP 6: Clean-up environment
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e) {
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
		} 
	}
	
	public static List<Image> selectImagesToSend()
	{
		//Preferably use Select For Update to prevent them from being selected again
		//until they are set as sent in the DB
		List<Image> images = new ArrayList<Image>();
		//Select the first unsent (state=0) image from the DB
		// msh mot2keda mn el satr da 5ales
		long imageID = -1;
		String senderUserName = null;
		String recepientUserName = null;
		String recepientRegID = null;
		String timestamp = null;
		Image image;

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT imageID, "
					+ "senderUserName, "
					+ "recepientUserName, "
					+ "recepientRegID, "
					+ "timestamp"
					+ " FROM images WHERE state=0");

			while (rs.next()) {
				//Retrieve by column name
				imageID = rs.getInt("imageID");
				senderUserName = rs.getString("senderUserName");
				recepientUserName = rs.getString("recepientUserName");
				recepientRegID = rs.getString("recepientRegID");
				timestamp = rs.getString("timestamp");
				//Add image to a new Image object and insert it into a list to be returned

				image = new Image();
				image.setImageID(imageID);
				image.setImageSenderUserName(senderUserName);
				image.setImageRecepientUserName(recepientUserName);
				image.setImageRecepientRegID(recepientRegID);
				image.setTimestamp(timestamp);
				images.add(image);

				//Set state as processing (state=2) in DB
				rs.updateInt("state", 2);
				rs.updateRow();
			}	

			rs.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		return images;
	}
	
	public static void updateImagesState(List<Image> images)
	{
		//Update the DB with the received images list
		Image image = new Image();

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
			prepstmt = conn.prepareStatement("Update images Set state=? WHERE imageID=?");
			while(i < images.size())
			{
				image = images.get(i);
				prepstmt.setInt(1, image.getState());		
				prepstmt.setLong(2, image.getImageID());
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

	public static Blob SelectImagebyID(String imageID)
	{

		Blob imageBlob = null;
		Connection conn = null;
		PreparedStatement prepstmt = null;
		//String y = "" ;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.google.gson");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query

			prepstmt = conn.prepareStatement("SELECT image_itself FROM images WHERE imageID=?");
			prepstmt.setString(1, imageID);
			ResultSet rs = prepstmt.executeQuery();

			rs.next();
			imageBlob = rs.getBlob("image_itself");

			rs.close();
			prepstmt.close();
			conn.close();
		}
		catch (SQLException se) {
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
			} 
		}
		return imageBlob ;
	}
}
