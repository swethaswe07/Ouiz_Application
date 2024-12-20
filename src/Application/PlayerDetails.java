package Application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayerDetails {
    private static final Object Option_e = null;
	private Scanner sc = new Scanner(System.in);
    private int count = 0;
    private boolean isFiftyFiftyUsed = false;
    private boolean isAudiencePollUsed = false;
    private String name;
    private String location;
	private int age;
	private String email;
	private int Phn_num;
	
	
	
	//Collect player Details and store it in the object
    public void setName(String name) {
    	this.name=name;
    	}
    public String getName() {
    	return name;
    }	
    
    public void setAge(int age) {
    	this.age=age;
    	}
    public int getAge() {
    	return age;
    }	
    
    public void setEmail(String email) {
    	this.email=email;
    	}
    public String getEmail() {
    	return email;
    }	
    
    public void setPhn_num(int Phn_num) {
    	this.Phn_num=Phn_num;
    	}
    public int getPhn_num() {
    	return Phn_num;
    }	
    
    public void setLocation(String location) {
    	this.location=location;
    	}
    public String getLocation() {
    	return location;
    }
    public void details() {
		Scanner sc=new Scanner(System.in);
     Details d= new Details();
    System.out.println("Enter your name: ");
	d.setName(sc.next());
	System.out.println("Enter your Age: ");
	d.setAge(sc.nextInt());
	System.out.println("Enter your phone number: ");
	d.setPhn_num(sc.next());
	System.out.println("Enter your Email id: ");
	d.setEmail(sc.next());
	System.out.println("Enter your location: ");
	d.setLocation(sc.next());
}
    
   
 // Display rules
    public void rules() {
    System.out.println("*"); 	
    System.out.println("Welcome to the Quiz Game! Your goal is to answer all the questions correctly and score the highest points.");
    System.out.println("1. Each question has four options: A, B, C, and D. Choose the correct answer by typing the corresponding letter.");
    System.out.println("2. For every correct answer, you will earn 10 points. There are no points for incorrect answers.");
    System.out.println("3. You have two lifelines to assist you during the game:");
    System.out.println("   - The 50-50 Lifeline removes two incorrect options, leaving one correct and one incorrect option.");
    System.out.println("   - The Audience Poll Lifeline shows audience voting percentages for each option to help you decide.");
    System.out.println("4. Each lifeline can only be used once during the game.");
    System.out.println("5. If you answer incorrectly, the game ends immediately, and your final score will be displayed.");
    System.out.println("6. Try your best and enjoy the game! Good luck!");

    }
    
    public void askQuestion(String question, String[] options,String correctAnswer) {
    	
        System.out.println(question);
        ArrayList<String> remainingOptions = new ArrayList<>();
        for (String option : options) {
            if (option.equals(Option_e)) {
                useFiftyFifty(options, option);
            }
        for (int i = 0; i < options.length; i++) {
            System.out.println("Option " + (char) ('A'+ i) + ": " + options[i] );
          
           
        }
        

        System.out.println("Enter your answer or type '50-50' or 'Poll' to use a lifeline:");
        String answer = sc.nextLine();
        
         
    if (answer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct!");
            count += 10;
            System.out.println("You have scored " + count + " points.");
        } else {
            System.out.println("Incorrect! The correct answer was: " + correctAnswer);
            System.out.println("Game over! You are leaving with " + count + " points.");
            System.exit(0);
        }
        }
    }
     public void useFiftyFifty(String[] options, String correctAnswer) {
        if (isFiftyFiftyUsed) {
            System.out.println("You have already used the 50-50 lifeline.");
            return;
        }

        Random random = new Random();
        int correctIndex = -1;
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(correctAnswer)) {
                correctIndex = i;
                break;
            }
        }

        boolean[] toShow = new boolean[options.length];
        toShow[correctIndex] = true;
        int removed = 0;

        while (removed < 2) {
            int randomIndex = random.nextInt(options.length);
            if (randomIndex != correctIndex && !toShow[randomIndex]) {
                toShow[randomIndex] = true;
                removed++;
            }
        }

        System.out.println("50-50 Lifeline Used. Remaining options:");
        for (int i = 0; i < options.length; i++) {
            if (toShow[i]) {
                System.out.println("Option " + (char) ('A' + i) + ": " + options[i]);
            }
        }

        isFiftyFiftyUsed = true;
    }
   
    public void useAudiencePoll(String[] options, String correctAnswer) {
        if (isAudiencePollUsed) {
            System.out.println("You have already used the Audience Poll lifeline.");
            return;
        }

        Random random = new Random();
        int correctIndex = -1;
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(correctAnswer)) {
                correctIndex = i;
                break;
            }
        }

        int[] poll = new int[options.length];
        int remaining = 100;
        for (int i = 0; i < poll.length; i++) {
            if (i == correctIndex) {
                poll[i] = 50 + random.nextInt(21); // 50-70% for the correct answer
            } else {
                poll[i] = random.nextInt(remaining / 2);
            }
            remaining -= poll[i];
        }

        poll[correctIndex] += remaining; // Ensure percentages add to 100

        System.out.println("Audience Poll Results:");
        for (int i = 0; i < options.length; i++) {
            System.out.println("Option " + (char) ('A' + i) + ": " + poll[i] + "%");
        }

        isAudiencePollUsed = true;
    }
    
  
//Questions
    public void playGame() {
    	askQuestion("Which brand is known for its iconic swoosh logo?", new String[]{"Nike", "Adidas", "Puma", "Reebok"}, "Nike");
    	
    	askQuestion("Which company created the first smartphone?", new String[]{"Apple", "Nokia", "Motorola", "Samsung"}, "Motorola");
    	askQuestion("What does the 'M' in McDonald's logo stand for?", new String[]{"Melted", "Meals", "Mass", "Golden Arches"}, "Golden Arches");
    	askQuestion("Which brand is known for the slogan 'Think Different'?", new String[]{"Apple", "Microsoft", "IBM", "Google"}, "Apple");
    	askQuestion("What color is the 'Coca-Cola' logo?", new String[]{"Red", "Blue", "Green", "Yellow"}, "Red");
    	askQuestion("Which company owns the popular music streaming service Spotify?", new String[]{"Spotify AB", "Apple", "Google", "Amazon"}, "Spotify AB");
    	askQuestion("Which luxury brand is famous for its red-bottomed shoes?", new String[]{"Christian Louboutin", "Gucci", "Prada", "Louis Vuitton"}, "Christian Louboutin");
    	askQuestion("Which fast food brand is famous for its Big Mac?", new String[]{"McDonald's", "Burger King", "Wendy's", "KFC"}, "McDonald's");
    	askQuestion("Which technology company created the Android operating system?", new String[]{"Google", "Apple", "Microsoft", "Amazon"}, "Google");
    	askQuestion("Which brand is famous for its yellow taxi cabs?", new String[]{"Taxi NYC", "Yellow Cabs", "Lyft", "Uber"}, "Taxi NYC");
       
    }
}