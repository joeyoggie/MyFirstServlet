package HelloWorld;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.sun.scenario.effect.ImageData;

import sun.font.StrikeCache;

/**
 * Servlet implementation class AddNewImage
 */
//This servlet is used to accept new images from users and adds them to the database
@WebServlet("/AddNewImage")
@MultipartConfig
public class AddNewImage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void init() throws ServletException{
		/*DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);*/
	}

	/**
	 * Default constructor. 
	 */
	public AddNewImage() {
		super();
	}
	/*String senderDeviceID;
	String recepientUserName;
	String timestamp;*/
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
	    System.out.println("Receiving image...");

		String senderDeviceID = request.getParameter("senderDeviceID");
		String recepientUserName = request.getParameter("recepientUserName");												
		String timestamp = request.getParameter("timestamp");
		String senderUserName = DBConnection.getUserName(senderDeviceID);
		String recepientRegID = DBConnection.getRegisterationID(recepientUserName);
		System.out.println("sender:" + senderUserName);
		System.out.println("recepient:" + recepientUserName);
		System.out.println("timestamp" + timestamp);
		
		Part filePart = request.getPart("image_file");
	    String fileName = getSubmittedFileName(filePart);
	    InputStream inputStream = filePart.getInputStream();
	    
		DBConnection.insertAnImage(senderUserName, recepientUserName, recepientRegID, inputStream, timestamp, fileName);

		//File imageFile = null;
	    //List<Byte> imageBinaryData = new ArrayList<>();
	    //imageFile = new File("C:\\Users\\Joey\\Desktop\\"+fileName);
		//FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
		//byte[] buffer = new byte[4096];
		//int length;

		/*while ((length = inputStream.read(buffer)) > 0){
			fileOutputStream.write(buffer, 0, length);
			//imageBinaryData.addAll(buffer);
		}*/
		//inputStream.close();
		//fileOutputStream.close();
		//fileOutputStream.flush();
	    System.out.println("Image received...");
	    

/*		try {
			ServletFileUpload fileUpload = new ServletFileUpload();
			FileItemIterator items = fileUpload.getItemIterator(request);
			//iterate items
			while (items.hasNext()) {
				System.out.println("HAS NEXT!");
				FileItemStream item = items.next();
				if (!item.isFormField()) {
					InputStream inputStream = item.openStream();
					System.out.println("Writing file....");
					imageFile = new File("C:\\Users\\Joey\\Desktop\\"+"woo.jpg");
					FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
					byte[] buffer = new byte[4096];
					int length;
					while ((length = inputStream.read(buffer)) > 0){
						fileOutputStream.write(buffer, 0, length);
					}
					inputStream.close();
					fileOutputStream.close();
					fileOutputStream.flush();
				}
			}
		} catch (FileUploadException e) {
			System.out.println("Exception: " + e.toString());
		}*/

		/*System.out.println(request.getParts().size());
		List<Part> parts= (List<Part>) request.getParts();
		System.out.println("Parts: " + parts.size());*/

		/*try {
			System.out.println("Begin receiving...");
			DiskFileItemFactory factory = new DiskFileItemFactory(50000, new File("C:\\Users\\Joey\\Desktop\\"));
			factory.setSizeThreshold(2000000);

			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
			System.out.println("Size of received items: "+items.size());
			for (FileItem item : items) {
				System.out.println("First iteration...");
				if (item.getFieldName().equals("uploaded_file")) {
					String fileName = item.getName();
					System.out.println("Filename: " + fileName);
					String fileContentType = item.getContentType();
					System.out.println("File type: " + fileContentType);
					InputStream fileContent = item.getInputStream();
					// ... (do your job here)
					System.out.println("Writing file....");
					imageFile = new File("C:\\Users\\Joey\\Desktop\\"+fileName);
					FileOutputStream fileOutputStream = new FileOutputStream(imageFile);

					byte[] buffer = new byte[4096];
					int length;
					while ((length = fileContent.read(buffer)) > 0){
						fileOutputStream.write(buffer, 0, length);
					}
					fileContent.close();
					fileOutputStream.close();
					fileOutputStream.flush();
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}*/

	}
	
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}

}
