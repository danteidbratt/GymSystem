
package GymBookingSystem;


public class Duration {
   private int ID;
   private int minutes;
   
   public Duration(int id, int minutes){
        this.ID = id;
        this.minutes = minutes;
                    
    }
    
    public Duration(){}
    
    public int getDurationID(){
        return ID;
    }
    
    public int getDurationMins(){
        return minutes;
    } 
   
   
}
