package accredyApp.model;

import java.util.*;

public class Model {
	//attributes
	public static UserInfo profile;
	//Story: I made a bunch of these public lists because I thought we might store the data long-term as we pass them around the controllers and frames. Then I realized it's simpler to just pass them locally, so I commented them out because they are unused.
	//NOW I realize that the SearchResources and SearchJobs need to be able to access the same list across controllers, so I added those ones back in and I'll add more in if the need arises.
	List<Goal> savedGoals;
	List<Resource> savedResources;
//	List<Resource> availResources;
	List<Job> savedJobs;
//	List<Job> availJobs;
//	List<UserInfo> attendees;
//	List<Rating> ratingList;
//	List<String> userSkills;
	
	public Model() {
		//instantiation
		Model.profile = new UserInfo();
		this.savedGoals = new ArrayList<Goal>();
		this.savedResources = new ArrayList<Resource>();
//		this.availResources = new ArrayList<Resource>();
		this.savedJobs = new ArrayList<Job>();
//		this.availJobs = new ArrayList<Job>();
//		this.attendees = new ArrayList<UserInfo>();
//		this.ratingList = new ArrayList<Rating>();
//		this.userSkills = new ArrayList<String>();
	}
	
	//add public get/set methods for the attributes
	public void setProfile(UserInfo profile) { this.profile = profile; }
	public UserInfo getProfile() { return profile; }
	
	//I made a WHOLE BUNCH of arraylists to store lists we get from the database, but now I realize it might be better to leave that locally to the controllers... oops...
	public void addSavedGoal(Goal newGoal) { this.savedGoals.add(newGoal); }
	public void removeSavedGoal(String oldGoalID) { this.savedGoals.removeIf(n -> (n.GID.equals(oldGoalID))); } //if we want to remove one goal from the list, do we want to identify it by goalID? alternative is to pass it the goal object, but idk if we will have that pointer when we want to remove the goal
	public List<Goal> getSavedGoals() { return savedGoals; }
	public void setSavedGoals(List<Goal> newGoals) { this.savedGoals = newGoals; }
	
	public void addSavedResource(Resource newResource) { this.savedResources.add(newResource); }
	public void removeSavedResource(String oldResourceID) { this.savedResources.removeIf(n -> (n.RID.equals(oldResourceID))); } //if we want to remove one resource from the list, do we want to identify it by resourceID?
	public List<Resource> getSavedResources() { return savedResources; }
	public void setSavedResources(List<Resource> newResources) { this.savedResources = newResources; }

/*	public void addAvailResource(Resource newResource) { this.availResources.add(newResource); }
	public void removeAvailResource(String oldResourceID) { this.availResources.removeIf(n -> (n.RID.equals(oldResourceID))); } //(same question stands for ALL of these)
	public List<Resource> getAvailResources() { return availResources; }
*/
	
	public void addSavedJob(Job newJob) { this.savedJobs.add(newJob); }
	public void removeSavedJob(String oldJobID) { this.savedJobs.removeIf(n -> (n.JID.equals(oldJobID))); }
	public List<Job> getSavedJobs() { return savedJobs; }
	public void setSavedJobs(List<Job> newJobs) { this.savedJobs = newJobs; }
	
/*
	public void addAvailJob(Job newJob) { this.availJobs.add(newJob); }
	public void removeAvailJob(String oldJobID) { this.availJobs.removeIf(n -> (n.JID.equals(oldJobID))); }
	public List<Job> getAvailJobs() { return availJobs; }

	public void addAttendee(UserInfo newUserInfo) { this.attendees.add(newUserInfo); }
	public void removeAttendee(String oldUserID) { this.attendees.removeIf(n -> (n.userID.equals(oldUserID))); }
	public List<UserInfo> getAttendees() { return attendees; }

	public void addRating(Rating newRating) { this.ratingList.add(newRating); }
	public void removeRating(String oldRatingID) { this.ratingList.removeIf(n -> (n.RID.equals(oldRatingID))); } //if we want to remove one rating from the list, do we want to identify it by resourceID? THIS ONE IS EXTRA TRICKY since it's identified by RESOURCEID instead of RATINGID.
	public List<Rating> getRatings() { return ratingList; }

	public void addUserSkill(String newSkill) { this.userSkills.add(newSkill); }
	public void removeSkill(String oldSkill) { this.userSkills.removeIf(n -> (n.equals(oldSkill))); }
	public List<String> getSkills() { return userSkills; }*/

	//add any other functions that should act on model, public or private
	

    @Override
	public String toString() {
    	//TODO idk if we will have any use for this so I'm leaving it blank for now
		return "hello world!";
	}

}
