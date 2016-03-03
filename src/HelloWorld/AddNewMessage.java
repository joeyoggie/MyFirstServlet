package HelloWorld;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HelloWorld.DBConnection;

/**
 * Servlet implementation class AddNewMessage
 */
@WebServlet("/AddNewMessage")
public class AddNewMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String senderDeviceID = request.getParameter("senderDeviceID");
		String recepientUserName = request.getParameter("recepientUserName");
		String message = request.getParameter("message");
		String timestamp = request.getParameter("timestamp");
		
		String senderUserName = DBConnection.getUserName(senderDeviceID);
		String recepientRegID = DBConnection.getRegisterationID(recepientUserName);
		DBConnection.insertMessageIntoDB(senderUserName, recepientUserName, recepientRegID, message, timestamp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
