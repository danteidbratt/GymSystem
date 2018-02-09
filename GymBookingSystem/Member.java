
package GymBookingSystem;


public class Member {
    private int ID;
    private String name;
    
    public Member(int id, String name){
        this.ID = id;
        this.name = name;
                    
    }
    
    public Member(){}
    
    public int getMemberID(){
        return ID;
    }
    
    public String getMemberName(){
        return name;
    }
    
    
    
    
}
