package HelloWorld;

import java.util.ArrayList;
import java.util.List;

public class MessageSendingService extends Thread {
	
	public void run() {
		
    	System.out.println("Message sending service started!");

    	List<Message> messages = new ArrayList<Message>();

    	boolean messagesSelected = false;

		//Loop that will select unsent messages from DB and send them
		while(MessageSendingServiceOnOff.enabled == true)
		{			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//DBConnection method that will select a message that should be sent
			//and then send them one by one, each in a seperate thread
			
			//DBConnection.selectMessagesToSend();
			//new sendMessageInBackground(message).start();
			
			int i = 0;
			messages = DBConnection.selectMessagesToSend();
			Message message = new Message();
			messagesSelected = false;

			while(i < messages.size())
			{
				message = messages.get(i);
				//sendMessageInBackground sender = new sendMessageInBackground(message);
				//sender.start();
				SendToGCM.sendMessageUsingRegID(message.getMessageSenderUserName(), message.getMessageRecepientRegID(), message.getMessageContent());
				message.setAsSent();
				messages.set(i, message);
				messagesSelected = true;
				i++;
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
			SendToGCM.sendMessageUsingRegID(message.getMessageSenderUserName(), message.getMessageRecepientRegID(), message.getMessageContent());
			System.out.println("Message sent!");
			//Set message as sent
		}
		
	}

}
