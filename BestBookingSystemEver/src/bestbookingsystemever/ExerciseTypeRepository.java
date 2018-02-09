
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

public class ExerciseTypeRepository {
    
    private Properties prop = new Properties();

    public ExerciseTypeRepository() {
        try {
            //prop.load(new FileInputStream("Database.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<ExerciseType> getAll() throws SQLException {

        try (Connection connect = (Connection) DriverManager.getConnection("xxx",
            "xxx",
            "xxx");
                PreparedStatement pstate = (PreparedStatement) connect.prepareStatement("SELECT * FROM exerciseType;");) {
            ResultSet result = pstate.executeQuery(); 
            List<ExerciseType> halls = new ArrayList();
            while(result.next()){
                int id = result.getInt("ID");
                String name = result.getString("name");
                halls.add(new ExerciseTypeRepository.ExerciseType(id, name));
            }
            return halls;
        }
    }

    public static class ExerciseType {
        private int id;
        private String name;
        
        public ExerciseType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
        
        
    }

}    

