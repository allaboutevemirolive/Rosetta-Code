import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayRandom {
    public static void main(String[] args) {
        int n = 20;
        boolean result = playRandom(n);
        System.out.println("Result: " + result);
    }

    private static boolean playRandom(int n) {

        // List of prisoners with their secret number in random order.
        List<Integer> secretList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Collections.shuffle(secretList);

        // For each prisoner, we check if the prisoner can find their secret number.
        prisoner: for (Integer i : secretList) {
            // i is the prisoner's number in 'random' order.
            // trialList is the list of 'boxes' containing the 'secret number' in random order.
            List<Integer> trialList = IntStream.range(0, n).boxed().collect(Collectors.toList());
            Collections.shuffle(trialList);

            // Each of the prisoners can only choose half of the boxes containing the secret number.
            for (int j = 0; j < trialList.size() / 2; ++j) {
                // j is the number of boxes
                // If the secret number in the box(j) is the same as the prisoner's number
                if (Objects.equals(trialList.get(j), i)) {
                    // We continue to the next prisoner to search for their secret number.
                    continue prisoner;
                }
            }
            // If the prisoner did not find the secret number, he will be executed and all the other prisoners will be executed as well.
            return false;
        }
        // If all the prisoners found their secret number, they will not be executed.
        return true;
    }
}