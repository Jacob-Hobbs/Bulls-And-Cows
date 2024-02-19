package bullscows;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameInterface {

    private  int[] secretNum = {};
    private int[] userNum = {};
    private int turn = 1;

    public GameInterface() {

    }

    // Method that manages main game interface thread
    public void start() {
        int length;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please, enter the secret code's length:");
            length = Integer.valueOf(scanner.nextLine());
            if (length > 10) {
                System.out.println("Error! Invalid secret code length");
            } else {
                break;
            }
        }

        randomNumberGenerator(length);
        System.out.println("Okay, let's start a game!");

        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.nextLine();
            placeUserNumInArray(guess, length);
            Grader grader = new Grader();
            grader.grade(secretNum, userNum);
            if (grader.getBulls() == length) {
                System.out.println(grader.getGrade());
                break;
            } else {
                System.out.println(grader.getGrade());
                turn++;
            }
        }

        System.out.println("Congratulations! You guessed the secret code.");
        scanner.close();
    }

    // Method generates a random 4-digit number and passes it into secretNum array
    private void numberGenerator() {
        Random randomInt = new Random();
        for (int i = 0; i <= 3; i++) {
            secretNum[i] = randomInt.nextInt(10);
        }
    }

    // Method places user specified 4-digit number into an array
    private void placeUserNumInArray(String input, int length) {
        userNum = new int[length];

        for (int i = 0; i <= length - 1; i++) {
            userNum[i] = input.charAt(i) - '0';
        }
    }

    // Method for task 3 to generate a variable-digit code composed of unique integers
    private void randomNumberGenerator(int digits) {
        // initializes new secretNum array for custom game size
        secretNum = new int[digits];
        // arraylist where custom secret code is stored after creation temporarily
        ArrayList<Integer> intArrayList = new ArrayList<>();
        Random randomInt = new Random();

        // adds a unique number to intArrayList for secret code generation
        for (int i = 0; i <= digits - 1; i++) {
            while (true) {
                int nextInt = randomInt.nextInt(10);
                if (!intArrayList.contains(nextInt)) {
                    intArrayList.add(nextInt);
                    break;
                }
            }
        }

        // adds secret code stored in intArrayList into secretNum array
        for (int i = 0; i <= digits - 1; i++) {
            secretNum[i] = intArrayList.get(i);
        }
    }
}
