package HelloWorld;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

//This class is responsible for selecting unsent images from the database
//and then sends them to GCM if they should be sent
//TODO finish implementing threading here
public class ImageSendingService extends Thread{

	public void run(){
		
    	System.out.println("Image sending service started!");

		List<Image> images = new ArrayList<Image>();

		boolean imagesSelected = false;

		//Loop that will select unsent images from DB and send them
		while(ImageSendingServiceOnOff.enabled == true){

			//TODO remove this delay
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//DBConnection method that will select an image that should be sent
			//and then send them one by one, each in a seperate thread
			//images = DBConnection.selectImagesToSend(); (done below)
			//loop through the images list (done below)
			//TODO new sendImageInBackground(images.get(i)).start();

			int i = 0;
			images = DBConnection.selectImagesToSend();
			Image image = new Image();
			Date dateOfImage = new Date();
			Date dateCurrent = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			int ImageTimeComparedToNow = 1;
			imagesSelected = false;
			int response;

			if(images.isEmpty() == false)
			{
				imagesSelected = true;
				while(i < images.size())
				{
					image  = images.get(i);
					//TODO Send each image in an individual thread on its own
					//ie, move this part of the code to the sendImageInBackground class below
					//sendImageInBackground sender = new sendImageInBackground(image);
					//sender.start();
					try {
						dateOfImage = simpleDateFormat.parse(image.getTimestamp());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ImageTimeComparedToNow = dateOfImage.compareTo(dateCurrent);
					if(ImageTimeComparedToNow <= 0)
					{
						response = SendToGCM.SendImage(image.getImageSenderUserName(), image.getImageRecepientUserName(), image.getImageRecepientRegID(), image.getImageID(), image.getTimestamp());
						if(response == 200)
						{
							image.setState(1);
							images.set(i, image);
						}
					}
					i++;
				}
			}
			//Update images' state in DB to prevent re-sending images which were sent
			if(imagesSelected == true)
			{
				DBConnection.updateImagesState(images);
			}
		}
		System.out.println("Image sending service stopped!");


	}
}
