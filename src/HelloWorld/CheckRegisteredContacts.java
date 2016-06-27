package HelloWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

/**
 * Servlet implementation class CheckRegisteredContacts
 */
//This servlet is used to check which of the contacts of a user have the app installed,
//and return them to him
@WebServlet("/CheckRegisteredContacts")
public class CheckRegisteredContacts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckRegisteredContacts() {
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
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");
	    
	    BufferedReader bReader = request.getReader();
	    String line;
	    String jsonData = "";
	    if((line = bReader.readLine()) != null)
	    	jsonData += line;
	    bReader.close();
	    System.out.println("Received JSON string: "+jsonData);
	    List<String> receivedNumbers = new ArrayList<>();
	    Gson gson = new Gson();
	    receivedNumbers = gson.fromJson(jsonData, ArrayList.class);
	    System.out.println("Received list: "+receivedNumbers.toString());
	    System.out.println("Received list size = " + receivedNumbers.size());
	    List<String> receivedNumbersTrimmed = new ArrayList<>();
	    for(int i = 0; i < receivedNumbers.size(); i++)
	    {
	    	receivedNumbersTrimmed.add(receivedNumbers.get(i).replaceAll("\\s", ""));
	    }
	    System.out.println("Trimmed list: "+receivedNumbersTrimmed.toString());
	    System.out.println("Trimmed list size = " + receivedNumbersTrimmed.size());

	    //Remove duplicates from the received, trimmed numbers
	    Set<String> hashSet = new HashSet<>();
	    hashSet.addAll(receivedNumbersTrimmed);
	    receivedNumbersTrimmed.clear();
	    receivedNumbersTrimmed.addAll(hashSet);
	    System.out.println("Trimmed list size after removing duplicates = " + receivedNumbersTrimmed.size());

	    List<Contact> registeredContacts = new ArrayList<>();
	    registeredContacts = DBConnection.getRegisteredNumbers(receivedNumbersTrimmed);
	    
	    String jsonString = gson.toJson(registeredContacts);
	    PrintWriter out = response.getWriter();
		out.write(jsonString);
		out.flush();
		out.close();
		System.out.println("Sent JSON string: "+jsonString);
	}

}
