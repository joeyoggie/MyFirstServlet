package HelloWorld;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AddNewProfilePicture
 */
@WebServlet("/AddNewProfilePicture")
@MultipartConfig
public class AddNewProfilePicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProfilePicture() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Receiving image...");

		String userName = request.getParameter("userName");
		String timestamp = request.getParameter("timestamp");
		System.out.println("userName:" + userName);
		System.out.println("timestamp" + timestamp);
		
		Part filePart = request.getPart("image_file");
	    InputStream inputStream = filePart.getInputStream();
	    
		DBConnection.insertProfilePicture(userName, inputStream, timestamp);

	    System.out.println("Image received...");
	}

}
