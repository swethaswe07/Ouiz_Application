package Application;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class QuizGame {
    // Variables to track score and lifeline usage
    int score = 0;
    boolean fiftyFiftyUsed = false;
    boolean audiencePollUsed = false;
    Scanner sc = new Scanner(System.in);
    
    
    

    // Class to store question data
    class Question {
        String question;
        String[] options;
        String correctAnswer;

        public Question(String question, String[] options, String correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    // Main game method
    public void startGame() {
        // Create a list of questions
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Rome"}, "Paris"));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, "4"));
        questions.add(new Question("Who wrote 'Hamlet'?", new String[]{"Shakespeare", "Tolstoy", "Homer", "Dickens"}, "Shakespeare"));
        questions.add(new Question("What is the largest planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, "Jupiter"));
        questions.add(new Question("What is the speed of light?", new String[]{"300,000 km/s", "150,000 km/s", "100,000 km/s", "50,000 km/s"}, "300,000 km/s"));
        questions.add(new Question("Who discovered gravity?", new String[]{"Newton", "Einstein", "Galileo", "Tesla"}, "Newton"));
        questions.add(new Question("Which is the longest river?", new String[]{"Nile", "Amazon", "Yangtze", "Mississippi"}, "Nile"));
        questions.add(new Question("Which country is known as the Land of the Rising Sun?", new String[]{"Japan", "China", "Korea", "Thailand"}, "Japan"));
        questions.add(new Question("What is the smallest prime number?", new String[]{"1", "2", "3", "5"}, "2"));
        questions.add(new Question("Which element has the chemical symbol 'O'?", new String[]{"Oxygen", "Osmium", "Gold", "Oxide"}, "Oxygen"));

        // Call the method to ask each question
        for (int i = 0; i < questions.size(); i++) {
            askQuestion(questions.get(i)); // Ask each question
        }

        // Final score after all questions
        System.out.println("\nCongratulations! You completed the quiz.");
        System.out.println("Your final score is: " + score);
    }

    // Method to ask each question
    public void askQuestion(Question q) {
        System.out.println("\n" + q.question);
        for (int i = 0; i < q.options.length; i++) {
            System.out.println("Option " + (char) ('A' + i) + ": " + q.options[i]);
        }

        // Prompt the user for their answer
        String answer = sc.nextLine().trim().toUpperCase();

        // Loop until a valid answer is given
        while (true) {
            // Handle 50-50 lifeline
            if (answer.equals("50-50")) {
                if (!fiftyFiftyUsed) {
                    fiftyFiftyUsed = true;
                    System.out.println("50-50 Lifeline Activated!");
                    printFiftyFifty(q.options, q.correctAnswer);
                    System.out.println("Enter your final answer (A/B/C/D):");
                    answer = sc.nextLine().trim().toUpperCase();
                    continue; // Re-validate the new answer
                } else {
                    System.out.println("You have already used the 50-50 lifeline. Enter your final answer (A/B/C/D):");
                    answer = sc.nextLine().trim().toUpperCase();
                    continue;
                }
            }

            // Handle Audience Poll
            if (answer.equals("Audience Poll")) {
                if (!audiencePollUsed) {
                    audiencePollUsed = true;
                    System.out.println("Audience Poll Activated!");
                    printAudiencePoll(q.options, q.correctAnswer);
                    System.out.println("Enter your final answer (A/B/C/D):");
                    answer = sc.nextLine().trim().toUpperCase();
                    continue; // Re-validate the new answer
                } else {
                    System.out.println("You have already used the Audience Poll lifeline. Enter your final answer (A/B/C/D):");
                    answer = sc.nextLine().trim().toUpperCase();
                    continue;
                }
            }

            // Check if the answer is valid (A, B, C, D)
            if (answer.length() == 1 && answer.charAt(0) >= 'A' && answer.charAt(0) <= 'D') {
                // Proceed with checking the answer
                break; // Exit the loop if a valid answer is given
            } else {
                // Invalid answer, ask the user to re-enter
                System.out.println("Invalid answer. Please choose a valid option (A/B/C/D) or use a lifeline.");
                answer = sc.nextLine().trim().toUpperCase();
            }
        }

        // Check if the answer is correct
        if (q.options[answer.charAt(0) - 'A'].equals(q.correctAnswer)) {
            System.out.println("Correct!");
            score += 10;
        } else {
            System.out.println("Incorrect!");
            System.out.println("Game over! You are leaving with " + score + " points.");
            System.exit(0); // End game if answer is wrong
        }
    }

    // 50-50 Lifeline Method
    public void printFiftyFifty(String[] options, String correctAnswer) {
    	  ArrayList<String> remainingOptions = new ArrayList<>();
        for (String option : options) {
            if (!option.equals(correctAnswer)) {
                remainingOptions.add(option);
            }
        }
        Collections.shuffle(remainingOptions);
        System.out.println("Option A: " + correctAnswer);
        System.out.println("Option B: " + remainingOptions.get(0)); // Show one incorrect option
    }

    // Audience Poll Method
    public void printAudiencePoll(String[] options, String correctAnswer) {
        // Simulate audience poll results (randomized for example)
        int correctPercentage = (int) (Math.random() * 40) + 40; // Correct answer gets between 40% to 80%
        int incorrectPercentage = (100 - correctPercentage) / (options.length - 1);

        System.out.println("Audience Poll Results:");
        for (String option : options) {
            if (option.equals(correctAnswer)) {
                System.out.println(option + ": " + correctPercentage + "%");
            } else {
                System.out.println(option + ": " + incorrectPercentage + "%");
            }
        }
    }

    // Main method to run the game
    public static void main(String[] args) {
        QuizGame game = new QuizGame();
        game.startGame();
    }
}
