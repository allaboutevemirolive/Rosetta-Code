package CaesarCipher;

class A_CaesarCipher {
    public static void main(String[] args) {
        CaesarCipher obj = new CaesarCipher();
        String str = "The quick brown fox Jumped over the lazy Dog";

        System.out.println("Encode:");
        System.out.println(obj.encode(str, 12));
        System.out.println("Decode:");
        System.out.println(obj.decode(obj.encode(str, 12), 12));
    }
}
