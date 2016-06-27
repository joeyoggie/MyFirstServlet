package HelloWorld;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateTypingStatus
 */
//This servlet is used to receive the 'typing status' of users
//and forwards it to GCM to send it to the other conversation party
@WebServlet("/UpdateTypingStatus")
public class UpdateTypingStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTypingStatus() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String typingStatus = request.getParameter("typingStatus");
		String recepientUserName = request.getParameter("recepientUserName");
		String senderUserName = request.getParameter("senderUserName");
		String recepientRegID = DBConnection.getRegisterationID(recepientUserName);

		SendToGCM.sendTypingStatus(senderUserName, recepientUserName, recepientRegID, typingStatus);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
