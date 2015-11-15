package HelloWorld;
import java.io.*;
import java.util.Scanner;
public class MessageSendingServiceOnOff {
	
	public static boolean enabled;
	
	public static void main(String[] args) {

		MessageSendingService sender = new MessageSendingService();

		Scanner in = new Scanner(System.in);
		String input = null;
		char choice = 'a';
		
		
		while(true)
		{
			System.out.println("Press 'y' to enable or 'e' to terminate...");
			input = in.nextLine();
			choice = input.charAt(0);
			
			if(choice == 'y')
			{
				enabled = true;
				sender = new MessageSendingService();
				sender.start();
			}			
			else if(choice == 'e')
			{
				enabled = false;
				/*
				try {
					Thread.sleep(100);
					sender.stop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			}
			else
			{
				System.out.println("Invalid input!");
			}
		}
		
	}

}
