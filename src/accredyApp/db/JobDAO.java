package accredyApp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import accredyApp.model.Job;
import accredyApp.model.Model;
import accredyApp.model.Resource;
import accredyApp.model.UserInfo;


public class JobDAO {

	java.sql.Connection conn;

    public JobDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    /*
     * CONTENTS
     * 
     * Get all User Jobs 4.b 4.c **
     * Save Jobs for User 4.a **
     * Delete Job for User 4.c **
     * Get Jobs relevant to a Goal 4.a **
     * 
     * */

    /**
     * Get a list of the jobs that a user has saved
     * @return the list of jobs
     * @throws Exception if the command failed
     */
    public List<Job> getSavedJobs() throws Exception {
        
        List<Job> allJobs = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Job WHERE JID IN (SELECT UJ.JID FROM userjob UJ WHERE UJ.userID=?);");
            ps.setString(1, Model.profile.getUserID());
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Job j = generateJob(resultSet);
                allJobs.add(j);
            }
            
            resultSet.close();
            ps.close();
            return allJobs;

        } catch (Exception e) {
            throw new Exception("Failed in getting Jobs: " + e.getMessage());
        }        
    }

    

	/**
     * saves a set of jobs for the user on the database.
     * @param jlist the list of jobs
     * @throws Exception if the command fails
     */
    public void saveJob(List<Job> jlist) throws Exception {
        try {

            PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO userjob VALUES (?,?);");
            ps.setString(1, Model.profile.getUserID());

            for(Job j : jlist) {
                ps.setString(2, j.getJID());
                ps.execute();
            }
            //currently returns nothing. Could tweak it to return the number of saved resources, but idk if it's worth the extra processing.

        } catch (Exception e) {
            throw new Exception("Failed to save jobs: " + e.getMessage());
        }
    }

    
    
    /**
     * remove a job from the user's list
     * @param JID the JID of the job to be removed
     * @return true if the delete worked
     * @throws Exception if the command failed
     */
    public boolean deleteJob(String JID) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM userjob WHERE userID = ? AND JID = ?;");
            ps.setString(1, Model.profile.getUserID());
            ps.setString(2, JID);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete Job: " + e.getMessage());
        }
    }

    
    
    /**
     * return all jobs that match a keyword of the goal
     * @param GID the input goal
     * @return a list of jobs
     * @throws Exception if the command fails
     */
    public List<Job> getJobsByGoal(String GID) throws Exception {
        //TODO THE QUERY DOESN'T WORK! To test I let it return every job, but this needs to be fixed!
    	
        List<Job> allJobs = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Job WHERE JID in(" + 
            		"    SELECT JS.JID FROM jobskill JS WHERE JS.keyword in(" + 
            		"        SELECT GS.keyword FROM goalskill GS WHERE GS.GID=?));");
            ps.setString(1, GID);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                Job j = generateJob(resultSet);
                allJobs.add(j);
            }
            
            resultSet.close();
            ps.close();
            return allJobs;

        } catch (Exception e) {
            throw new Exception("Failed in getting Jobs: " + e.getMessage());
        }        
    }

    

    private Job generateJob(ResultSet resultSet) throws Exception {
    	String JID  = resultSet.getString("JID");
    	String name  = resultSet.getString("jname");
    	String location  = resultSet.getString("jlocation");
    	float salary = resultSet.getFloat("jsalary");
    	return new Job (JID, name, location, salary);
	}

}