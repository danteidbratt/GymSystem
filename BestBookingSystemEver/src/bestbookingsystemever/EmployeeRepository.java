package bestbookingsystemever;

import java.util.List;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Properties;

public class EmployeeRepository {

    private Properties prop = new Properties();

    public EmployeeRepository() {
        try {
            //prop.load(new FileInputStream("Database.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addEmployee(String name) {

        try (Connection connect = (Connection) DriverManager.getConnection("xxx",
            "xxx",
            "xxx");
                PreparedStatement pstate = (PreparedStatement) connect.prepareStatement("INSERT INTO trainer(name) VALUES (?);");) {
            pstate.setString(1, name);
            int result = pstate.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<EmployeeRepository.Employee> getAllEmployees() throws SQLException {
        try (Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://gymdb.cdxrtv212wn1.eu-central-1.rds.amazonaws.com:3306/GymBookingSystem",
            "Jozephine",
            "pentagon567");
                PreparedStatement pstate = (PreparedStatement) connect.prepareStatement("SELECT * from trainer;");) {
            ResultSet result = pstate.executeQuery();
            java.util.List<Employee> employees = new ArrayList();
            while(result.next()){
                int id = result.getInt("ID");
                String name = result.getString("name");
                employees.add(new Employee(id, name));
            }
            return employees ;
        }
    }

    public static class Employee {

        private int id;
        private String name;

        public Employee(int id, String name) {
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
