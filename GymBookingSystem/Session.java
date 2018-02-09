package GymBookingSystem;

import java.time.LocalDateTime;


public class Session {
    private int ID;
    private LocalDateTime scheduled;
    private int trainerID;
    private int durationID;
    private int hallID;
    
    public Session(int id, LocalDateTime scheduled, int trainerID, int durationID, int hallID){
        this.ID = id;
        this.scheduled = scheduled;
        this.trainerID = trainerID;
        this.durationID = durationID;
        this.hallID = hallID;
        
    }
    public Session(){}
    
    public int getSessionID(){
        return ID;
    }
    
    public LocalDateTime getSessionSchedule(){
        return scheduled;
    }
    
    public int getTrainerID(){
        return trainerID;
    }
    
    public int getDurationID(){
        return durationID;
    }
    public int getHallID(){
        return hallID;
    }
    
    
    
    
    
}
