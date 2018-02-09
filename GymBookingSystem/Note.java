package GymBookingSystem;


public class Note {
    private int ID;
    private String comment;
    private int memberID;
    private int trainerID;
    
    public Note(int id, String comment, int trainerID, int memberID){
        this.ID = id;
        this.memberID = memberID;
        this.trainerID = trainerID;
        this.comment = comment;
              
    }
    
    public Note(){}
    
    public int getNoteID(){
        return ID;
    }
    
    public String getNoteComment(){
        return comment;
    }
    
    public int getTrainerID(){
        return trainerID;
    }
    
    public int getMemberID(){
        return memberID;
    }
    
    
}
