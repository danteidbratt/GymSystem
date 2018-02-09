package GymBookingSystem;


public class Trainer {
    private int ID;
    private String name;
    
    public int getTrainerID(){
        return ID;
    }
    
    public String getTrainerName(){
        return name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
