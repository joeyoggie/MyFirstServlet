package HelloWorld;

//Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Extend HttpServlet class
@WebServlet("/Register")
public class Register extends HttpServlet {
	static final long serialVersionUID = 1L;

	private String message;

	public void init() throws ServletException
	{
		// Do required initialization
		message = "Reply from Tomcat server @Joey-PC/MyFirstServlet/HelloWorld";
	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{
		// Set response content type
		response.setContentType("text/html;charset=UTF-8");
		
		String receivedUserName = request.getParameter("userName");
		String receivedName = request.getParameter("name");
		String receivedPhoneNumber = request.getParameter("phoneNumber");
		String receivedDeviceID = request.getParameter("deviceID");
		String receivedRegID = request.getParameter("regID");

		boolean inserted = DBConnection.insertUserIntoDB(receivedUserName, receivedName, receivedPhoneNumber, receivedDeviceID, receivedRegID);
		
		PrintWriter out = response.getWriter();
		
		if(inserted == true)
		{
			out.println("User " +receivedName+ " info registered successfully!");
			System.out.println("User " +receivedName+ " info registered successfully!");
		}
		else
		{
			out.println("User " +receivedName+ " info not inserted, phone number already registered!");
			System.out.println("User " +receivedName+ " info not inserted, phone number already registered!");
		}
		
	}

	public void destroy()
	{
		// do nothing.
	}
}

