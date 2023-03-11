package Chaocipher;

public class ACC {
    public static void main(String[] args) {
        BCC obj = new BCC();
        String plainText = "WELLDONEISBETTERTHANWELLSAID";
        System.out.printf("The original plaintext is: %s%n", plainText);
        System.out.println("\nThe left and right alphabets after each permutation during encryption are:");
        String cipherText = obj.execute(plainText, BCC.Mode.ENCRYPT, true);
        System.out.printf("%nThe cipher text is: %s%n", cipherText);
        String decryptedText = obj.execute(cipherText, BCC.Mode.DECRYPT);
        System.out.printf("%nThe recovered plaintext is: %s%n", decryptedText);
    }
}
