package bullscows;

import java.util.ArrayList;
import java.util.Arrays;

public class Grader {

    private int bulls = 0;
    private int cows = 0;

    // Grader default constructor
    public Grader() {

    }

    // Method to calculate grade of user's guess
    public void grade(String[] secretNum, String[] userNum) {
        ArrayList<String> consideredList = new ArrayList<>();
        for (int i = 0; i <= userNum.length - 1; i++) {
            if (!consideredList.contains(userNum[i])) {
                if (userNum[i].equals(secretNum[i])) {
                    bulls++;
                    consideredList.add(userNum[i]);
                } else {
                    for (int j = 0; j <= userNum.length - 1; j++) {
                        if (!consideredList.contains(secretNum[i])) {
                            if (userNum[i].equals(secretNum[j])) {
                                cows++;
                                consideredList.add(secretNum[i]);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    // Getter for bulls
    public int getBulls() {
        return this.bulls;
    }

    // Setter for bulls
    public void setBulls(int num) {
        this.bulls = num;
    }

    // Getter for cows
    public int getCows() {
        return this.cows;
    }

    // Setter for cows
    public void setCows(int num) {
        this.cows = num;
    }

    // Method to retrieve grade in the form of a String object
    public String getGrade() {
        if (bulls == 0 && cows == 0) {
            return "Grade: None.";
        } else if (bulls > 0 && cows > 0) {
            return "Grade: " + bulls + " bull(s) + and " + cows + " cow(s).";
        } else if (bulls > 0) {
            return "Grade: " + bulls + " bull(s).";
        } else {
            return "Grade: " + cows + " cow(s).";
        }
    }
}
