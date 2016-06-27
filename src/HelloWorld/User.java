package HelloWorld;

//This class is used to map the attributes of a User object, 
//used when a user requests the info of another user
//TODO use the privacy functions here to return only the authorized content
public class User {

	String phoneNumber;
	String userName;
	String name;
	String status;
	//profile picture kaman
	//online/offline flag
	
	
	public User() {
		
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
}
