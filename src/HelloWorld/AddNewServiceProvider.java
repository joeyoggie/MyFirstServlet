package HelloWorld;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewServiceProvider
 */
//This servlet is for registering a new service provider and adding them to the database
@WebServlet("/AddNewServiceProvider")
public class AddNewServiceProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewServiceProvider() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String receivedName = request.getParameter("name");
		String receivedPhoneNumber = request.getParameter("phoneNumber");
		String receivedUserName = request.getParameter("userName");
		String receivedLatitudeString = request.getParameter("latitude");
		String receivedLongitudeString = request.getParameter("longitude");
		String receivedServiceCategory = request.getParameter("job");
		String receivedAddress = request.getParameter("address");
		
		Double receivedLatitude = 0.0;
		if(receivedLatitudeString != null){
			receivedLatitude = Double.valueOf(receivedLatitudeString);
		}
		
		Double receivedLongitude = 0.0;
		if(receivedLongitudeString != null){
			receivedLongitude = Double.valueOf(receivedLongitudeString);
		}
		
		DBConnection.insertServiceProviderIntoDB(receivedName, receivedPhoneNumber, receivedUserName, receivedLatitude, receivedLongitude,  receivedServiceCategory, receivedAddress);
		
		System.out.println("Service provider " +receivedName+ " info added successfully!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
