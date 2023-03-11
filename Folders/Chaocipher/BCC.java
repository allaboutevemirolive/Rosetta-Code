package Chaocipher;

import java.util.Arrays;

public class BCC {
    // Define the encryption and decryption modes
    public enum Mode {
        ENCRYPT,
        DECRYPT
    }

    // Define the left and right alphabets
    public static final String LEFT_ALPHABET = "HXUCZVAMDSLKPEFJRIGTWOBNYQ";
    public static final String RIGHT_ALPHABET = "PTLNBQDEOYSFAVZKGJRIHWXUMC";

    // Define a helper function to find the index of a character in an array
    public int indexOf(char[] array, char character) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == character) {
                return i;
            }
        }
        return -1;
    }

    // Define an overloaded execute method to set showSteps to false by default
    public String execute(String text, Mode mode) {
        return execute(text, mode, false);
    }

    // Define the main execute method that performs the encryption or decryption
    public String execute(String text, Mode mode, boolean showSteps) {
        // Convert the left and right alphabets to character arrays
        char[] leftAlphabet = LEFT_ALPHABET.toCharArray();
        char[] rightAlphabet = RIGHT_ALPHABET.toCharArray();
        // Define an array to store the encrypted or decrypted text
        char[] encryptedText = new char[text.length()];
        // Define a temporary array to help with swapping letters in the alphabets
        char[] temp = new char[26];

        // Loop through each character in the input text
        for (int i = 0; i < text.length(); ++i) {
            // If showSteps is true, print out the current state of the left and right
            // alphabets
            if (showSteps) {
                System.out.printf("%s  %s%n", new String(leftAlphabet), new String(rightAlphabet));
            }
            int index;
            // If encrypting, find the index of the character in the right alphabet
            if (mode == Mode.ENCRYPT) {
                index = indexOf(rightAlphabet, text.charAt(i));
                // Replace the character with the corresponding letter from the left alphabet
                encryptedText[i] = leftAlphabet[index];
            } else {
                // If decrypting, find the index of the character in the left alphabet
                index = indexOf(leftAlphabet, text.charAt(i));
                // Replace the character with the corresponding letter from the right alphabet
                encryptedText[i] = rightAlphabet[index];
            }
            // If we are at the end of the input text, break out of the loop
            if (i == text.length() - 1) {
                break;
            }

            // Move the letters in the left alphabet to the right
            if (26 - index >= 0) {
                System.arraycopy(leftAlphabet, index, temp, 0, 26 - index);
            }
            System.arraycopy(leftAlphabet, 0, temp, 26 - index, index);
            char storedCharacter = temp[1];
            System.arraycopy(temp, 2, temp, 1, 12);
            temp[13] = storedCharacter;
            leftAlphabet = Arrays.copyOf(temp, temp.length);

            // Move the letters in the right alphabet to the left
            if (26 - index >= 0) {
                System.arraycopy(rightAlphabet, index, temp, 0, 26 - index);
            }
            System.arraycopy(rightAlphabet, 0, temp, 26 - index, index);
            storedCharacter = temp[0];
            System.arraycopy(temp, 1, temp, 0, 25);
            temp[25] = storedCharacter;
            storedCharacter = temp[2];
            System.arraycopy(temp, 3, temp, 2, 11);
            temp[13] = storedCharacter;
            rightAlphabet = Arrays.copyOf(temp, temp.length);
        }
        return new String(encryptedText);
    }
}
