package bullscows;

import java.util.Random;
import java.util.Scanner;

public class GameInterface {

    private final int[] secretNum = {0, 0, 0, 0};
    private final int[] userNum = {0, 0, 0, 0};

    public GameInterface() {

    }

    protected void start() {
        numberGenerator();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        placeUserNumInArray(input);
        Grader grader = new Grader();
        grader.grade(secretNum, userNum);
        System.out.println(grader.getGrade() + " The secret code is " + secretNum[0] +
                secretNum[1] + secretNum[2] + secretNum[3]);

        // This portion of the exercise doesn't use this, but we will need it later
        /* System.out.println("The secret code is prepared: ****.");
        System.out.println();
        System.out.println("Turn 1. Answer:");
        System.out.println("1234");
        System.out.println("Grade: 1 cow.");
        System.out.println();
        System.out.println("Turn 2. Answer:");
        System.out.println("5678");
        System.out.println("Grade: 1 cow.");
        System.out.println();
        System.out.println("Turn 3. Answer:");
        System.out.println("9305");
        System.out.println("Grade: 4 bulls.");
        System.out.println("Congrats! The secret code is 9305."); */

    }

    // Method generates a random 4-digit number and passes it into secretNum array
    private void numberGenerator() {
        Random randomInt = new Random();
        for (int i = 0; i <= 3; i++) {
            secretNum[i] = randomInt.nextInt(10);
        }
    }

    // Method places user specified 4-digit number into an array
    private void placeUserNumInArray(String input) {
        for (int i = 0; i <= 3; i++) {
            userNum[i] = input.charAt(i) - '0';
        }
    }
}