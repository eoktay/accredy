package accredyApp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import accredyApp.model.Job;
import accredyApp.model.Model;
import accredyApp.model.Resource;
import accredyApp.model.UserInfo;


public class ResourceDAO {

	java.sql.Connection conn;

    public ResourceDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    /*
     * CONTENTS
     * 
     * Get all User Resources **
     * Save Resources for User 3.a **
     * Delete Resource for User 3.c **
     * Get Resources relevant to a Goal 3.a **
     * Get Goals relevant to a Resource 3.b //TODO I am *this* close to just nixing this part of it. This was part of a user story Erden suggested, but he's barely allocated enough space for just the title, let alone all this. I'm skipping this until we decide we definitely want to add this, after the rest is implemented.
     * Rate Resource ** 
     * Get Attendees **
     * 
     * */

    //UUID.randomUUID().toString() (this generates a good random id, which will be useful )
    
    /**Get all of the user's resources.
     * Input userID (no need to pass it in)
     * @return a list of all Resources saved by the individual
     * @throws Exception failed to get the get the Resources from the database
     */
    public List<Resource> getSavedResources() throws Exception {
        
        ArrayList<Resource> allResources = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Resource_E WHERE RID IN (SELECT RI.RID FROM resourceint RI WHERE RI.userID=?);");
            ps.setString(1, Model.profile.getUserID());
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Resource r = generateResource(resultSet);
                allResources.add(r);
            }
            
            resultSet.close();
            ps.close();
            return allResources;

        } catch (Exception e) {
            throw new Exception("Failed in getting Resources: " + e.getMessage());
        }        
    }
    
    /**
     * saves a set of resources for the user en masse
     * @param rlist the list of resources to save
     * @throws Exception if the command fails
     */
    public void saveResource(List<Resource> rlist) throws Exception {
        try {

            PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO resourceint VALUES (?,?);");
            ps.setString(2, Model.profile.getUserID());

            for(Resource r : rlist) {
                ps.setString(1, r.getRID());
                ps.execute();            	
            }
            //currently returns nothing. Could tweak it to return the number of saved resources, but idk if it's worth the extra processing.

        } catch (Exception e) {
            throw new Exception("Failed to save resources: " + e.getMessage());
        }
    }
    
    /**
     * remove a resource from the user's saved list
     * @param RID the RID of the resource to be removed
     * @return true if the delete was successful
     * @throws Exception if the command failed
     */
    public boolean deleteResource(String RID) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM resourceint WHERE userID = ? AND RID = ?;");
            ps.setString(1, Model.profile.getUserID());
            ps.setString(2, RID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete Resource: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    /**
     * Get the list of all resources that match a goal by keyword
     * @param GID the goal
     * @return a list of resources
     * @throws Exception if the command fails
     */
    public List<Resource> getResouresByGoal(String GID) throws Exception {
    	
        List<Resource> allResources = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Resource_E WHERE RID in ("
            		+ "SELECT RS.RID FROM resourceskill RS WHERE RS.keyword in ("
            		+ "SELECT GS.keyword FROM goalskill GS WHERE GS.GID=?));");
            ps.setString(1, GID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Resource r = generateResource(resultSet);
                allResources.add(r);
            }
            
            resultSet.close();
            ps.close();
            return allResources;

        } catch (Exception e) {
            throw new Exception("Failed in getting Resources: " + e.getMessage());
        }        
    }

    
    
        
    /**
     * Get a list of users that are interested in the input resource
     * @param r the resource for which we want a list of attendees
     * @return a list of UserInfo (no passwords)
     * @throws Exception if the query fails
     */
    public List<UserInfo> getAttendees(Resource r) throws Exception {
        
        ArrayList<UserInfo> attendees = new ArrayList<UserInfo>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT U.ufname, U.ulname, U.userID FROM Userr U, resourceint RI WHERE RID=? AND U.userID = RI.userID;");
            ps.setString(1, r.getRID());
            System.out.println(r.getRID());
            ResultSet resultSet = ps.executeQuery();
            System.out.println(ps.toString());
            
            while (resultSet.next()) {
                UserInfo u = generateAttendee(resultSet);
                System.out.println("one attendee");
                attendees.add(u);
            }
            
            resultSet.close();
            ps.close();
            return attendees;

        } catch (Exception e) {
            throw new Exception("Failed in getting attendees: " + e.getMessage());
        }
    }

        

        /**
         * Insert a rating.
         * @param resource the resourse to rate
         * @param rating the rating from 1 to 5
         * @param comment the user comment about the resource
         * @param completed the date that the user completed the resource, to deter people from prematurely rating things.
         * @return true if the command works
         * @throws Exception if the command fails
         */
        public boolean addRating(Resource resource, int rating, String comment, Date completed) throws Exception { //TODO it would be a nice feature if we allow users to change ratings. you'll need a getRating DAO, and to tweak this one to update if the rating exists and do this create if it does not.
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO resourcerating VALUES(?,?,?,?,?);");
                ps.setString(1,  resource.getRID());
                ps.setString(2,  Model.profile.getUserID());
                ps.setInt(3,  rating);
                ps.setString(4,  comment);
                ps.setDate(5,  completed);
                ps.execute();
                return true;

            } catch (Exception e) {
                throw new Exception("Failed to insert new rating: " + e.getMessage());
            }
        }

        private Resource generateResource(ResultSet resultSet) throws Exception {
        	String name  = resultSet.getString("rname");
        	String RID = resultSet.getString("RID");
        	return new Resource (RID, name);
        }
        
        private UserInfo generateAttendee(ResultSet resultSet) throws Exception {
        	String fname  = resultSet.getString("ufname");
        	String lname  = resultSet.getString("ulname");
        	String uname  = resultSet.getString("userID");
        	return new UserInfo(fname, lname, uname);
        }

        
}