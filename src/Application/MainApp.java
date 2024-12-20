package Application;
import java.util.Scanner;

public class MainApp {
	public static void main(String args[]){
System.out.println("Are you ready for the game ? ");
Scanner sc=new Scanner(System.in);
String str=sc.next();
if (str.equals("No")){
	System.exit(0);
	
}
else {
 
  
      App quiz = new App();
      quiz.details();
     quiz.rules();
      quiz.playGame();
  
}
}
}