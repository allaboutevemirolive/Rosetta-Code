import java.math.BigInteger;
import java.util.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static java.lang.Math.min;

// Define a class named NineBillNameGod
public class NineBillNameGod {

    // Define the main method
    public static void main(String[] args) {
        // Print the first 10 rows of the number triangle
        System.out.println("Rows:");
        long start = System.currentTimeMillis();
        for (int i = 1; i < 11; i++) {
            System.out.printf("%2d: %s%n", i, getRowList(i));
        }

        // Print the cumulative sums for the numbers 23, 123, and 1234
        System.out.println("\nSums:");
        for (int i : new int[] { 23, 123, 1234 }) {
            List<BigInteger> cumulativeList = getCumulativeList(i);
            System.out.printf("%s %s%n", i, cumulativeList.get(cumulativeList.size() - 1));
        }
        long end = System.currentTimeMillis();
        System.out.printf("Elapsed time: %d milliseconds%n", end - start);
    }

    // Define a static method that returns a list of cumulative sums for each row
    static List<BigInteger> getCumulativeList(int n) {
        // Initialize an ArrayList to hold cached values of cumulative sums for each row
        List<List<BigInteger>> cache = new ArrayList<>();
        // Add the first row, which has a single element of value 1, to the cache
        cache.add(asList(BigInteger.ONE));

        // Iterate from the size of the cache to the target row number n+1
        for (int i = cache.size(); i < n + 1; i++) {
            // Initialize an ArrayList to hold the cumulative sums for the current row
            List<BigInteger> temp = new ArrayList<>();
            // Add a 0 to the beginning of the list, which will be used as a starting point
            // for the cumulative sum
            temp.add(BigInteger.ZERO);
            // Iterate from 1 to i+1, which represents the indices of the previous rows
            // whose values will be added to the current row
            for (int j = 1; j < i + 1; j++) {
                // Add the j-th element of the previous row and the minimum of j and i-j-th
                // element of the previous row to the current element
                temp.add(
                        temp
                                .get(temp.size() - 1)
                                .add(cache.get(i - j)
                                        .get(min(j, i - j))));
                // BigInteger previousElement = temp.get(temp.size() - 1);
                // BigInteger previousRowElement = cache.get(i - j).get(min(j, i - j));
                // BigInteger currentElement = previousElement.add(previousRowElement);
                // temp.add(currentElement);
            }
            // Add the cumulative sums for the current row to the cache
            cache.add(temp);
        }
        // Return the cumulative sums for the target row n
        return cache.get(n);
    }

    // Define a static method that returns a list of differences between adjacent
    // elements in the row
    static List<BigInteger> getRowList(int n) {
        // Get the cumulative sums for the target row
        List<BigInteger> cumulativeList = getCumulativeList(n);
        // Map the differences between adjacent elements in the cumulative sums to a new
        // list
        return range(0, n)
                .mapToObj(i -> cumulativeList.get(i + 1)
                        .subtract(cumulativeList.get(i)))
                .collect(toList());
        // return range(0, n)
        // .mapToObj(i -> {
        // BigInteger cumulative_i_plus_1 = cumulativeList.get(i + 1);
        // BigInteger cumulative_i = cumulativeList.get(i);
        // BigInteger difference = cumulative_i_plus_1.subtract(cumulative_i);
        // return difference;
        // })
        // .collect(toList());

    }

}
