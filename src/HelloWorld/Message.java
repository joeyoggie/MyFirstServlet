package HelloWorld;

public class Message {
	
	private int messageID;
	private String messageContent;
	private String messageSenderUserName;
	private String messageRecepientRegID;
	private int state;
	
	public Message()
	{
		messageID = -1;
		messageContent = null;
		messageSenderUserName = null;
		messageRecepientRegID = null;
		state = -1;
	}
	public Message(int msgID, String senderUserName, String recepientRegID, String message)
	{
		this.messageID = msgID;
		this.messageSenderUserName = senderUserName;
		this.messageRecepientRegID = recepientRegID;
		this.messageContent = message;
	}
	
	public void setAsSent()
	{
		this.state = 1;
	}
	public int getState()
	{
		return this.state;
	}
	
	public void setMessageID(int msgID)
	{
		this.messageID = msgID;
	}
	public void setMessageContent(String message)
	{
		this.messageContent = message;
	}
	public void setMessageSenderUserName(String senderUserName)
	{
		this.messageSenderUserName = senderUserName;
	}
	public void setMessageRecepientRegID(String recepientRegID)
	{
		this.messageRecepientRegID = recepientRegID;
	}
	
	public int getMessageID()
	{
		return this.messageID;
	}
	public String getMessageContent()
	{
		return this.messageContent;
	}
	public String getMessageSenderUserName()
	{
		return this.messageSenderUserName;
	}
	public String getMessageRecepientRegID()
	{
		return this.messageRecepientRegID;
	}

}
