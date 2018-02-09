
package GymBookingSystem;

import java.util.Scanner;
import javax.swing.JOptionPane;


public class GymBookingSystem {
 Repository r = new Repository();
 Trainer myAccount;
 Scanner scanner;
    int individualsessionID =0; String comment = "", snr = "", wholeline = ""; String[] snrAndName;
 GymBookingSystem(){
     scanner = new Scanner(System.in);

      
    }
 
 public void start(){
     System.out.println("Please type your name?");
     login(scanner.nextLine());
     boolean again = true;
     while(again){
     System.out.println("Choose an activity");
     System.out.println("Get a Members Group training  [1]" + 
             "\nGetMembersNotes  [2]" + 
             "\nSee a member's Individuals training  [3]" + 
             "\nSee all members' trainings  [4]" +
             "\nWrite notes of a member  [5]");
     String choice = scanner.nextLine();
     switch(choice){
         case "1":
             System.out.println("Name of member: ");
             String name = scanner.nextLine().trim();
             r.getMembersTraining(name);
             break;
         case "2":
             System.out.println("Name of member: ");
             name = scanner.nextLine().trim();
             r.getMembersNotes(name);
             break;
         case "3": 
             System.out.println("Name of member: ");
             name = scanner.nextLine().trim();
             r.getIndividualsTraining(name);
             break;
         case "4":
             r.getAllMembersTraining();
             break;
         case "5": //INCOMPLETE
//             System.out.println("Name of member: ");
//             name = scanner.nextLine().trim();
//            individualsessionID = r.checkIfMemberHasIndividualTraining(name);
//
//        if(individualsessionID >0 ){ 
//            int id = r.checkIfNoteExists(individualsessionID);
//          
//               System.out.println("Write your comment here: ");
//                 comment = scanner.nextLine();
//                r.writeMembersNotes(comment, id);
//                 System.out.println("Comment successfully entered into note");}
//           else 
//            System.out.println("Member has no individual trainings");
             break;
         default:
             again = false;
             break;
             
             }
     
     }
 }
 
 public void login(String trainerName){
     myAccount = r.logIn(trainerName);
 }
    
    
    public static void main(String[] args) throws NullPointerException {
        GymBookingSystem best = new GymBookingSystem();
       best.start();
    }
    
}
