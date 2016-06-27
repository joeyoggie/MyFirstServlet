package HelloWorld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetLocalServices
 */
//This servlet sends the nearby service providers to a user when requested
//TODO re-implement the distance calculator in the DBCOnnecion.java class
@WebServlet("/GetLocalServices")
public class GetLocalServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetLocalServices() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String serviceCategory = request.getParameter("serviceCategory");
		String userLongitudeString = request.getParameter("userLongitude");
		String userLatitudeString = request.getParameter("userLatitude");
		
		System.out.println(userLatitudeString + userLongitudeString);
		Double userLongitude = Double.valueOf(userLongitudeString);
		Double userLatitude = Double.valueOf(userLatitudeString);
		
		System.out.println("Received:");
		System.out.println("Requested service category: "+serviceCategory);
		System.out.println("User location: Longitude=" + userLongitudeString + " & Latitude= "+userLatitudeString);
		
		List<ServiceProvider> serviceProviders = new ArrayList<>();
		serviceProviders = DBConnection.getNearbyServiceProviders(serviceCategory, userLongitude, userLatitude);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(serviceProviders);
		PrintWriter writer = response.getWriter();
		writer.write(jsonString);
		System.out.println(jsonString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
