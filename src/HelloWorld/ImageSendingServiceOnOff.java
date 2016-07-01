package HelloWorld;

import java.util.Scanner;

//This class is used as a normal Java app to start the image sending service
public class ImageSendingServiceOnOff {

	public static boolean enabled;

	public static void main(String[] args) {

		ImageSendingService sender = new ImageSendingService();
		Scanner in = new Scanner(System.in);
		String input = null;
		char choice = 'a';
		
		while(true){
			System.out.println("Press 'y' to enable or 'e' to terminate...");
			input = in.nextLine();
			choice = input.charAt(0);
			
			if(choice == 'y')
			{
				enabled = true;
				sender = new ImageSendingService();
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
