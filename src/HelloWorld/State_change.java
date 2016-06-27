package HelloWorld;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class State_change
 */
//This servlet is used to receive the online/offline states of users 
//and updates the database with the received info
@WebServlet("/State_change")
public class State_change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public State_change() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		String userName = request.getParameter("userName");
		String state = request.getParameter("state");
		String timestamp = request.getParameter("timestamp");
		int userID = DBConnection.getUserID(userName);
		DBConnection.OnlineState(userID, state , timestamp);
	}

}
