package bestbookingsystemever;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

public class SessionRepository {

    private Properties prop = new Properties();

    public SessionRepository() {
        try {
            //prop.load(new FileInputStream("C:\\Users\\Jozephine\\Documents\\Objektori\\Sprint 2 Uppgifter\\BestBookingSystemEver\\Database.properties"));
            //System.out.println(prop.getProperty("connectionString"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int addGroupSession(String requestedTime, int exerciseID, int capacity, int hallID, int durationID, int trainerID) throws SQLException {
        try (Connection connect = (Connection) DriverManager.getConnection("xxx",
            "xxx",
            "xxx");
                CallableStatement stmt = connect.prepareCall("{call AddGroupSession(?, ?, ?, ?, ?, ?, ?)};");) {
            stmt.setTimestamp(1, Timestamp.valueOf(requestedTime));
            stmt.setInt(2, exerciseID);
            stmt.setInt(3, capacity);
            stmt.setInt(4, hallID);
            stmt.setInt(5, durationID);
            stmt.setInt(6, trainerID);
            stmt.registerOutParameter(7, java.sql.Types.INTEGER);            
            stmt.execute();
            int result = stmt.getInt(7);
            return result;
        }
    }
    
    public int addIndividualSession(String requestedTime, int hallID, int durationID, int trainerID) throws SQLException {
        try (Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://gymdb.cdxrtv212wn1.eu-central-1.rds.amazonaws.com:3306/GymBookingSystem",
            "Jozephine",
            "pentagon567");
                CallableStatement stmt = connect.prepareCall("{call AddIndividualSession(?, ?, ?, ?, ?)};");) {
            stmt.setTimestamp(1, Timestamp.valueOf(requestedTime));
            stmt.setInt(2, durationID);
            stmt.setInt(3, trainerID);
            stmt.setInt(4, hallID);
            stmt.registerOutParameter(5, java.sql.Types.INTEGER);            
            stmt.execute();
            int result = stmt.getInt(5);
            return result;
        }
    }
}

