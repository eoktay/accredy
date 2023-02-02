package accredyApp.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class UserDAO {

	java.sql.Connection conn;

    public UserDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    //UUID.randomUUID().toString() (this generates a good random id, which will be useful )
    
  //ultra-basic example proving that we can connect to the database AND execute queries. Worthless for our final product, but a good start.
    public boolean testConnection() throws Exception {
        
        try {
            Statement statement = conn.createStatement();
            //feel free to insert some other command here, if you would like another test.
            String query = "SELECT * FROM User";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
            	System.out.println("I see a tuple!"); //this will print out once per tuple returned.
            }
        	System.out.println("no more results.");
            
            statement.close();
            return true;

        } catch (Exception e) {
            throw new Exception("Query or Connection failed: " + e.getMessage());
        }
    }

    
    
    
    
/* I copied this DAO from another project (formerly ConstantDAO) to mimic its layout. These commands were included in that project, and I've commented them out in case we want examples when writing our DAOs, but overall we can delete them when we see fit.
    public Constant getConstant(String name) throws Exception {
        
        try {
            Constant constant = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Constants WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                constant = generateConstant(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return constant;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting constant: " + e.getMessage());
        }
    }
    
    public boolean deleteConstant(Constant constant) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Constants WHERE name = ?;");
            ps.setString(1, constant.name);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public boolean updateConstant(Constant constant) throws Exception {
        try {
        	String query = "UPDATE Constants SET value=? WHERE name=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, constant.value);
            ps.setString(2, constant.name);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update report: " + e.getMessage());
        }
    }
    
    
    public boolean addConstant(Constant constant) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Constants WHERE name = ?;");
            ps.setString(1, constant.name);
            ResultSet resultSet = ps.executeQuery();
            
            // already present?
            while (resultSet.next()) {
                Constant c = generateConstant(resultSet);
                resultSet.close();
                return false;
            }

            ps = conn.prepareStatement("INSERT INTO Constants (name,value) values(?,?);");
            ps.setString(1,  constant.name);
            ps.setDouble(2,  constant.value);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
    }

    public List<Constant> getAllConstants() throws Exception {
        
        List<Constant> allConstants = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Constants";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Constant c = generateConstant(resultSet);
                allConstants.add(c);
            }
            resultSet.close();
            statement.close();
            return allConstants;

        } catch (Exception e) {
            throw new Exception("Failed in getting books: " + e.getMessage());
        }
        
        private Constant generateConstant(ResultSet resultSet) throws Exception {
        	String name  = resultSet.getString("name");
        	Double value = resultSet.getDouble("value");
        	return new Constant (name, value);
        }*/

}