package Application;
import java.util.Scanner;

public class Main1 {
	public static void main(String args[]){
		System.out.println("Welcome to the Game!");
		System.out.println("****************");
		 PlayerDetails quiz = new PlayerDetails();
	      quiz.details();
System.out.println("Are you ready for the game ? ");
Scanner sc=new Scanner(System.in);
String str=sc.next();
if (str.equals("No")){
	System.exit(0);
	
}
else {
 
  
      PlayerDetails quiz1 = new PlayerDetails();
     
     quiz1.rules();
     quiz1.playGame();
  
}
}
}