package accredyApp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import accredyApp.model.Model;


public class SkillDAO {

	java.sql.Connection conn;

    public SkillDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    /*
     * CONTENTS
     * 
     * Get all User Skills **
     * Add Skills to User **
     * Delete all User Skills **
     * 
     * Add Skills to Goal **
     * Delete all Goal Skills **
     * 
     * get keyword suggestions based on keyword fragment **
     * 
     * */

    /**
     * get a list of all of the user's keywords
     * @return the list of user keywords
     * @throws Exception if the query fails
     */
    public List<String> getAllUserKeywords() throws Exception {
        
        List<String> allKeywords = new ArrayList<>(); //create an empty list that we can store the new keywords in when we get them
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT keyword FROM userrskill WHERE userID = ?;"); //the select statement will get all of the user's skills
        	ps.setString(1, Model.profile.getUserID()); //userID comes from profile

            ResultSet resultSet = ps.executeQuery(); //run the query and store the results in resultSet (this time we expect any number of results)

            while (resultSet.next()) { //this while loop will run the same code on every tuple that came back from the query
            	String newkey  = resultSet.getString("keyword"); //we copy the value out of the "keyword" column of results, and store it in the String called newkey (defined in this line)
                allKeywords.add(newkey); //add the new keyword to the list we made at the top of the method
            }
            //at this point, we have copied all of the keywords to the allKeywords list
            
            resultSet.close(); //free up the resultSet
            ps.close(); //free up the ps
            return allKeywords; //give this list of keywords to the calling function.

        } catch (Exception e) {
            throw new Exception("Failed in getting user keywords: " + e.getMessage());
        }
    }

    
    
    
    /**
     * Add a list of keywords to the user
     * @param skillList the list of keywords
     * @throws Exception if the insert commands fail
     */
    public void addUserKeywords(List<String> skillList) throws Exception {  //we pass a list of strings (keywords) and one string (the goalID) as input for this one.
        try {
            PreparedStatement ps1 = conn.prepareStatement("INSERT IGNORE INTO Skill VALUES(?);"); //we prepare both insert statements at once, so we can run both commands at once for each keyword
            PreparedStatement ps2 = conn.prepareStatement("INSERT IGNORE INTO userrskill VALUES(?,?);"); //the first inserts into the skill table, the second in the userSkill relation
            //COOL NEWS! I had issues with the fact that we expect to try and fail to insert a lot of duplicates into Skill (since we usually WANT people to use the keywords that have been there).
            //Java usually throws an exception and stops the method if it sees one of these errors, BUT if we add the "INSERT IGNORE" to the commands then it all works! I plan to use it in more places!
            //We definitely need the ignore in the first insert because most keywords should be duplicates. We don't NEED it in the second, except that it will help if the user adds duplicates.
            
            
            for (String k : skillList) { //this is a for each loop, which runs the same code loop FOR EACH keyword in the list of strings.
            	String upperk = k.toUpperCase(); //I create a new string, upperk, which has the same string k but in all caps (we can discuss, but I think the keywords should not be case sensitive. We can either make them all upper case or all lowercase, I'm going with upper case for now.
            	System.out.println(upperk); //testing to make sure the strings look right before they are sent to the database.
            	ps1.setString(1, upperk);  //we set the ? of ps1 as the keyword
            	ps2.setString(1, upperk); //we set the 1st ? of ps2 as the keyword
            	ps2.setString(2, Model.profile.getUserID()); //we set the 2nd ? of ps1 as the keyword
            	//(Note: the command specified in "Queries" was "INSERT INTO UserSkill VALUES(‘userID’, ‘[1]’);", and I've been tweaking this for 20 minutes to get the table name and order of inputs right)
            	
            	ps1.execute(); //execute the first insert
            	ps2.execute(); //execute the second insert
//            	System.out.println("added keyword " + k);
            }
            ps1.close(); //close the prepared statements to free their resources
            ps2.close();
            
            //this one doesn't return anything because there's nothing worthwhile that we can return. I was originally going to count up the number of keywords in the loop that were added, but that would be the same as keywords.size(), but less efficient.
            
        } catch (Exception e) {
            throw new Exception("Failed to insert user skills: " + e.getMessage());
        }
    }

    /**
     * Delete all of the user's Skills in the database
     * @return the number of skills deleted
     * @throws Exception if the delete command fails
     */
    public int deleteUserKeywords() throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM userrskill WHERE userID = ?;");  //command deletes every keyword-relation for user.
            ps.setString(1, Model.profile.getUserID()); //set userID to the one in global profile
            int numAffected = ps.executeUpdate(); //after the executeUpdate, the number of affected keywords is stored in int numAffected
            ps.close(); //free the ps
            
            return numAffected; //return the number of deleted records

        } catch (Exception e) {
            throw new Exception("Failed to delete user skills: " + e.getMessage());
        }
    }

    
    //(I didn't comment the goal skill functions because they are pretty much carbon copies of the userSkill ones.)
    /**
     * Add a list of keywords to the goalID
     * @param keywords the list of keywords
     * @param goalID the goalID to which we connect the new keywords
     * @throws Exception if the insert commands fail
     */
    public void addGoalKeywords(List<String> keywords, String goalID) throws Exception {
        try {
            PreparedStatement ps1 = conn.prepareStatement("INSERT IGNORE INTO Skill VALUES(?);");
            PreparedStatement ps2 = conn.prepareStatement("INSERT IGNORE INTO goalskill VALUES(?,?);");
            
            for (String k : keywords) { //for each keyword, add it to both tables
            	String upperk = k.toUpperCase(); //I create a new string, upperk, which has the same string k but in all caps (we can discuss, but I think the keywords should not be case sensitive. We can either make them all upper case or all lowercase, I'm going with upper case for now.
            	ps1.setString(1, upperk);
            	ps2.setString(1, upperk);
            	ps2.setString(2, goalID);
            	ps1.execute();
            	ps2.execute();
            }
            ps1.close();
            ps2.close();

            
        } catch (Exception e) {
            throw new Exception("Failed to insert user skills: " + e.getMessage());
        }
    }

    
    /**
     * Delete all of the goal's Skills in the database
     * @param goalID the number 
     * @return the number of goal skills deleted
     * @throws Exception if the delete command fails
     */
    public int deleteGoalKeywords(String goalID) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM goalskill WHERE userID = ?;");
            ps.setString(1, Model.profile.getUserID());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return numAffected;

        } catch (Exception e) {
            throw new Exception("Failed to delete goal skills: " + e.getMessage());
        }
    }

    
    /**
     * Given a partial keyword, get a list of every Skill in the database that contains the partial keyword
     * @param partialkey the partial keyword
     * @return
     * @throws Exception
     */
    public List<String> getSuggestions(String partialkey) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT S.keyword FROM Skills S WHERE S.keyword LIKE ?;"); //This one (which hasn't actually been tested yet) will give us every keyword that contains the string in ?
            ps.setString(1, "%" + partialkey + "%"); //TODO this is suspicious and should be tested, but in theory it should add %% around the "partialkey" string that was input, like "%program%"
//            System.out.println(ps.toString());  //I think this will let me see what the query looks like, though I haven't tried it yet.
            ResultSet resultSet = ps.executeQuery(); //this executes the query and returns the tuples in resultSet

            List<String> results = new ArrayList<String>(); //create a new (empty) list of strings that will store all of the db results
            while (resultSet.next()) {  //this repeats for every tuple that returned, and stops when we run out
            	String newkey  = resultSet.getString("name"); //get the String stored in the "name" column and store it in newkey
            	results.add(newkey); //add the newkey String to the list of strings (results) that we made above
            }
            
            resultSet.close(); //close the resultSet when we've finished getting its keywords
            ps.close(); //free the ps
            
            return results; //return the list of results

        } catch (Exception e) {
            throw new Exception("Failed to get suggestions: " + e.getMessage());
        }
    }

    
}