package bullscows;

import java.sql.Array;
import java.util.ArrayList;
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

        // TODO: fix this method
        // TODO: 1. Generate int that represents number of ints
        // TODO: 2. Generate int that represents number of chars
        // TODO: 3. Use these numbers to add random ints and chars to list
        // TODO: 4. Randomly select digits number of symbols from list.
        // TODO: 5. Add these to array to create secret code.
        Random random = new Random();
        // This is the number of integers that can appear in code
        int numOfIntegers = random.nextInt(digits + 1);
        // This is the number of characters that can appear in code
        int numOfCharacters = digits - numOfIntegers;

        // TODO: Continue to step 3






        ArrayList<String> randomCharList = new ArrayList<>();

        while (randomCharList.size() < numOfChars) {
            Random random = new Random();
            char character = (char)(random.nextInt(26) + 'a');
            if (!randomCharList.contains(String.valueOf(character))) {
                randomCharList.add(String.valueOf(character));
            }
        }


        // arraylist where custom secret code is stored after creation temporarily
        //ArrayList<Integer> intArrayList = new ArrayList<>();
        Random randomInt = new Random();

        // adds a unique number to intArrayList for secret code generation
        for (int i = 0; i <= numOfIntegers - 1; i++) {
            while (true) {
                int nextInt = randomInt.nextInt(10);
                if (!randomCharList.contains(String.valueOf(nextInt))) {
                    randomCharList.add(String.valueOf(nextInt));
                    break;
                }
            }
        }

        // TODO Actually make secret code. Currently all chars and ints are stored in randomCharList
        // TODO 1. Generate random number corresponding to element in list.
        // TODO 2. If element at random number has not been used, place on secretNum array.
        ArrayList<String> secretCodeList = new ArrayList<>();
        for (int i = 0; i <= digits - 1; i++) {
            while (true) {
                int nextInt = randomInt.nextInt(digits);
                if (!secretCodeList.contains(randomCharList.get(nextInt))) {
                    secretCodeList.add(randomCharList.get(nextInt));
                    break;
                }
            }
        }


        // adds secret code stored in intArrayList into secretNum array
        for (int i = 0; i <= digits - 1; i++) {
            secretNum[i] = secretCodeList.get(i);
        }
    }

    private String printStars(int length) {
        String output = "";
        for (int i = 0; i <= length - 1; i++) {
            output.concat("*");
        }
        return output;
    }
}
