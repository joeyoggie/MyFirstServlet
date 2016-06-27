package HelloWorld;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetAllServiceProviderCategories
 */
//This servlet is used to return a list of all possible service providers' categories
//It gets the categories already stored in the database when other users registered,
//and adds some which could be used as well
@WebServlet("/GetAllServiceProviderCategories")
public class GetAllServiceProviderCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllServiceProviderCategories() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Get all the categories from the database
		Set<String> categories = DBConnection.getAvailableServiceProviderCategories();
		categories.add("Mechanic");
		categories.add("Electrician");
		categories.add("Plumber");
		categories.add("Doctor");
		categories.add("Pharmacy");
		categories.add("Police");
		categories.add("Cook");
		categories.add("Chef");
		categories.add("Carpenter");
        //TODO probably add more here to cover more use cases,
		//and keep this synced with the client as well if possible
		List<String> allCategories = new ArrayList<>();
		allCategories.addAll(categories);
		
		//Send the list of categories to the client
		Gson gson = new Gson();
		String jsonString = gson.toJson(allCategories);
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
