package accredyApp.model;

//data concerns:
//job does not have title or description


public class Job {
	String JID;
	String name;
	String location;
	float salary;
	
	//constructor
	public Job(String JID, String name, String location, float salary) {
		this.location = location;
		this.salary = salary;
		this.name = name;
		this.JID = JID;
	}
	
	//empty constructor
	public Job() {
	}

	//getters and setters
	public void setLocation(String location) { this.location = location; }
	public String getLocation() { return location; }

	public void setName(String name) { this.name = name; }
	public String getName() { return name; }

	public void setSalary(float salary) { this.salary = salary; }
	public float getSalary() { return salary; }

	public void setJID(String JID) { this.JID = JID; }
	public String getJID() { return JID; }

	// prints contents as a string
	public String toString() {
		return name + " in " + location + ", salary $" + salary;
	}
}
