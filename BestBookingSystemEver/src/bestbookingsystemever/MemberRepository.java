
package bestbookingsystemever;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MemberRepository {
    
    private Properties prop = new Properties();

    public MemberRepository() {
        try {
            //prop.load(new FileInputStream("Database.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean addMember(String name){

        try (Connection connect = (Connection) DriverManager.getConnection("xxx",
            "xxx",
            "xxx");
                PreparedStatement pstate = (PreparedStatement) connect.prepareStatement("INSERT INTO member(name) VALUES (?);");) {
            pstate.setString(1, name);
            int result = pstate.executeUpdate(); 
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}    

