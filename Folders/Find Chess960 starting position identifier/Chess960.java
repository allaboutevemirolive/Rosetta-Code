import java.util.HashMap;
import java.util.Map;

// https://rosettacode.org/wiki/Find_Chess960_starting_position_identifier#Go
public class Chess960 {

    public static void main(String[] args) {
        String[] positions = { "♕♘♖♗♗♘♔♖", "♖♘♗♕♔♗♘♖", "♖♕♘♗♗♔♖♘", "♖♘♕♗♗♔♖♘" };

        for (String pos : positions) {
            int spid = spid(pos);
            String letters = g2l(pos);
            // System.out.printf("%s : %d%n", pos, spid);
            System.out.printf("%s : %d%n", letters, spid);

        }
    }

    // private static final char[] GLYPHS = { '♜', '♞', '♝', '♛', '♚', '♖', '♘', '♗', '♕', '♔' };
    private static final Map<Character, String> NAMES = new HashMap<>();
    private static final Map<Character, String> G2L_MAP = new HashMap<>();
    private static final Map<String, Integer> NTABLE = new HashMap<>();

    static {
        NAMES.put('R', "rook");
        NAMES.put('N', "knight");
        NAMES.put('B', "bishop");
        NAMES.put('Q', "queen");
        NAMES.put('K', "king");

        G2L_MAP.put('♜', "R");
        G2L_MAP.put('♞', "N");
        G2L_MAP.put('♝', "B");
        G2L_MAP.put('♛', "Q");
        G2L_MAP.put('♚', "K");
        G2L_MAP.put('♖', "R");
        G2L_MAP.put('♘', "N");
        G2L_MAP.put('♗', "B");
        G2L_MAP.put('♕', "Q");
        G2L_MAP.put('♔', "K");

        NTABLE.put("01", 0);
        NTABLE.put("02", 1);
        NTABLE.put("03", 2);
        NTABLE.put("04", 3);
        NTABLE.put("12", 4);
        NTABLE.put("13", 5);
        NTABLE.put("14", 6);
        NTABLE.put("23", 7);
        NTABLE.put("24", 8);
        NTABLE.put("34", 9);
    }

    private static String g2l(String pieces) {
        StringBuilder lets = new StringBuilder();
        for (char p : pieces.toCharArray()) {
            lets.append(G2L_MAP.get(p));
        }
        return lets.toString();
    }

    public static int spid(String pieces) {
        // convert glyphs to letters
        pieces = g2l(pieces); 

        // check for errors
        if (pieces.length() != 8) {
            throw new IllegalArgumentException("There must be exactly 8 pieces.");
        }
        for (char one : "KQ".toCharArray()) {
            int count = 0;
            for (int i = 0; i < pieces.length(); i++) {
                if (pieces.charAt(i) == one) {
                    count++;
                }
            }
            if (count != 1) {
                throw new IllegalArgumentException("There must be one " + NAMES.get(one) + ".");
            }
        }
        for (char two : "RNB".toCharArray()) {
            int count = 0;
            for (int i = 0; i < pieces.length(); i++) {
                if (pieces.charAt(i) == two) {
                    count++;
                }
            }
            if (count != 2) {
                throw new IllegalArgumentException("There must be two " + NAMES.get(two) + ".");
            }
        }
        int r1 = pieces.indexOf('R');
        int r2 = pieces.indexOf('R', r1 + 1);
        int k = pieces.indexOf('K');
        if (k < r1 || k > r2) {
            throw new IllegalArgumentException("The king must be between the rooks.");
        }
        int b1 = pieces.indexOf('B');
        int b2 = pieces.indexOf('B', b1 + 1);
        if ((b2 - b1) % 2 == 0) {
            throw new IllegalArgumentException("The bishops must be on opposite color squares.");
        }

        /* compute SP_ID */
        String piecesN = pieces.replace("Q", "").replace("B", "");
        int n1 = piecesN.indexOf('N');
        int n2 = piecesN.indexOf('N', n1 + 1);
        int N = NTABLE.get("" + n1 + n2);

        String piecesQ = pieces.replace("B", "");
        int Q = piecesQ.indexOf('Q');

        int D = "0246".indexOf("" + b1);
        int L = "1357".indexOf("" + b2);
        if (D == -1) {
            D = "0246".indexOf("" + b2);
            L = "1357".indexOf("" + b1);
        }

        return 96 * N + 16 * Q + 4 * D + L;
    }
}