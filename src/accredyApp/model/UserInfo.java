package accredyApp.model;

public class UserInfo {
	String firstName;
	String lastName;
	String userID;
	String password; //This is ONLY USED AFTER LOGIN and ONLY for the logged in user.
	
	//Data concern:
	//not sure if we want to store userID. It COULD BE part of the list we provide, but I don't think we ever have to send a UserInfo back to the database so we don't need it?
	
	//constructor
	public UserInfo(String fName, String lName, String userID) {
		this.firstName = fName;
		this.lastName = lName;
		this.userID = userID;
	}
	
	public UserInfo(String fName, String lName, String userID, String password) {
		this.firstName = fName;
		this.lastName = lName;
		this.userID = userID;
	}

	//empty constructor
	public UserInfo() {
	}

	//getters and setters
	public void setFirstName(String name) { this.firstName = name; }
	public String getFirstName() { return firstName; }

	public void setLastName(String name) { this.lastName = name; }
	public String getLastName() { return lastName; }

	public void setUserID(String userID) { this.userID = userID; }
	public String getUserID() { return userID; }

	// prints name as a string, useful for attendee list
	public String getName() {
		return firstName + " " + lastName;
	}

	// prints contents as a string
	public String toString() {
		return "\n" + firstName + " " + lastName;
	}
}
