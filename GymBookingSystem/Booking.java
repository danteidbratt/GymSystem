
package GymBookingSystem;


public class Booking {
   private int ID;
   private int memberID;
   private int groupSessionID;
   
   public Booking(int id, int memberID, int groupSessionID){
        this.ID = id;
        this.memberID = memberID;
        this.groupSessionID = groupSessionID;
                    
    }
    
    public Booking(){}
    
    public int getBookingID(){
        return ID;
    }
    
    public int getMemberID(){
        return memberID;
    } 
    
    public int getGroupSessionID(){
        return groupSessionID;
    }
}
