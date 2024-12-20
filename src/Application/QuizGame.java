package Application;
import java.util.Scanner;
import java.util.Random;

// Player class to encapsulate player details
class Player {
    private String name;
    private int age;

    // Constructor to initialize player details
    public Player(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for age
    public void setAge(int age) {
        this.age = age;
    }
}

public class QuizGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the Brand Quiz Game!");
        System.out.println("Before we begin, let's get to know you a bit.");

        // Ask for player details with exception handling
        String playerName = "";
        int playerAge = 0;
        boolean validInput = false;

        // Get player name
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();

        // Get player age with exception handling
        while (!validInput) {
            try {
                System.out.print("Enter your age: ");
                playerAge = Integer.parseInt(scanner.nextLine()); // Convert age to integer
                if (playerAge <= 0) {
                    throw new NumberFormatException("Age must be a positive number.");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid positive number for age.");
            }
        }

        // Create player object using encapsulation
        Player player = new Player(playerName, playerAge);

        // Display player details
        System.out.println("Welcome, " + player.getName() + "!");
        System.out.println("Your age: " + player.getAge());

        // Ask if player is ready to start the game with exception handling
        int startGame = -1;
        while (startGame != 0 && startGame != 1) {
            try {
                System.out.println("Are you ready to start the game?");
                System.out.print("Enter 1 to start or 0 to quit: ");
                startGame = Integer.parseInt(scanner.nextLine()); // Convert input to integer
                if (startGame != 0 && startGame != 1) {
                    throw new IllegalArgumentException("Invalid choice. Please enter 1 to start or 0 to quit.");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        if (startGame == 0) {
            System.out.println("Game terminated. Goodbye, " + player.getName() + "!");
            return; // Exit the game if player chooses 0
        }

        // Proceed to the quiz if player is ready
        startQuiz(scanner, player);

        scanner.close(); // Close the scanner resource
    }

    // Method to start the quiz game
    public static void startQuiz(Scanner scanner, Player player) {
        String[] questions = {
            "Which company is known for its iconic product 'iPhone'?",
            "Which brand's logo is a 'swoosh' symbol?",
            "What is the main product of Tesla?",
            "Which company owns the popular soft drink 'Coca-Cola'?",
            "Which brand is known for its search engine and the company logo featuring a colorful wordmark?",
            "Which automobile brand produces the luxury model 'A8'?",
            "What company is known for its social media platform called 'Instagram'?",
            "Which company is the largest producer of smartphones in the world by volume?",
            "Which brand is known for the slogan 'Just Do It'?",
            "Which brand is the parent company of 'Subway'?"
        };

        String[][] options = {
            {"Apple", "Samsung", "Microsoft", "Sony"},
            {"Adidas", "Nike", "Puma", "Reebok"},
            {"Laptops", "Electric Cars", "Smartphones", "Airplanes"},
            {"PepsiCo", "Coca-Cola Company", "Dr Pepper Snapple Group", "Nestle"},
            {"Microsoft", "Google", "Yahoo!", "Bing"},
            {"BMW", "Audi", "Mercedes-Benz", "Lexus"},
            {"Facebook", "Google", "Twitter", "Microsoft"},
            {"Apple", "Xiaomi", "Samsung", "Huawei"},
            {"Reebok", "Nike", "Puma", "Adidas"},
            {"McDonald's", "Yum! Brands", "Restaurant Brands International", "Doctor's Associates Inc."}
        };

        String[] correctAnswers = {
            "A", "B", "B", "B", "B", "B", "A", "C", "B", "D"
        };

        int score = 0;
        boolean lifeline1Used = false;
        boolean lifeline2Used = false;

        // Start the game
        for (int i = 0; i < questions.length; i++) {
            boolean questionAnswered = false; // Flag to check if the question has been answered

            while (!questionAnswered) {
                System.out.println(questions[i]);
                System.out.println("Option A: " + options[i][0]);
                System.out.println("Option B: " + options[i][1]);
                System.out.println("Option C: " + options[i][2]);
                System.out.println("Option D: " + options[i][3]);
                System.out.println("Option E: Lifeline");
                System.out.println("Option F: Quit the game");

                String userChoice = "";
                boolean validChoice = false;

                // Handle user input with exception handling
                while (!validChoice) {
                    try {
                        System.out.print("Enter your choice: ");
                        userChoice = scanner.nextLine().toUpperCase();
                        if (!(userChoice.equals("A") || userChoice.equals("B") || userChoice.equals("C") ||
                              userChoice.equals("D") || userChoice.equals("E") || userChoice.equals("F"))) {
                            throw new IllegalArgumentException("Invalid choice. Please choose between A, B, C, D, E, or F.");
                        }
                        validChoice = true; // Valid choice made, exit loop
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (userChoice.equals("F")) {
                    System.out.println("You chose to quit the game.");
                    break;
                } else if (userChoice.equals("E")) {
                    // Lifeline options
                    if (!lifeline1Used || !lifeline2Used) {
                        System.out.println("Lifeline 1: 50-50");
                        System.out.println("Lifeline 2: Poll");
                        System.out.print("Choose a lifeline (1 or 2): ");
                        int lifelineChoice = -1;
                        while (lifelineChoice != 1 && lifelineChoice != 2) {
                            try {
                                lifelineChoice = Integer.parseInt(scanner.nextLine());
                                if (lifelineChoice != 1 && lifelineChoice != 2) {
                                    throw new IllegalArgumentException("Invalid lifeline choice. Please enter 1 or 2.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please choose 1 or 2 for lifelines.");
                            }
                        }

                        if (lifelineChoice == 1) {
                            if (lifeline1Used) {
                                System.out.println("You have already used the 50-50 lifeline.");
                                continue; // Repeat the same question
                            } else {
                                lifeline1Used = true;
                                // 50-50 lifeline logic
                                System.out.println("50-50 Lifeline used: Let's remove two wrong answers.");
                                Random rand = new Random();
                                int correctOptionIndex = getCorrectOptionIndex(correctAnswers[i]);

                                int wrongOptionIndex = rand.nextInt(4);
                                while (wrongOptionIndex == correctOptionIndex) {
                                    wrongOptionIndex = rand.nextInt(4);
                                }

                                System.out.println("Remaining options after 50-50:");
                                System.out.println("Option " + (char)('A' + correctOptionIndex) + ": " + options[i][correctOptionIndex]);
                                System.out.println("Option " + (char)('A' + wrongOptionIndex) + ": " + options[i][wrongOptionIndex]);

                                System.out.print("Choose your option (A, B, C, or D): ");
                                userChoice = scanner.nextLine().toUpperCase();
                            }
                        } else if (lifelineChoice == 2) {
                            if (lifeline2Used) {
                                System.out.println("You have already used the Poll lifeline.");
                                continue; // Repeat the same question
                            } else {
                                lifeline2Used = true;
                                // Poll lifeline logic
                                System.out.println("Poll Lifeline used: Let's see what the audience thinks!");
                                userChoice = simulatePoll(scanner, options[i], correctAnswers[i]);
                            }
                        }
                    } else {
                        System.out.println("You have already used both lifelines.");
                    }
                }

                if (userChoice.equals(correctAnswers[i])) {
                    score += 100;
                    System.out.println("Correct Answer! Your current score is: " + score);
                    questionAnswered = true; // Set flag to true once the question is answered correctly
                } else if (!userChoice.equals("F")) {
                    System.out.println("Incorrect Answer. Game Over!");
                    System.out.println("Your final score is: " + score);
                    System.exit(0); // Terminate the game immediately on wrong answer
                }

                System.out.println(); // Blank line for separation
            }

            if (questionAnswered) {
                continue; // Proceed to next question if the current one is answered
            } else {
                break; // Exit the quiz if the player quits
            }
        }
    }

    private static int getCorrectOptionIndex(String correctAnswer) {
        switch (correctAnswer) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            default:
                return -1; // Default case for invalid input
        }
    }

    // Simulate the Audience Poll with a higher percentage for the correct answer
    private static String simulatePoll(Scanner scanner, String[] options, String correctAnswer) {
        Random rand = new Random();
        String simulatedAnswer = "";
        int correctIndex = getCorrectOptionIndex(correctAnswer);
        int[] pollResults = new int[4];

        // Simulate polling results with the highest percentage for the correct answer
        pollResults[correctIndex] = 50 + rand.nextInt(31); // Correct answer gets 50-80%
        int remainingPercentage = 100 - pollResults[correctIndex];

        // Distribute the remaining percentage randomly among the incorrect answers
        for (int i = 0; i < 4; i++) {
            if (i != correctIndex) {
                pollResults[i] = remainingPercentage / 3 + rand.nextInt(11); // Random share of remaining percentage
            }
        }

        // Print poll results
        System.out.println("Audience Poll results:");
        for (int i = 0; i < 4; i++) {
            System.out.println(options[i] + ": " + pollResults[i] + "%");
        }

        // Simulate the player's choice based on the poll results
        int maxPoll = -1;
        for (int i = 0; i < 4; i++) {
            if (pollResults[i] > maxPoll) {
                maxPoll = pollResults[i];
                simulatedAnswer = String.valueOf((char) ('A' + i));
            }
        }

        return simulatedAnswer;
    }
}
