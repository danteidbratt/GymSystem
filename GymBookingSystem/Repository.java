
package GymBookingSystem;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Repository {
    
// private Connection con;
 private Properties p = new Properties();
 private Login login = new Login();
    
    public Repository(){
       
    }
    public void getMembersTraining(String memberName){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int groupsessionID = 0;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);

      PreparedStatement memberStmt = con.prepareStatement("select member.name as 'Member',"
      + " exerciseType.name as 'Exercise',\n" + "groupSession.sessionID as 'Group Session ID',"
      + " session.scheduled as Date from groupSession\n" +
            "inner join booking on booking.groupSessionID = groupSession.ID\n" +
            "inner join member on member.ID = booking.memberID \n" +
            "inner join session on groupSession.sessionID= session.ID\n" +
            "inner join exerciseType on exerciseType.ID = "
            + "groupSession.exerciseTypeID where member.name like ? ");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                exercise = rs.getString("Exercise");
                scheduled = rs.getString("Date");
                groupsessionID = rs.getInt("Group Session ID");
                System.out.println("Member: " + membername + ". " + 
                        "Exercise: " +  exercise + ". " + "Date and Time: " + 
                        scheduled + ". GroupSession ID " + groupsessionID + "\n");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
     public void getIndividualsTraining(String memberName){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int individualsessionID = 0; boolean attendance = true;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);

      PreparedStatement memberStmt = con.prepareStatement("select  "
              + "member.name as 'Member', "
              + "individualSession.sessionID as 'Individual Session ID', "
              + "individualSession.attendance as Attendance, \n" 
              + "session.scheduled as Date from individualSession\n" +
"inner join member on member.ID = individualSession.memberID\n" +
"inner join session on individualSession.sessionID= session.ID\n" +
"where member.name like ? ");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                scheduled = rs.getString("Date");
                attendance = rs.getBoolean("Attendance");
                individualsessionID = rs.getInt("Individual Session ID");
                System.out.println(membername + "   " + 
                         scheduled + "    IndividualSessionID: " + 
                        individualsessionID + "    Attendance: " + attendance + "\n");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back \n");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
     public int checkIfMemberHasIndividualTraining(String memberName){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int individualsessionID = 0, sessionID = 0; boolean attendance = true;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);

      PreparedStatement memberStmt = con.prepareStatement("select  member.name as 'Member', "
              + "individualSession.ID as 'Individual Session ID' from individualSession\n" +
"inner join member on member.ID = individualSession.memberID\n" +
"inner join session on individualSession.sessionID= session.ID\n" +
"where member.name like ? ");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                sessionID = rs.getInt("Individual Session ID");
                System.out.println(sessionID+ "," + membername);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return sessionID;
    }
    public void getAllMembersTraining(){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        String membername = "", exercise = "", scheduled = ""; 
        int groupsessionID = 0; boolean attendance = true;
        
        try { con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);

     PreparedStatement memberStmt = con.prepareStatement("select  "
             + "member.name as 'Member', exerciseType.name as 'Exercise',\n" +
"groupSession.sessionID as 'Group Session ID', session.scheduled as Date, "
             + "booking.attendance as Attendance from groupSession\n" +
"inner join booking on booking.groupSessionID = groupSession.ID\n" +
"inner join member on member.ID = booking.memberID \n" +
"inner join session on groupSession.sessionID= session.ID\n" +
"inner join exerciseType on exerciseType.ID = groupSession.exerciseTypeID;");
            
            //memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                membername = rs.getString("Member");
                exercise = rs.getString("Exercise");
                attendance = rs.getBoolean("Attendance");
                scheduled = rs.getString("Date");
                groupsessionID = rs.getInt("Group Session ID");
                    System.out.println( membername + "    " + exercise 
                    + "    " + scheduled + "     Attendance " + attendance + "\n");
                
            }
        
                         
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
     
    public void getMembersNotes(String memberName){
        ResultSet rs = null;
        Connection con = null;
        String membername = "", comment = "", trainer = "";
        
        try {con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
        PreparedStatement memberStmt = con.prepareStatement("select trainer.name as 'Trainer', "
                + "member.name as 'Member' , comment as 'Comment' from note\n" +
"inner join individualSession on individualSession.ID = note.individualSessionID\n" +
"inner join session on session.ID = individualSession.sessionID\n" +
"inner join member on individualSession.memberID = member.ID\n" +
"inner join trainer on trainer.ID = session.trainerID where member.name like ? ");
            
            memberStmt.setString(1, memberName);
            rs = memberStmt.executeQuery();
            
            while (rs.next()) {
                trainer = rs.getString("Trainer");
                membername = rs.getString("Member");
                comment = rs.getString("Comment");
            }
            System.out.println("Trainer " + trainer + "; Member: " + membername + "; " + 
                    "Comments: " +  comment + "\n");
             
          
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
      
    }
     public void writeMembersNotes(String comment, int individualsessionID){
        ResultSet rs = null;
        Connection con = null;
        comment = ""; String trainer = "";
        individualsessionID = 0;
        int rows = -1;        
        try {con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
        PreparedStatement memberStmt = con.prepareStatement("insert into note "
                + "(comment,individualSessionID ) values (?,?) ");

            memberStmt.setString(1, comment);
            memberStmt.setInt(2, individualsessionID);
            //rows = memberStmt.executeUpdate();
            memberStmt.executeUpdate();
            memberStmt.close();
         
             
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    //  return rows;
    }
     public int checkIfNoteExists(int individualsessionID){
        String query = "select count(note.ID) as Count from note where individualSessionID = ?";

        ResultSet rs = null; 
        ResultSet rs2 = null;
        Connection con = null;
        String comment = "", theCommentAndNr = ""; individualsessionID = 0;
        int rows = -1;
        
        try {con = DriverManager.getConnection(login.connectionString,
                                                login.name,
                                                login.password);
//        PreparedStatement noteStmt = con.prepareStatement("select note.comment as 'FullComment', "
//                + "note.individualSessionID as IndividualSessionID, \n" +
//"trainer.name as Trainer, individualSession.attendance as Attendance, session.scheduled from note\n" +
//"inner join individualSession on individualSession.ID = note.individualSessionID\n" +
//"inner join member on member.ID = individualSession.memberID\n" +
//"inner join session on session.ID = individualSession.sessionID\n" +
//"inner join trainer on trainer.ID = session.trainerID\n" +
//"where individualSession.ID = ?");
        
         PreparedStatement noteStmt2 = con.prepareStatement(query);
           // noteStmt.setInt(1,individualsessionID);
            noteStmt2.setInt(1,individualsessionID);
//            rs = noteStmt.executeQuery();
//            System.out.println("utanför ");
//            while (rs.next()) {
//                
//            }
            
            rs2 = noteStmt2.executeQuery();
            
            while(rs2.next()) {
                if (rs2.getInt("Count") > 0) {
                    individualsessionID = 0 ;
                }
            }
//            System.out.println("Comment: " +  comment + "IndividualSessionID " + individualsessionID );
//           theCommentAndNr = comment; 
        }
        catch (SQLException e){
            e.printStackTrace();
            if (con != null) {
                try {
                    System.out.print("Transaction is being rolled back");
                    con.rollback();
                } 
                catch(SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
      return individualsessionID;
    }
     
     public class FindID {
    
    public int findID(String customerNr, String pnr){
        int i = 1;
        if(customerNr.equals(pnr)){
            i =1;
        }
        else 
            i=2;
        
     return i;   
    }
}
    
    public Trainer logIn(String trainerName) {
        Trainer trainer = new Trainer();
        String query = "select * from trainer where name like ?";
        try(Connection con = DriverManager.getConnection(login.connectionString, login.name, login.password);
                PreparedStatement stmt = con.prepareStatement(query);
                ){
            stmt.setString(1, trainerName);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                trainer.setID(rs.getInt("ID"));
                trainer.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainer;
    }
    
    
    
}
