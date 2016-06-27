package HelloWorld;

//Import required java libraries
import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Extend HttpServlet class
@WebServlet("/Register")
//This class is used to add a new user to the database
public class Register extends HttpServlet {
	static final long serialVersionUID = 1L;

	public void init() throws ServletException
	{
		// Do required initialization
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

		String registrationResponse = DBConnection.insertUserIntoDB(receivedUserName, receivedName, receivedPhoneNumber, receivedDeviceID, receivedRegID);
		
		PrintWriter out = response.getWriter();
		
		if(registrationResponse.equals("success"))
		{
			out.println("Registered successfully!");
			System.out.println("User " +receivedName+ " registered successfully!");
		}
		else if(registrationResponse.contains("phoneNumber"))
		{
			out.println("Registration failed, phoneNumber "+receivedPhoneNumber+" already registered!");
			System.out.println("User " +receivedName+ " info not inserted, phoneNumber already registered!");
		}
		else if(registrationResponse.contains("userName"))
		{
			out.println("Registration failed, userName "+receivedUserName+" already in use. Choose another userName and try again.");
			System.out.println("User " +receivedName+ " not registered, userName already in use. Choose another userName and try again.");
		}
	}

	public void destroy()
	{
		// do nothing.
	}
}

