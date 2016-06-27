package HelloWorld;

//This class is used to map the attributes of a Contacts object to be ruturned to users
//when they check which of their contacts have the app as well
public class Contact {

	
	private String phoneNumber;
	private String userName;
	private String name;
	
	public Contact()
	{
		this.phoneNumber = null;
		this.userName = null;
		this.name = null;
	}
	public Contact(String phoneNumber, String userName, String name)
	{
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.name = name;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPhoneNumber()
	{
		return this.phoneNumber;
	}
	public String getUserName()
	{
		return this.userName;
	}
	public String getName()
	{
		return this.name;
	}
}
