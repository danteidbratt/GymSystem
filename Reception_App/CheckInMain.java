package gruppuppgiftdatabas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CheckInMain {
	
	InputStream input = null;
	LoginInfo l = new LoginInfo();	
	Properties prop = new Properties();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	Members currentMember;
	Scanner scanner;
	IndividualSession nextInd;
	GroupSession nextGrp;

	public void setProperty() {
	
    try {

        input = new FileInputStream("src/gruppuppgiftdatabas/config.properties");

        // load a properties file
        prop.load(input);

        l.setUsername(prop.getProperty("username"));
       l.setPassword(prop.getProperty("password"));
      l.setPath(prop.getProperty("Path"));

    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            	}
        	}
    	}
	}
    
	public CheckInMain() {
		scanner = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Members getMember(String name) {
		
		Members m = new Members();
		
		try ( Connection con = DriverManager.getConnection(l.getPath(), l.getUsername(),l.getPassword());
        PreparedStatement stmt = con.prepareStatement("Select * from member where name = ?;");){
        	stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
        	
        	m.setID(rs.getInt("ID"));
        	m.setName(rs.getString("name"));
        	
        }
			
        } catch (SQLException e) {
	e.printStackTrace();
        }     
		return m;
	}
	
	public List<IndividualSession> getIndListInMember(int ID){
		List<IndividualSession> IndList = new ArrayList<>();
		try ( Connection con = DriverManager.getConnection(l.getPath(), l.getUsername(),l.getPassword());
		        PreparedStatement stmt = con.prepareStatement("Select individualSession.id as 'indID'"
		        		+ ", individualSession.attendance as 'Attendance',"
		        		+ "session.id as 'SessID', "
		        		+ "session.scheduled as 'DateTime' from individualSession "
		        		+ "inner join session on individualSession.sessionID = session.ID "
		        		+ " where memberID = ?;");){
		        	stmt.setString(1, String.valueOf(ID));
					ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				IndividualSession temp = new IndividualSession();
				temp.setindID(rs.getInt("indID"));
				temp.setID(rs.getInt("SessID"));
				temp.setAttendance(rs.getBoolean("Attendance"));
				temp.setDateTime(LocalDateTime.parse(rs.getString("DateTime").substring(0, 19), dtf));
				IndList.add(temp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return IndList;
		
	}
	
	public List<Booking> getBookingsInMember(int ID){
		List<Booking> bookList = new ArrayList<>();
		try ( Connection con = DriverManager.getConnection(l.getPath(), l.getUsername(),l.getPassword());
		        PreparedStatement stmt = con.prepareStatement("Select booking.id as 'BookingID'"
		        		+ ", booking.attendance as 'Attendance', groupSession.id as 'GrpID',"
		        		+ "session.id as 'SessID', session.scheduled as 'DateTime' from booking "
		        		+ "inner join groupSession on booking.groupSessionID = groupSession.id "
		        		+ "inner join session on groupSession.sessionID = session.ID where "
		        		+ "booking.memberid = ?;");){
			
		        	stmt.setString(1, String.valueOf(ID));
					ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Booking temp = new Booking();
				temp.setID(rs.getInt("BookingID"));
				temp.setAttendance(rs.getBoolean("Attendance"));
				temp.getGrpSess().setID(rs.getInt("SessID"));
				temp.getGrpSess().setgrpID(rs.getInt("GrpID"));
				temp.getGrpSess().setDateTime(LocalDateTime.parse(rs.getString("DateTime").substring(0, 19), dtf));
				bookList.add(temp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return bookList;
		
	}
	
	public int checkInIndividual(int indID) {
		int rowsAffected = -1;
		try ( Connection con = DriverManager.getConnection(l.getPath(), l.getUsername(),l.getPassword());
		        PreparedStatement stmt = con.prepareStatement("update individualSession set attendance = 1 where ID = ?;");){
		        	stmt.setString(1, String.valueOf(indID));
					rowsAffected = stmt.executeUpdate();	
		        } catch (SQLException e) {
			e.printStackTrace();
		        }     
				return rowsAffected;
	}
	
	public int checkInGroup(int GrpID) {
		int rowsAffected = -1;
		try ( Connection con = DriverManager.getConnection(l.getPath(), l.getUsername(),l.getPassword());
		        PreparedStatement stmt = con.prepareStatement("update booking set attendance = 1 where groupSessionID = ?;");){
		        	stmt.setString(1, String.valueOf(GrpID));
					rowsAffected = stmt.executeUpdate();	
		        } catch (SQLException e) {
			e.printStackTrace();
		        }     
				return rowsAffected;
	}
	
	public void start() {
		System.out.println("Ange namn:");
		currentMember = getMember(scanner.nextLine());
		currentMember.setIndList(getIndListInMember(currentMember.getID()));
		currentMember.setBookList(getBookingsInMember(currentMember.getID()));
		
		List<IndividualSession> legitInds = currentMember.getIndList().stream()
				.filter(a -> !a.isAttendance() && a.getDateTime().isAfter(LocalDateTime.now()))
				.collect(Collectors.toList());
		
		List<GroupSession> legitGrps = currentMember.getBookList().stream()
				.filter(b -> !b.isAttendance())
				.map(a -> a.getGrpSess())
				.filter(c -> c.getDateTime().isAfter(LocalDateTime.now()))
				.collect(Collectors.toList());
		try {
		nextInd = legitInds.get(0);
		}catch(IndexOutOfBoundsException e) {
		}try {
		nextGrp = legitGrps.get(0);
		}catch(IndexOutOfBoundsException e) {
		}
		
		for(IndividualSession i : legitInds) {
			if(i.getDateTime().isBefore(nextInd.getDateTime())) {
				nextInd = i;
			}
		}
		
		for(GroupSession g : legitGrps) {
			if(g.getDateTime().isBefore(nextGrp.getDateTime())) {
				nextGrp = g;
			}
		}
		
		if(nextInd != null) {
			if (nextGrp != null) {
				if(nextInd.getDateTime().isBefore(nextGrp.getDateTime())) {
					checkInIndividual(nextInd.getindID());
					System.out.println("Du har checkats in på ett PT-pass");
				}
				else {
					checkInGroup(nextGrp.getgrpID());
					System.out.println("Du har checkats in på ett Grupp-pass");
				}
			}
			else {
				checkInIndividual(nextInd.getindID());
				System.out.println("Du har checkats in på ett PT-pass");
			}
		}
		else if (nextGrp != null) {
			checkInGroup(nextGrp.getgrpID());
			System.out.println("Du har checkats in på ett Grupp-pass");
		}
		else {
			System.out.println("Du har inga bokade pass");
		}
		
	}
		public static void main(String[] args) {
			CheckInMain c = new CheckInMain();
			c.setProperty();
			c.start();
		}
}
