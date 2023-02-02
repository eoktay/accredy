package accredyApp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import accredyApp.model.UserInfo;
import accredyApp.model.Model;


public class ProfileDAO {

	java.sql.Connection conn;

    public ProfileDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
            System.out.println("couldn't make the connection here"); //print statement, so I know that the login didn't work
    		conn = null;
    	}
    }
    
    /*
     * CONTENTS
     * 
     * Create Profile **
     * Validate Login (+ full name) **
     * Update Profile **
     * Update Password **
     * 
     * */
    
    /**
     * Insert a new user into the database
     * @param password the password of the new user
     * @return true if the userID didn't already exist
     * @throws Exception if the command fails
     */ //this type of block comment appears before each function just so that we know what it does, what it inputs and what it outputs, for the sake of documentation.
    public boolean addUser(String password) throws Exception {
        try {
        	//This code starts by checking whether the userID already exists. Part of me says this is redundant, but part of me also says this will help give feedback for the user.
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Userr WHERE userID = ?;"); //This will return anything with the userID we are planning to insert
            ps.setString(1, Model.profile.getUserID()); //set the first ? to be the userID, which is part of the global UserInfo named "profile" (see model.Model, and model.UserInfo)
            ResultSet resultSet = ps.executeQuery(); //this executes the query, and stores whatever tuples return in a "ResultSet"
            
            // already present?
            while (resultSet.next()) { //if the ResultSet has at least one tuple, the userID is already in use so we enter this while loop.
                resultSet.close(); //free up the result set because we don't need it anymore
                return false; //this ends the function and returns "false", so whatever called this addUser() method can know that the userID was already in use.
            }

            ps = conn.prepareStatement("INSERT INTO Userr VALUES(?,?,?,?);"); //We overwrite the query at the top with a new command, which will insert a new Userr tuple.
            ps.setString(1,  Model.profile.getUserID()); //first ? is the userID, which is stored in the global profile (btw, global profile just has a userID, first name, and last name)
            ps.setString(2,  Model.profile.getFirstName()); //second ? is the first name, also stored in the global profile
            ps.setString(3,  Model.profile.getLastName()); //third ? is the last name, also stored in the global profile
            ps.setString(4,  password); //last ? is the password. We don't want to store that because it's insecure, so instead the password has to be passed directly into this method (see the addUser(String password) at the top of the method) 
            System.out.println("add User."); //print statement, just so I can be sure that the command went through.
            ps.execute(); //this runs the command. We don't get any feedback whether it worked.
            return true; //if we got to this point, we can only assume the insert command was successful, so we let the controller that called this function know that it worked by returning "true".

        } catch (Exception e) { //we have to surround these database commands with a "try catch block" because it's so nondeterministic. 
            throw new Exception("Failed to add user: " + e.getMessage()); //we "throw an exception" so that if part of this messes up, we print out that "Failed to add user" as well as the exact error message. This helps us debug.
        }
    }

    /**
     * Validate the login credentials provided by the user.
     * @param password the password to verify
     * @return return true if the user login is valid.
     * @throws Exception if the command fails unexpectedly.
     */
    public boolean logIn(String password) throws Exception {
        try {
            System.out.println("Before query execution"); //print statement, so I know that the login didn't work
            PreparedStatement ps = conn.prepareStatement("SELECT ufname, ulname FROM Userr WHERE userID = ? AND password = ?;"); //This select will return the user's first and last name if their username and password match. If not, it gives us nothing.
            System.out.println("a"); //print statement, so I know that the login didn't work
            ps.setString(1, Model.profile.getUserID()); //the first ? is userID, from the global profile
            System.out.println("b"); //print statement, so I know that the login didn't work
            ps.setString(2, password); //the second ? is password. We don't store that for security reasons, so it has to be passed in (so the controller that calls this calls it like "logIn(pass)" )
            System.out.println("c"); //print statement, so I know that the login didn't work
            ResultSet resultSet = ps.executeQuery(); //this sends the query to the database, and stores any results in ResultSet. We expect either 0 or 1 tuples to return.
            System.out.println("After query execution"); //print statement, so I know that the login didn't work

            // did the tuple return a result?
            if (resultSet.next()) { //if there was a tuple returned, then the login was good and we enter this if. If we make this "resultSet.next()" into a while loop, we can do the same code for every tuple that returns (good for lists).
                Model.profile.setFirstName(resultSet.getString("ufname")); //we get the tuple's ufname data and store that in the global profile.
                Model.profile.setLastName(resultSet.getString("ulname")); //we get the tuple's ulname data and store that in the global profile.
                resultSet.close(); //we don't need the resultSet anymore, so we release it (to get back the memory/thread/other resources it was using)
                System.out.println("logged in."); //print statement so I know that the command worked.
                return true; //the method stops running here. It returns true so that the controller that called logIn() knows that the login worked.
            }

            //If I got here, the username and password did not match the database.
            System.out.println("failed to log in."); //print statement, so I know that the login didn't work
            return false; //return false so the calling controller knows that the login failed.

        } catch (Exception e) {  //we have to surround these database commands with a "try catch block" because it's so nondeterministic.
            throw new Exception("Failed to login: " + e.getMessage()); //we "throw an exception" so that if part of this messes up, we print out that "Failed to login" as well as the exact error message. This helps us debug.
        }
    }

    /**
     * Update the name of the user
     * @return true if exactly 1 profile was updated (this is probably redundant tbh)
     * @throws Exception if the update command failed unexpectedly
     */
    public boolean updateProfile() throws Exception {
        try {
        	String query = "UPDATE Userr SET ufname=?, ulname=? WHERE userID=?;"; //this command updates the first and last name based on userID.
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Model.profile.getFirstName()); // first ? is first name from profile
            ps.setString(2, Model.profile.getLastName()); //second ? is last name from profile
            ps.setString(3, Model.profile.getUserID()); //third ? is userID from profile
            int numAffected = ps.executeUpdate(); //executeUpdate specifically works for update functions, and it returns the number of tuples that were updated (stored in int numAffected)
            ps.close(); // free up the prepared statement now that we've finished using it. This is good practice, tbh I should use it more often.
            
            System.out.println("updated profile."); //print statement which just appears in the console to make it clear when the command runs
            return (numAffected == 1); //this returns true if exactly 1 record was updated, false otherwise.
        } catch (Exception e) {
            throw new Exception("Failed to update profile: " + e.getMessage());
        }
    }

    /**
     * Update the user's password
     * @param password the new password
     * @return true if exactly 1 password was changed.
     * @throws Exception if the command failed.
     */
    public boolean updatePassword(String password) throws Exception {
        try {
        	String query = "UPDATE Userr SET password=? WHERE userID=?;"; //this is almost exactly the same as the one above, except password is passed in as a parameter instead of getting the name from the global profile.
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, Model.profile.getUserID());
            int numAffected = ps.executeUpdate();
            ps.close();
            
            System.out.println("updated password.");
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update password: " + e.getMessage());
        }
    }


}