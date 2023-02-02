package accredyApp.model;

//data concerns:
//resource does not have description

public class Resource {
	String RID;
	String name;
	
	//constructor
	public Resource(String RID, String name) {
		this.name = name;
		this.RID = RID;
		//does a new resource need any additional instantiation?
	}
	
	//empty constructor
	public Resource() {
	}

	//getters and setters
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }

	public void setRID(String RID) { this.RID = RID; }
	public String getRID() { return RID; }

	// prints contents as a string
	public String toString() {
		return name;
	}

}
