
package GymBookingSystem;


public class ExerciseType {
   private int ID;
   private String name;
   
    
    public ExerciseType(int id, String name){
        this.ID = id;
        this.name = name;
                    
    }
    
    public ExerciseType(){}
    
    public int getExerciseTypeID(){
        return ID;
    }
    
    public String getExerciseName(){
        return name;
    } 
    
}
