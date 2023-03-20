import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// This class is checking if the optimal strategy works for n prisoners.
public class PlayOptimal {
    public static void main(String[] args) {
        // Change the number of prisoners here
        int n = 20;
        // playOptimal takes an integer as an argument and returns a boolean
        boolean result = playOptimal(n);
        System.out.println("Result: " + result);
    }

    private static boolean playOptimal(int n) {
        // range(0, n) returns a sequential ordered IntStream from 0 (inclusive) to n (exclusive) by an incremental step of 1.
        // boxed() returns a Stream consisting of the elements of this stream, each boxed to an Integer.
        // collect(Collectors.toList()) returns a Collector that accumulates the input elements into a new List.
        // In short, this line creates a list of integers from 0 to n - 1.
        List<Integer> secretList = IntStream.range(0,n).boxed().collect(Collectors.toList());

        // shuffle() randomly permutes the specified list using a default source of randomness.
        Collections.shuffle(secretList);

        // For each prisoner, we check if the prisoner can find their secret number.
        prisoner: for (int i = 0; i < secretList.size(); ++i) {
            // i is the prisoner's number in a sequential order.
            // prev is the `box number` that the prisoner is currently searching for the secret number.
            int prev = i;
            // Each of the prisoners can only choose half of the boxes containing the secret number.
            for (int j = 0; j < secretList.size() / 2; ++j) {
                // j is the number of boxes
                // If the secret number in the box(j) is the same as the prisoner's number
                if (secretList.get(prev) == i) {
                    // We continue to the next prisoner to search for their secret number.
                    continue prisoner;
                }
                // Otherwise, we move to the next box.
                prev = secretList.get(prev);
            }
            // If the prisoner did not find the secret number, he will be executed and all the other prisoners will be executed as well.
            return false;
        }
        // If all the prisoners found their secret number, they will not be executed.
        return true;
    }
}