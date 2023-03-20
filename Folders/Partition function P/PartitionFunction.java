import java.math.BigInteger;

public class PartitionFunction {
    public static void main(String[] args) {
        // Start the timer to measure the execution time of partitions() method
        long start = System.currentTimeMillis();
        
        // Call the partitions() method to calculate P(6666)
        BigInteger result = partitions(6666);
        
        // Stop the timer and calculate the elapsed time
        long end = System.currentTimeMillis();
        System.out.println("P(6666) = " + result);
        System.out.printf("Elapsed time: %d milliseconds%n", end - start);
    }

    // Method to calculate the partition function P(n)
    private static BigInteger partitions(int n) {
        // Initialize an array to store the values of P(n) up to n
        BigInteger[] p = new BigInteger[n + 1];
        // P(0) = 1
        p[0] = BigInteger.ONE; 
        
        // Iterate over all values of i from 1 to n
        for (int i = 1; i <= n; ++i) {
            // Initialize p[i] to 0
            p[i] = BigInteger.ZERO; 
            
            // Iterate over all values of k such that (k * (3 * k - 1)) / 2 <= i
            for (int k = 1;; ++k) {
                // Calculate the jth pentagonal number
                // Formula : https://www.youtube.com/watch?v=iJ8pnCO0nTY
                //           https://math.stackexchange.com/questions/453562/formula-for-pentagonal-numbers
                //           https://en.wikipedia.org/wiki/Partition_(number_theory)#Partition_function
                //           https://upload.wikimedia.org/wikipedia/commons/0/05/Euler_partition_function.svg
                //           https://en.wikipedia.org/wiki/Pentagonal_number#Partition_function
                //           https://en.wikipedia.org/wiki/Pentagonal_number#Generating_function
                int j = (k * (3 * k - 1)) / 2; 
                
                // If the pentagonal number j exceeds i, break out of the loop
                if (j > i) {
                    break;
                }
                
                // If k is odd, add P(i - j) to p[i], else subtract P(i - j) from p[i]
                // ((k & 1) != 0) is equivalent to (k % 2 != 0)
                // When you perform a bitwise AND operation between an integer k and 1, 
                // it returns 1 if the least significant bit of k is 1, indicating that k is an odd number. 
                // Otherwise, it returns 0, indicating that k is an even number.
                if ((k & 1) != 0) {
                    p[i] = p[i].add(p[i - j]);
                } else {
                    p[i] = p[i].subtract(p[i - j]);
                }
                
                // Calculate the next pentagonal number
                j += k;
                
                // If the pentagonal number j exceeds i, break out of the loop
                if (j > i) {
                    break;
                }
                
                // If k is odd, add P(i - j) to p[i], else subtract P(i - j) from p[i]
                // ((k & 1) != 0) is equivalent to (k % 2 != 0)
                if ((k & 1) != 0) {
                    p[i] = p[i].add(p[i - j]);
                } else {
                    p[i] = p[i].subtract(p[i - j]);
                }
            }
        }
        
        return p[n];
    }
}
