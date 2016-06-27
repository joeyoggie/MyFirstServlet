package HelloWorld;
import java.util.Scanner;

//This class is used as a normal Java app to start the messaging service
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
