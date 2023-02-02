package accredyApp.model;

//data concerns:
//rating does not have a "comment"/description, but I don't know if we want one
//Also, I am unsure what rating system we want to use, as I could change the "to String" to match that better (0-5? 0-100?)

public class Rating {
	int score;
	String RID;
	
	//constructor
	public Rating(int rating, String RID) {
		this.score = rating;
		this.RID = RID;
		//does a new resource need any additional instantiation?
	}
	
	//empty constructor
	public Rating() {
	}

	//getters and setters
	public void setScore(int score) { this.score = score; }
	public int getScore() { return score; }

	public void setRID(String RID) { this.RID = RID; }
	public String getRID() { return RID; }

	// prints contents as a string
	public String toString() {
		return "" + score;
	}
}
