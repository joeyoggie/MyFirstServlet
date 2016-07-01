package HelloWorld;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

//This class is responsible for selecting unsent messages from the database
//and then sends them to GCM if they should be sent
//TODO finish implementing threading here
public class MessageSendingService extends Thread {
	
	public void run() {
		
    	System.out.println("Message sending service started!");

    	List<Message> messages = new ArrayList<Message>();

    	boolean messagesSelected = false;

		//Loop that will select unsent messages from DB and send them
		while(MessageSendingServiceOnOff.enabled == true){			
			
			//TODO remove this delay
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//DBConnection method that will select a messages that should be sent
			//and then send them one by one, each in a seperate thread
			//messages = DBConnection.selectMessagesToSend(); (done below)
			//loop through the messages list (done below)
			//TODO new sendMessageInBackground(messages.get(i)).start();
			
			int i = 0;
			messages = DBConnection.selectMessagesToSend();
			Message message = new Message();
			Date dateOfMessage = new Date();
			Date dateCurrent = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			int messageTimeComparedToNow = 1;
			messagesSelected = false;
			int response;

			if(messages.isEmpty() == false)
			{
				messagesSelected = true;
				while(i < messages.size())
				{
					message = messages.get(i);
					//TODO Send each message in an individual thread on its own
					//ie, move this part of the code to the sendMessageInBackground class below
					//sendMessageInBackground sender = new sendMessageInBackground(message);
					//sender.start();
					try {
						dateOfMessage = simpleDateFormat.parse(message.getTimestamp());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					messageTimeComparedToNow = dateOfMessage.compareTo(dateCurrent);
					if(messageTimeComparedToNow <= 0)
					{
						response = SendToGCM.sendMessage(message.getMessageSenderUserName(), message.getMessageRecepientUserName(), message.getMessageRecepientRegID(), message.getMessageContent(), message.getTimestamp());
						if(response == 200)
						{
							message.setState(1);
							messages.set(i, message);
						}
					}
					i++;
				}
			}
			//Update messages' state in DB to prevent re-sending messages which were sent
			if(messagesSelected == true)
			{
				DBConnection.updateMessagesState(messages);
			}
		}
		System.out.println("Message sending service stopped!");
	}
	
	private static class sendMessageInBackground extends Thread
	{
		Message message;
		public sendMessageInBackground(Message msg)
		{
			super();
			message = msg;
		}
		public void run()
		{
			//Send message in background here (just call SendToGCM.sendMessage() here,
			//using the message passed
			//SendToGCM.sendMessageUsingRegID(message.getMessageSenderUserName(), message.getMessageRecepientRegID(), message.getMessageContent());
			System.out.println("Message sent!");
			//Set message as sent (code for that above)
		}
		
	}

}
