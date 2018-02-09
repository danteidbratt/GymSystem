
package GymBookingSystem;


public class IndividualSession {
    private int ID;
    private boolean attendance;
    private int memberID;
    private int sessionID;
    
    public IndividualSession(int id, boolean attendance,int memberID, int sessionID ){
        this.ID = id;
        this.attendance = attendance;
                    
    }
    
    public IndividualSession(){}
    
    public int getIndividualSessionID(){
        return ID;
    }
    
    public boolean getSessionAttendance(){
        return attendance;
    }
    
    public int getMemberID(){
        return memberID;
    }
    public int getSessionID(){
        return sessionID;
    }
     
}
