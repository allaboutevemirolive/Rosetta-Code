import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HundredPrisoners {

    public static void main(String[] args) {
        // The number of executions.
        final int n = 100_000;
        // The number of prisoners.
        final int p = 100;
        // Print the number of executions.
        System.out.printf("Number of executions: %d%n", n);
        long start = System.currentTimeMillis();
        // Print the success rate of the optimal play and the random play.
        System.out.printf("Optimal play success rate: %f%%%n",
                exec(
                        n, p, HundredPrisoners::playOptimal));
        System.out.printf("Random play success rate: %f%%%n",
                exec(
                        n, p, HundredPrisoners::playRandom));
        long end = System.currentTimeMillis();
        System.out.printf("Elapsed time: %d ms%n", end - start);
    }

    // Optimal play:
    // The prisoner who is in cell 1 will always choose cell 2,
    // the prisoner in cell 2 will always choose cell 3, and so on.
    // The prisoner in cell 100 will always choose cell 1.
    private static boolean playOptimal(int n) {
        // Create a list of integers from 0 to n-1, and shuffle it.
        List<Integer> secretList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Collections.shuffle(secretList);

        // For each prisoner, check if the door to the cell he chose is opened.
        prisoner: for (int i = 0; i < secretList.size(); ++i) {
            int prev = i;
            // The prisoner will check the door to the cell he chose, and the door to the
            // cell that the cell he chose points to, and so on.
            for (int j = 0; j < secretList.size() / 2; ++j) {
                // If the door to the cell he chose is opened, he will continue to the next prisoner.
                if (secretList.get(prev) == i) {
                    continue prisoner;
                }
                // Otherwise, he will check the door to the cell that the cell he chose points to.
                prev = secretList.get(prev);
            }
            // If the door to the cell he chose is not opened, he will continue to the next prisoner.
            return false;
        }
        // If all the doors are opened, the play is successful.
        return true;
    }

    // Random play: Each prisoner chooses a cell at random, and the door to that cell is opened.
    private static boolean playRandom(int n) {
        // Create a list of integers from 0 to n-1, and shuffle it.
        List<Integer> secretList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        // Shuffle the list.
        Collections.shuffle(secretList);

        // For each prisoner, check if the door to the cell he chose is opened.
        prisoner: for (Integer i : secretList) {
            // Create a list of integers from 0 to n-1, and shuffle it.
            List<Integer> trialList = IntStream.range(0, n).boxed().collect(Collectors.toList());
            Collections.shuffle(trialList);

            // For each prisoner, check if the door to the cell he chose is opened.
            for (int j = 0; j < trialList.size() / 2; ++j) {
                // If the door to the cell he chose is opened, he will continue to the next prisoner.
                if (Objects.equals(trialList.get(j), i)) {
                    continue prisoner;
                }
            }
            // If the door to the cell he chose is not opened, he will continue to the next prisoner.
            return false;
        }
        return true;
    }

    // Executes the given play function n times and returns the success rate.
    private static double exec(int n, int p, Function<Integer, Boolean> play) {
        // Count the number of successful plays.
        int succ = 0;
        // Execute the play function n times.
        for (int i = 0; i < n; ++i) {
            // If the play is successful, increment the counter.
            if (Boolean.TRUE.equals(play.apply(p))) {
                succ++;
            }
        }
        // Return the success rate.
        return (succ * 100.0) / n;
    }

}