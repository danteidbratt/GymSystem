
package bestbookingsystemever;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DurationRepository {
    
    private Properties prop = new Properties();

    public DurationRepository() {
        try {
            //prop.load(new FileInputStream("C:\\Users\\Jozephine\\Documents\\Objektori\\Sprint 2 Uppgifter\\BestBookingSystemEver\\src\\bestbookingsystemever\\Database.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Duration> getAll() throws SQLException {

        try (Connection connect = (Connection) DriverManager.getConnection("xxx",
            "xxx",
            "xxx");
                PreparedStatement pstate = (PreparedStatement) connect.prepareStatement("SELECT * FROM duration;");) {
            ResultSet result = pstate.executeQuery(); 
            List<Duration> durations = new ArrayList();
            while(result.next()){
                int id = result.getInt("ID");
                int minutes = result.getInt("minutes");
                durations.add(new DurationRepository.Duration(id, minutes));
            }
            return durations;
        }
    }

    public static class Duration {
        private int id;
        private int minutes;
        
        public Duration(int id, int minutes) {
            this.id = id;
            this.minutes = minutes;
        }

        public int getId() {
            return id;
        }

        public int getMinutes() {
            return minutes;
        }

        @Override
        public String toString() {
            return String.valueOf(minutes);
        } 
    }

}    

