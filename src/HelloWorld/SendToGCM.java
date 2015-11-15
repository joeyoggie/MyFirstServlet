package HelloWorld;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendToGCM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		sendMessageUsingUserName("JoeyOggie", "JoeyOggie", "Testing!");

	}
	
	public static void sendMessageUsingUserName(String senderUserName, String recepientUserName, String message)
	{
		System.out.println("Sending to GCM server...");
		//String api_key = "AIzaSyCkArQ7w7kgiLit36nxi394sjvVXDei-Fs";
		String api_key = "AIzaSyCci78JwoIsYzAPJmXvUihOUxYTObJ2Zh4";
		String registration_id = DBConnection.getRegisterationID(recepientUserName);
		System.out.println(registration_id);
		
		try {
			URL url;
			url = new URL("https://gcm-http.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
	        //conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "key="+api_key);
			
	        
	        String content = "registration_id="+registration_id+"&data.title=New message from "+senderUserName+"&data.message="+message;
	        OutputStream out = conn.getOutputStream();
	        out.write(content.getBytes());
	        out.close();
	        
	        
	        
	     // 6. Get the response
            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 7. Print result
            System.out.println(response.toString());
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void sendMessageUsingRegID(String senderUserName, String recepientRegID, String message)
	{
		System.out.println("Sending to GCM server...");
		//String api_key = "AIzaSyCkArQ7w7kgiLit36nxi394sjvVXDei-Fs";
		String api_key = "AIzaSyCci78JwoIsYzAPJmXvUihOUxYTObJ2Zh4";
		
		try {
			URL url;
			url = new URL("https://gcm-http.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
	        //conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "key="+api_key);
			
	        
	        String content = "registration_id="+recepientRegID+"&data.title=New message from "+senderUserName+"&data.message="+message;
	        OutputStream out = conn.getOutputStream();
	        out.write(content.getBytes());
	        out.close();
	        
	     // 6. Get the response
            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 7. Print result
            System.out.println(response.toString());
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
