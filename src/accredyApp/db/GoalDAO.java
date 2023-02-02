package accredyApp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import accredyApp.model.Goal;
import accredyApp.model.Model;


public class GoalDAO {

	java.sql.Connection conn;

    public GoalDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    /*
     * CONTENTS
     * 
     * Get all User Goals 2.b, 2.c **
     * Create new Goal 2.a **
     * Delete Goal 2c **
     * 
     * */
    
 
    
    /**
     * Get a list of all of userID's goals, which each have a GID, a description, and a set of skills.
     * @return a list of goals
     * @throws Exception if the command fails.
     */
    public List<Goal> getAllGoals() throws Exception {
    	List<Goal> allGoals = new ArrayList<>();
        try {
//            PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Goal, savedgoal SG, Userr U WHERE SG.userID = U.userID AND U.userID = ?;");
            PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Goal WHERE GID IN (SELECT SG.GID FROM savedgoal SG WHERE SG.userID = ?);");
            PreparedStatement ps2 = conn.prepareStatement("SELECT keyword FROM goalskill WHERE GID = ?;");
        	ps1.setString(1, Model.profile.getUserID()); //userID comes from profile

            ResultSet resultSetGoal = ps1.executeQuery(); //run the query and store the results in resultSet (this time we expect any number of results)
            

            while (resultSetGoal.next()) {
            	Goal newgoal = generateGoal(resultSetGoal); //use the "generate Goal" method to create a new goal from the results
            	ps2.setString(1, newgoal.getGID()); //use the GID of newgoal to get newgoal's skills
                ResultSet resultSetSkill = ps2.executeQuery();

                while (resultSetSkill.next()) { //double while loop, so that we can get and add every skill to each goal
                	String newkey  = resultSetSkill.getString("keyword");
                    newgoal.addSkill(newkey);
                }
                allGoals.add(newgoal);
                resultSetSkill.close();
            }
            //at this point, we have every user goal in the list list
            
            resultSetGoal.close();
            ps1.close();
            ps2.close();
            return allGoals;

        } catch (Exception e) {
            throw new Exception("Failed in getting user goals: " + e.getMessage());
        }
    }
    
    /**
     * add a new goal to the database with UUID 
     * @param description the string describing the goal.
     * @return the gid of the new goal.
     * @throws Exception if the command fails.
     */
    public String addGoal(String description) throws Exception {
        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Goal WHERE GID = ?;");
            String gid = "";
        	for (int i = 5; i < 10; i++) { //want to make sure we pick a unique GID. try 10 times to get a unique id; if it's still breaking after that then there's something else wrong.
        		//it is practically impossible to overlap these even once, but it sure would suck if it did.
                gid = UUID.randomUUID().toString().substring(0, 8);
	            ps.setString(1, gid);
	            ResultSet resultSet = ps.executeQuery();
	            
	            // not present? then gid is unique and we can proceed.
	            if (!resultSet.next()) {
	            	break;
	            }
        	}

            ps = conn.prepareStatement("INSERT INTO Goal VALUES (?,?);");
            ps.setString(1, gid);
            ps.setString(2, description);
            ps.execute();

            ps = conn.prepareStatement("INSERT INTO savedgoal VALUES (?,?);");
            ps.setString(1, Model.profile.getUserID());
            ps.setString(2, gid);
            ps.execute();

            return gid;

        } catch (Exception e) {
            throw new Exception("Failed to insert goal: " + e.getMessage());
        }
    }
    

    /**
     * Delete the goal.
     * @param gid goal to be deleted
     * @return true if exactly 1 goal was deleted
     * @throws Exception if command failed
     */
    public boolean deleteGoal(String gid) throws Exception {
        try {
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM savedgoal WHERE GID = ?;");
            PreparedStatement ps2 = conn.prepareStatement("DELETE FROM goalskill WHERE GID = ?;");
            PreparedStatement ps3 = conn.prepareStatement("DELETE FROM Goal WHERE GID = ?;");
            ps1.setString(1, gid);
            ps2.setString(1, gid);
            ps3.setString(1, gid);
            ps1.executeUpdate();
            ps2.executeUpdate();
            int numAffected = ps3.executeUpdate();
  
            ps1.close();
            ps2.close();
            ps3.close();
            
            return (numAffected == 1); //bad news if it failed for some reason, because it already 

        } catch (Exception e) {
            throw new Exception("Failed to delete goal: " + e.getMessage());
        }
    }


    
    //UUID.randomUUID().toString() (this generates a good random id, which will be useful )


    private Goal generateGoal(ResultSet resultSet) throws Exception {
    	String gid  = resultSet.getString("GID");
    	String desc = resultSet.getString("description");
    	return new Goal(desc, gid);
    }
    
    
    

}