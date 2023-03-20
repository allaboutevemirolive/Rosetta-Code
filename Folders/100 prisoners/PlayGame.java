import java.util.function.Function;


// This class plays a game that if the secret number is equal to the myNumber, the player wins the game.

public class PlayGame {

    public static void main(String[] args) {
        // Define a function that plays a game and returns a boolean indicating success.
        // The function takes an integer as an argument and returns a boolean.
        // Integer is the probability of winning the game.
        // Boolean is the result of the game.
        Function<Integer, Boolean> play = p -> {

            // Generate a random number between 1 and 10 (inclusive) using the Math.random() method.

            // This method returns a random double value between 0.0 and 1.0 (exclusive), 
            // so we multiply it by 10 to get a value between 0.0 and 10.0 (exclusive), and then 
            // cast it to an integer to get a value between 1 and 10 (inclusive).

            // Plus 1 is added to the result to get a value between 1 and 10 (inclusive). 
            // If not the result would be between 0 and 9 (inclusive).
            int secretnumber = (int) (Math.random() * 10) + 1;

            System.out.println("The secret number is: " + secretnumber);

            // Check if the secret number is equal to myNumber.
            return secretnumber == p;
        };

        // My number.
        // Check if my number is less than or equal
        int myNumber = 10; 

        System.out.println("My number is: " + myNumber + "");
        // apply the function to the myNumber
        // play takes an integer as an argument and returns a boolean
        boolean result = play.apply(myNumber);
        if (result) {
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("Sorry, you lose.");
        }
    }
}
