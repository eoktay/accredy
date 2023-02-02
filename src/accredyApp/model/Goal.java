package accredyApp.model;

import java.util.ArrayList;

//data concerns:
//goal does not have a title (idk if it should though)


public class Goal {
	String description;
	String GID;
	ArrayList<String> skills;

	
	//constructor
	public Goal(String description, String GID) {
		this.description = description;
		this.GID = GID;
		this.skills = new ArrayList<String>();
	}
	
	//empty constructor
	public Goal() {
		this.skills = new ArrayList<String>();
	}

	//getters and setters
	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return description; }

	public void setGID(String GID) { this.GID = GID; }
	public String getGID() { return GID; }

	public void addSkill(String newSkill) { this.skills.add(newSkill); }
	public void removeSkill(String oldSkill) { this.skills.removeIf(n -> (n.equals(oldSkill))); }
	public ArrayList<String> getSkills() { return skills; }

	// prints list of skills as a string
	public String skillList() {
		return skills.toString();
	}

	// prints contents as a string
	public String toString() {
		return description + ", skills: " + skillList();
	}
}
