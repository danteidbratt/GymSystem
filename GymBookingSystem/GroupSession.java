
package GymBookingSystem;


public class GroupSession {
   private int ID;
   private int capacity;
   private int exerciseTypeID;
   private int sessionID;
    
    public GroupSession(int id, int capacity, int exerciseTypeID,int sessionID){
        this.ID = id;
        this.capacity = capacity;
        this.exerciseTypeID = exerciseTypeID;
        this.sessionID = sessionID;
                    
    }
    
    public GroupSession(){}
    
    public int getGroupSessionID(){
        return ID;
    }
    
    public int getCapacity(){
        return capacity;
    } 
    
    public int exerciseTypeID(){
        return exerciseTypeID;
    }
     public int sessionID(){
        return sessionID;
    }
}
