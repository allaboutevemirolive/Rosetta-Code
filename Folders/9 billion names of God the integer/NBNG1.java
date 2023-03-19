import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NBNG1 {
    public static void main(String[] args) {
        List<Integer> previousRow = Arrays.asList(1, 2, 1);
        List<Integer> currentRow = new ArrayList<>();
        currentRow.add(0); // Add a padding zero at the beginning

        for (int j = 1; j < previousRow.size() + 1; j++) {
            // currentRow.add(
            // currentRow.get(currentRow.size() - 1)
            // + previousRow.get(Math.min(j, previousRow.size() - j))
            // );
            
            // Get the previous element in the current row
            int previousElement = currentRow.get(currentRow.size() - 1);

            // Add the j-th and i-j-th element of the previous row
            int previousRowElement = previousRow.get(Math.min(j, previousRow.size() - j));
            int currentElement = previousElement + previousRowElement;

            currentRow.add(currentElement);
        }

        currentRow.remove(0); // Remove the padding zero at the beginning
        System.out.println(currentRow); // Output: [2, 4, 2]

    }
}
