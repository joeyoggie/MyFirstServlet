package HelloWorld;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadApk
 */
//This servlet is used to send the APK file to anyone who opens its link
//TODO remove this servlet
@WebServlet("/DownloadApk")
public class DownloadApk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadApk() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String id = request.getParameter("id");

		System.out.println("A user requested to download the application...");
        String fileName = "C:\\Users\\Joey\\AndroidStudioProjects\\Project1\\app\\build\\outputs\\apk\\app-debug.apk";
        String fileType = "application/apk";
        // Find this file id in database to get file name, and file type

        // You must tell the browser the file type you are going to send
        // for example application/pdf, text/plain, text/html, image/jpg
        response.setContentType(fileType);

        // Make sure to show the download dialog
        response.setHeader("Content-disposition","attachment; filename=Project1.apk");

        // Assume file name is retrieved from database
        // For example D:\\file\\test.pdf

        File my_file = new File(fileName);

        System.out.println("File upload started...");
        // This should send the file to browser
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
           out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        System.out.println("File upload completed successfully.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
