package HelloWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import HelloWorld.DBConnection;

/**
 * Servlet implementation class AddNewMessage
 */
//This servlet is used to accept new messages from users and adds them to the database
@WebServlet("/AddNewMessage")
public class AddNewMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewMessage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*String senderDeviceID = request.getParameter("senderDeviceID");
		String recepientUserName = request.getParameter("recepientUserName");
		String message = request.getParameter("message");
		String timestamp = request.getParameter("timestamp");
		String senderUserName = DBConnection.getUserName(senderDeviceID);
		String recepientRegID = DBConnection.getRegisterationID(recepientUserName);
		DBConnection.insertMessageIntoDB(senderUserName, recepientUserName, recepientRegID, message, timestamp);
		System.out.println("New message inserted into DB.");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		/*response.setContentType("application/json");
	    response.setCharacterEncoding("utf-8");*/
	    
	    BufferedReader bReader = request.getReader();
	    String line;
	    String jsonData = "";
	    if((line = bReader.readLine()) != null)
	    	jsonData += line;
	    bReader.close();
	    Gson gson = new Gson();
	    
	    /*Message messageObject = new Message();
	    messageObject = gson.fromJson(jsonData, Message.class);
	    String senderDeviceID = messageObject.getMessageSenderDeviceID();
		String recepientUserName = messageObject.getMessageRecepientUserName();
		String message = messageObject.getMessageContent();
		String timestamp = messageObject.getTimestamp();
		System.out.println(senderDeviceID);
		System.out.println(recepientUserName);
		System.out.println(message);
		System.out.println(timestamp);*/
	    
	    JSONObject messageJsonObject = new JSONObject();
	    messageJsonObject = gson.fromJson(jsonData, JSONObject.class);
	    String senderDeviceID = (String) messageJsonObject.get("senderDeviceID");
		String recepientUserName = (String) messageJsonObject.get("recepientUserName");
		String message = (String) messageJsonObject.get("messageContent");
		String timestamp = (String) messageJsonObject.get("timestamp");
		int messageID = Integer.parseInt((String)messageJsonObject.get("messageID"));
		System.out.println("Sender: " + senderDeviceID);
		System.out.println("Recepient: " + recepientUserName);
		System.out.println("MessageID: " + messageID);
		System.out.println("Message: " + message);
		System.out.println("Timestamp: " + timestamp);
		
		String senderUserName = DBConnection.getUserName(senderDeviceID);
		String recepientRegID = DBConnection.getRegisterationID(recepientUserName);
		DBConnection.insertMessageIntoDB(senderUserName, recepientUserName, recepientRegID, message, timestamp);
		System.out.println("New message inserted into DB.");
		
		JSONObject jObject = new JSONObject();
		jObject.put("messageID", messageID);
		jObject.put("response", "Message sent successfully");
		PrintWriter out = response.getWriter();
		out.write(jObject.toJSONString());
		out.close();
		out.flush();
	}

}
