package HelloWorld;


//This class is used to map the attributes of an Image, to be used by the server
//when sending images from the database to GCM
public class Image {

	private long ImageID;
	private String ImageSenderUserName;
	private String ImageRecepientUserName;
	private String ImageRecepientRegID;
	private String timestamp;
	private int state;
	private String imageFileName;
	private String json_image;


	public Image()
	{
		this.ImageID = -1;
		this.json_image = null;
		this.ImageSenderUserName = null;
		this.ImageRecepientUserName = null;
		this.ImageRecepientRegID = null;
		this.timestamp = null;
		this.state = -1;
		this.imageFileName = null;
	}

	public Image(long imgID, String senderUserName, String recepientUserName, String recepientRegID, String img, String timestamp, String imageFileName)
	{
		this.ImageID = imgID;
		this.ImageSenderUserName = senderUserName;
		this.ImageRecepientUserName = recepientUserName;
		this.ImageRecepientRegID = recepientRegID;
		this.json_image = img;
		this.timestamp = timestamp;
		this.imageFileName = imageFileName;
	}

	public void setImageID(long imgID)
	{
		this.ImageID = imgID;
	}

	public void setImageSenderUserName(String senderUserName)
	{
		this.ImageSenderUserName = senderUserName;
	}
	public void setImageRecepientUserName(String recepientUserName)
	{
		this.ImageRecepientUserName = recepientUserName;
	}
	public void setImageRecepientRegID(String recepientRegID)
	{
		this.ImageRecepientRegID = recepientRegID;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public void setImageContent(String img)
	{
		this.json_image= img;
	}

	public long getImageID()
	{
		return this.ImageID;
	}
	public String getImageSenderUserName()
	{
		return this.ImageSenderUserName;
	}
	public String getImageRecepientUserName()
	{
		return this.ImageRecepientUserName;
	}
	public String getImageRecepientRegID()
	{
		return this.ImageRecepientRegID;
	}
	public int getState()
	{
		return this.state;
	}
	public String getTimestamp()
	{
		return this.timestamp;
	}
	public String getImageFileName() {
		return this.imageFileName;
	}
	public String getImageContent()
	{
		return this.json_image;
	}

}
