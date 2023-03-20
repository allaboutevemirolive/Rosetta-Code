import java.util.function.Function;

public class SuccessRate {

    public static void main(String[] args) {
        // Define a function that plays a game and returns a boolean indicating success.
        Function<Integer, Boolean> play = p -> {
            // Generate a random number between 1 and 10.
            // The probability of the random number being less than or equal to p is p/10.
            int randomNum = (int) (Math.random() * 10) + 1;
            // Return true if the random number is less than or equal to p.
            return randomNum <= p;
        };

        // Call the exec method with n=100 and p=5.
        // n is the number of executions.
        // p is the probability of success.
        // play is the function that plays the game.
        double successRate = exec(100, 5, play);

        // Print the success rate.
        System.out.println("Success rate: " + successRate + "%");
    }

    // This method executes the play function n times.
    private static double exec(int n, int p, Function<Integer, Boolean> play) {
        // Count the number of successful plays.
        int succ = 0;
        // Execute the play function n times.
        for (int i = 0; i < n; ++i) {
            // If the play is successful, increment the counter.
            // If play.apply(p) returns true and same as Boolean.TRUE, then increment the counter.
            if (Boolean.TRUE.equals(play.apply(p))) {
                succ++;
            }
            // Otherwise, jump to 
        }
        // Return the success rate.
        return (succ * 100.0) / n;
    }

}
