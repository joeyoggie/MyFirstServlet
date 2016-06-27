package HelloWorld;

import java.io.IOException;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AddNewImage
 */
//This servlet is responsible for adding new images sent by the users to the database
@WebServlet("/AddNewImage")
public class AddNewImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String senderDeviceID = request.getParameter("senderDeviceID");
		String recepientUserName = request.getParameter("recepientUserName");
		String timestamp = request.getParameter("timestamp");
		String senderUserName = DBConnection.getUserName(senderDeviceID);
		String recepientRegID = DBConnection.getRegisterationID(recepientUserName);
		System.out.println(timestamp);
		InputStream inputStream = request.getInputStream();
		   
		Part filePart = request.getPart("photo");
		
		if (filePart != null) {
			// prints out some information for debugging    
			//  System.out.println(filePart.getName());
			// System.out.println(filePart.getSize());
			// System.out.println(filePart.getContentType());
			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();     
		}
		System.out.println("INPUTSTREAM:");
		System.out.println(inputStream.toString());
		//DBConnection.insertAnImage(senderUserName, recepientUserName, recepientRegID,inputStream, timestamp);
		
		////////////////////////////////////////////////
		
		//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//InputStream input = classLoader.getResourceAsStream("/resources/tina.jpg");
		//InputStream input = classLoader.getResourceAsStream("tina.jpg");

	}

}
