package bullscows;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameInterface {

    private  String[] secretNum = {};
    private String[] userNum = {};
    private int turn = 1;

    public GameInterface() {

    }

    // Method that manages main game interface thread
    public void start() {

        // FIXME: Refactor to match example more closely.
        // TODO: 1. If number of symbols is <= 10 then all ints
        // TODO: 2. If number of symbols is > 10 then ints and chars



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

        System.out.println("Code: " + Arrays.toString(secretNum));

        // FIXME: incorrect statement of available characters
        System.out.println("The secret is prepared: " + printStars(length) +
                " (0-9, a-z)");
        System.out.println("Okay, let's start a game!");



        // TODO
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

    // Method places user specified 4-digit number into an array
    private void placeUserNumInArray(String input, int length) {
        userNum = new String[length];

        for (int i = 0; i <= length - 1; i++) {
            userNum[i] = String.valueOf(input.charAt(i) - '0');
        }
    }

    // Method for task 3 to generate a variable-digit code composed of unique integers and characters
    private void randomCodeGenerator(int digits, int numOfChars) {
        // initializes new secretNum array for custom game size
        secretNum = new String[digits];
        Random random = new Random();
        ArrayList<String> randomSymbolsList = new ArrayList<>();

        // Prepare randomCharacterList with integers
        if (digits <= 10) {
            while (randomSymbolsList.size() < digits) {
                int randomInt = random.nextInt(10);
                if (!randomSymbolsList.contains(String.valueOf(randomInt))) {
                    randomSymbolsList.add(String.valueOf(randomInt));
                }
            }
        // Prepare randomCharacterList with integers and characters
        } else {
            ArrayList<String> usableCharList = new ArrayList<>();
            // add all possible chars to above list
            for (int i = 0; i < digits - 11; i++) {
                char nextChar = (char) ('a' + i);
                usableCharList.add(String.valueOf(nextChar));
            }
            while (randomSymbolsList.size() < digits) {
                int randomInt = random.nextInt(10);
                if (!randomSymbolsList.contains(String.valueOf(randomInt))) {
                    randomSymbolsList.add(String.valueOf(randomInt));
                }
            }
            randomSymbolsList.addAll(usableCharList);
        }

        // TODO: Create secret code from ints and characters on randomCharacterList
        ArrayList<String> randomCharacterList = new ArrayList<>();
        while (randomCharacterList.size() <= digits) {
            int randomElement = random.nextInt(digits);
            if (!randomCharacterList.contains(randomSymbolsList.get(randomElement))) {
                randomCharacterList.add(randomSymbolsList.get(randomElement));
            }
        }

        // TODO: Add elements in randomCharacterList to secretNum[]
        for (int i = 0; i <= digits - 1; i++) {
            secretNum[i] = randomCharacterList.get(i);
        }
    }

    private String printStars(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= length - 1; i++) {
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }
}
