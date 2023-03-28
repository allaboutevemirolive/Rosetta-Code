// Import necessary classes and packages
import static java.lang.Math.abs;
import java.util.Random;

// https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
// https://chess.stackexchange.com/questions/19331/how-does-x-fen-chess960-fen-differentiate-from-traditional-fen-notation
public class Fen {
    // Create an instance of the Random class
    static Random rand = new Random();

    // Define the main method
    public static void main(String[] args) {
        // Print the random chess position in FEN format
        System.out.println(createFen());
    }

    // Define a static method that creates a random chess position in FEN format
    static String createFen() {
        // Create an 8x8 2D array to represent the chessboard
        char[][] grid = new char[8][8];

        // Place the kings randomly on the chessboard
        placeKings(grid);

        // Place the pawns and non-pawn pieces randomly on the chessboard
        // white pieces
        placePieces(grid, "PPPPPPPP", true);
        // black pieces
        placePieces(grid, "pppppppp", true);
        // white pieces
        placePieces(grid, "RNBQBNR", false);
        // black pieces
        placePieces(grid, "rnbqbnr", false);

        // Convert the 2D array representation of the chessboard to FEN format
        return toFen(grid);
    }

    // Define a static method that places the kings randomly on the chessboard
    static void placeKings(char[][] grid) {
        // Declare variables to store the row and column indices of the two kings
        int r1, c1, r2, c2;
        
        // Generate random row and column indices for the two kings, ensuring that they are not on adjacent squares
        while (true) {
            r1 = rand.nextInt(8);
            c1 = rand.nextInt(8);
            r2 = rand.nextInt(8);
            c2 = rand.nextInt(8);

            // r1 != r2         checks that the kings are not in the same row
            // abs(r1 - r2) > 1 checks that they are not one square apart 'vertically'
            // abs(c1 - c2) > 1 checks that they are not one square apart 'horizontally'.
            if (r1 != r2 && abs(r1 - r2) > 1 && abs(c1 - c2) > 1) {
                // Once the conditions are met, the break statement is executed, and the loop is terminated.
                break;
            }
            // If any of these conditions is not met, the loop continues and 
            // generates new random positions for the kings until the conditions are satisfied
        }
        
        // Place the two kings on the chessboard
        grid[r1][c1] = 'K';
        grid[r2][c2] = 'k';
    }

    // Define a static method that places the non-king pieces randomly on the chessboard
    static void placePieces(char[][] grid, String pieces, boolean isPawn) {
        // Generate a random number of pieces to place on the chessboard
        int numToPlace = rand.nextInt(pieces.length());
        
        // Loop over the number of pieces to place
        for (int n = 0; n < numToPlace; n++) {
            // Declare variables to store the row and column indices of the piece
            int r, c;
            
            // Generate random row and column indices for the piece, 
            // ensuring that the square is empty and, 
            // if it is a pawn, that it is not in the promotion square
            do {
                r = rand.nextInt(8);
                c = rand.nextInt(8);
            } while (grid[r][c] != 0 || (isPawn && (r == 7 || r == 0)));
            
            // Place the piece on the chessboard
            grid[r][c] = pieces.charAt(n);
        }
    }

    static String toFen(char[][] grid) {
        // Initialize a StringBuilder object to store the FEN notation
        StringBuilder fen = new StringBuilder();
        
        // Initialize a counter to keep track of consecutive empty squares
        int countEmpty = 0;
        
        // Loop through each square in the grid
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                // Get the piece in the current square
                char ch = grid[r][c];
                
                // Print the piece to the console (for debugging purposes)
                System.out.printf("%2c ", ch == 0 ? '.' : ch);
                
                // If the square is empty, increment the countEmpty counter
                if (ch == 0) {
                    countEmpty++;
                } else {
                    // If there are any consecutive empty squares, append their count to the FEN notation
                    if (countEmpty > 0) {
                        fen.append(countEmpty);
                        // Reset the countEmpty counter
                        countEmpty = 0;
                    }
                    // Append the piece to the FEN notation
                    fen.append(ch);
                }
            }
            // If there are any consecutive empty squares at the end of a row, append their count to the FEN notation
            if (countEmpty > 0) {
                fen.append(countEmpty);
                // Reset the countEmpty counter
                countEmpty = 0;
            }
            // Append a slash to indicate the end of the row
            fen.append("/");
            // Print a newline to the console (for formatting purposes)
            System.out.println();
        }
        
        // Append the remaining FEN notation (color to move, castling rights, en passant square, and halfmove and fullmove counters)
        return fen.append(" w - - 0 1").toString();
    }
}