package bullscows;

public class Grader {

    private int bulls = 0;
    private int cows = 0;

    public Grader() {

    }

    // FIXME: If the user enters a 2 and the code is 0220, the one 2 can only count for 1 cow!
    public void grade(int[] secretNum, int[] userNum) {
        for (int i = 0; i <= 3; i++) {
            if (userNum[i] == secretNum[i]) {
                bulls++;
            } else {
                for (int j = 0; j <= 3; j++) {
                    if (userNum[i] == secretNum[j]) {
                        cows++;
                    }
                }
            }
        }
    }

    public int getBulls() {
        return this.bulls;
    }

    public int getCows() {
        return this.cows;
    }

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
