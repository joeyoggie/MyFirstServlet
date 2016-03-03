package HelloWorld;

public class Message {
	
	private int messageID;
	private String messageContent;
	private String messageSenderUserName;
	private String messageRecepientUserName;
	private String messageRecepientRegID;
	private String timestamp;
	private int state;
	
	public Message()
	{
		messageID = -1;
		messageContent = null;
		messageSenderUserName = null;
		messageRecepientUserName = null;
		messageRecepientRegID = null;
		this.timestamp = null;
		state = -1;
	}
	public Message(int msgID, String senderUserName, String recepientUserName, String recepientRegID, String message, String timestamp)
	{
		this.messageID = msgID;
		this.messageSenderUserName = senderUserName;
		this.messageRecepientUserName = recepientUserName;
		this.messageRecepientRegID = recepientRegID;
		this.messageContent = message;
		this.timestamp = timestamp;
	}
	
	public void setState(int state)
	{
		this.state = state;
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
	public void setMessageRecepientUserName(String recepientUserName)
	{
		this.messageRecepientUserName = recepientUserName;
	}
	public void setMessageRecepientRegID(String recepientRegID)
	{
		this.messageRecepientRegID = recepientRegID;
	}
	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}
	
	public int getState()
	{
		return this.state;
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
	public String getMessageRecepientUserName()
	{
		return this.messageRecepientUserName;
	}
	public String getMessageRecepientRegID()
	{
		return this.messageRecepientRegID;
	}
	public String getTimestamp()
	{
		return this.timestamp;
	}

}
