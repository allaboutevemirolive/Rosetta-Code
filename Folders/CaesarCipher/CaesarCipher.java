package CaesarCipher;

class CaesarCipher {


    public static void main(String[] args) {
        CaesarCipher obj = new CaesarCipher();
        String str = "The quick brown fox Jumped over the lazy Dog";

        System.out.println("Encode:");
        System.out.println(obj.encode(str, 12));
        System.out.println("Decode:");
        System.out.println(obj.decode(obj.encode(str, 12), 12));
    }

    // This method decodes an encoded string by subtracting the offset from the given key.
    public String decode(String enc, int offset) {
        return encode(enc, 26-offset);
    }

    // This method encodes a given string by adding the given offset to each character.
    public String encode(String enc, int offset) {
        // Make sure the offset is within the range of 0 to 25 by using modulo operation.
        offset = offset % 26 + 26;
        StringBuilder encoded = new StringBuilder();
        // Loop through each character in the given string.
        for (char i : enc.toCharArray()) {
            // Check if the character is a letter.
            if (Character.isLetter(i)) {
                // Check if the letter is uppercase.
                if (Character.isUpperCase(i)) {
                    // Add the offset to the uppercase letter and append the encoded character to the StringBuilder.
                    encoded.append((char) ('A' + (i - 'A' + offset) % 26 ));
                } else {
                    // Add the offset to the lowercase letter and append the encoded character to the StringBuilder.
                    encoded.append((char) ('a' + (i - 'a' + offset) % 26 ));
                }
            } else {
                // Append the character to the StringBuilder without encoding.
                encoded.append(i);
            }
        }
        // Convert the encoded StringBuilder to a String and return it.
        return encoded.toString();
    }
}
