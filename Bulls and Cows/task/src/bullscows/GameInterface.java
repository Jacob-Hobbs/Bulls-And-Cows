package bullscows;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameInterface {

    private  String[] secretNum = {};
    private String[] userNum = {};
    private int turn = 1;

    // GameInterface default constructor
    public GameInterface() {

    }

    // Method that manages main game interface thread
    public void start() {
        int length;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Input the length of the secret code:");
            length = Integer.valueOf(scanner.nextLine());
            if (length > 36) {
                System.out.println("Error! Invalid secret code length");
            } else {
                break;
            }
        }

        int numOfSymbols;
        while (true) {
            System.out.println("Input the number of possible symbols in the code:");
            numOfSymbols = Integer.valueOf(scanner.nextLine());
            if (numOfSymbols > 36) {
                System.out.println("Error! You can't have more symbols that the max length" +
                        " (36) of the secret code!");
            } else if (numOfSymbols < length) {
                System.out.println("Error! You can't have less symbols than the length ("
                        + length + ") of the secret code!");
            } else {
                break;
            }
        }

        randomCodeGenerator(length, numOfSymbols);
        System.out.println("The secret is prepared: " + printStars(length) +
                " " + printGuessRange(numOfSymbols));
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
                grader.setBulls(0);
                grader.setCows(0);
            }
        }

        System.out.println("Congratulations! You guessed the secret code.");
        scanner.close();
    }

    // Method places user specified 4-digit number into an array
    private void placeUserNumInArray(String input, int length) {
        userNum = new String[length];
        for (int i = 0; i <= length - 1; i++) {
            userNum[i] = String.valueOf(input.charAt(i));
        }
    }

    // Method for task 3 to generate a variable-digit code composed of unique integers and characters
    private void randomCodeGenerator(int digits, int numOfChars) {
        // initializes new secretNum array for custom game size
        secretNum = new String[digits];
        Random random = new Random();
        ArrayList<String> randomSymbolsList = new ArrayList<>();
        ArrayList<String> randomCharacterList = new ArrayList<>();

        // Prepare randomCharacterList with integers
        if (numOfChars <= 10) {
            while (randomSymbolsList.size() < digits) {
                int randomInt = random.nextInt(10);
                if (!randomSymbolsList.contains(String.valueOf(randomInt))) {
                    randomSymbolsList.add(String.valueOf(randomInt));
                }
            }
            randomCharacterList.addAll(randomSymbolsList);
        // Prepare randomCharacterList with integers and characters
        } else {
            ArrayList<String> usableCharList = new ArrayList<>();
            // add all possible chars to above list
            for (int i = 0; i < numOfChars - 10; i++) {
                char nextChar = (char) ('a' + i);
                usableCharList.add(String.valueOf(nextChar));
            }
            for (int i = 0; i < 10; i++) {
                randomSymbolsList.add(String.valueOf(i));
            }
            randomSymbolsList.addAll(usableCharList);

            // Randomly select elements to place in secret code from randomSymbolList
            while (randomCharacterList.size() < digits) {
                int randomElement = random.nextInt(numOfChars - 1);
                if (!randomCharacterList.contains(randomSymbolsList.get(randomElement))) {
                    randomCharacterList.add(randomSymbolsList.get(randomElement));
                }
            }
        }

        // Add random code to secretNum[]
        for (int i = 0; i <= digits - 1; i++) {
            secretNum[i] = randomCharacterList.get(i);
        }
    }

    // Method to print number of * for every digit in code
    private String printStars(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= length - 1; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }

    // Method to print the guess range for the user
    private String printGuessRange(int numOfSymbols) {
        if (numOfSymbols < 10) {
            return "(0-" + (numOfSymbols - 1) + ").";
        } else if (numOfSymbols == 10) {
            return "(0-9).";
        } else if (numOfSymbols == 11) {
            return "(0-9, a).";
        } else {
            int maxChar = numOfSymbols - 10;
            char mChar = (char)('a' + (maxChar - 1));
            return "(0-9, a-" + mChar + ").";
        }
    }
}
