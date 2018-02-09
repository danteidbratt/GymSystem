/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GymBookingSystem;


public class Hall {
   private int ID;
   private String name;
    
    public Hall(int id, String name ){
        this.ID = id;
        this.name =name;
                    
    }
    
    public Hall(){}
    
    public int getHallID(){
        return ID;
    }
    
    public String getHallName(){
        return name;
    }  
}
