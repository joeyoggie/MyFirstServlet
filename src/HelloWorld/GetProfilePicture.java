package HelloWorld;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetProfilePicture
 */
@WebServlet("/GetProfilePicture")
public class GetProfilePicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfilePicture() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		Blob imageBlob = DBConnection.SelectImagebyUsername(userName);

		System.out.println("Sending profile picture...");
		ServletOutputStream outputStream = response.getOutputStream();
		response.setContentType("image/png");
		try {
			InputStream in = imageBlob.getBinaryStream();
			int length = (int) imageBlob.length();
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			while ((length = in.read(buffer)) != -1) {
				//System.out.println("writing " + length + " bytes");
				outputStream.write(buffer, 0, length);
			}
			in.close();
			outputStream.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Profile picture sent!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
