package HelloWorld;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//This class is responsible for handling everything related to GCM
public class SendToGCM {

	public static void main(String[] args) {
		
		//sendMessageUsingUserName("JoeyOggie", "JoeyOggie", "testing!");

	}
	
	public static int sendMessage(String senderUserName, String recepientUserName, String recepientRegID, String message, String timestamp)
	{
		int responseCode = 0;
		System.out.println("Sending message to GCM server...");
		//String api_key = "AIzaSyCkArQ7w7kgiLit36nxi394sjvVXDei-Fs";
		String api_key = "AIzaSyCci78JwoIsYzAPJmXvUihOUxYTObJ2Zh4";
		
		try {
			URL url;
			url = new URL("https://gcm-http.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	        //conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "key="+api_key);
	        
	        String content = "registration_id="+recepientRegID+"&data.sender="+senderUserName+"&data.message="+URLEncoder.encode(message,"UTF-8")+"&data.recepient="+recepientUserName+"&data.timestamp="+timestamp;	        
	        OutputStream out = conn.getOutputStream();
	        out.write(content.getBytes());
	        out.close();
	 
	     // 6. Get the response
            responseCode = conn.getResponseCode();
            //System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code: " + responseCode);

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
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return responseCode;
	}
	
	public static void sendMessageUsingUserName(String senderUserName, String recepientUserName, String message, String timestamp)
	{
		System.out.println("Sending to GCM server...");
		//String api_key = "AIzaSyCkArQ7w7kgiLit36nxi394sjvVXDei-Fs";
		String api_key = "AIzaSyCci78JwoIsYzAPJmXvUihOUxYTObJ2Zh4";
		String recepientRegID = DBConnection.getRegisterationID(recepientUserName);
		System.out.println(recepientRegID);
		
		try {
			URL url;
			url = new URL("https://gcm-http.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
	        //conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Authorization", "key="+api_key);
	        
	        String content = "registration_id="+recepientRegID+"&data.title=Message from "+senderUserName+"&data.message="+message+"&data.timestamp="+timestamp;	        
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
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendMessageUsingRegID(String senderUserName, String recepientRegID, String message, String timestamp)
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
			
	        
	        String content = "registration_id="+recepientRegID+"&data.title=Message from "+senderUserName+"&data.message="+message+"&data.timestamp="+timestamp;
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
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int sendTypingStatus(String senderUserName, String recepientUserName, String recepientRegID, String typingStatus)
	{
		int responseCode = 0;
		//System.out.println("Sending typing status to GCM server...");
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
	        
	        String content = "registration_id="+recepientRegID+"&data.senderUserName="+senderUserName+"&data.recepientUserName="+recepientUserName+"&data.typingStatus="+typingStatus;	        
	        OutputStream out = conn.getOutputStream();
	        out.write(content.getBytes());
	        out.close();
	 
	     // 6. Get the response
            responseCode = conn.getResponseCode();
            //System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 7. Print result
            //System.out.println(response.toString());
					
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return responseCode;
	}
	
	public static int SendImage(String senderUserName, String recepientUserName, String recepientUserRegID, long imageID, String timestamp)
	{
		int responseCode =0 ;
        System.out.println("Sending to GCM server...");
        //String api_key = "AIzaSyCkArQ7w7kgiLit36nxi394sjvVXDei-Fs";
        String api_key = "AIzaSyCci78JwoIsYzAPJmXvUihOUxYTObJ2Zh4";
        String recepientRegID = DBConnection.getRegisterationID(recepientUserName);
        System.out.println(recepientRegID);

        try {
            URL url;
            url = new URL("https://gcm-http.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            //conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + api_key);

            String content = "registration_id="+recepientRegID+"&data.senderUserName="+senderUserName+"&data.recepientUserName="+recepientUserName+"&data.imageID="+imageID+"&data.timestamp="+timestamp;
            OutputStream out = conn.getOutputStream();
            out.write(content.getBytes());
            out.close();

            responseCode = conn.getResponseCode();
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

            System.out.println(response.toString());
        }
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return responseCode;    
	}
}
