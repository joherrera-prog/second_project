import java.util.Scanner;

/**
 * Main class for Tic-Tac-Toe game.
 */
public final class Main {

    /** Constant defining the size of the board. */
    private static final int BOARD_SIZE = 3;

    /** The game board matrix. */
    private static final char[][] BOARD = new char[BOARD_SIZE][BOARD_SIZE];

    private Main() {
        // Prevent instantiation of utility class
    }

    /**
     * Entry point of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in,
                java.nio.charset.StandardCharsets.UTF_8);

        initializeBoard();
        printBoard();

        char turn = 'X';
        boolean gameOver = false;

        // Clean and readable game loop
        while (!gameOver) {
            // 1. Get a validated move from the user
            //We receive whatever getValidMove returns and store it in move
            int[] move = getValidMove(scanner);

            // 2. Register move and print
            BOARD[move[0]][move[1]] = turn;
            printBoard();

            // 3. Evaluate the state of the game
            //Takes the last X or O and if that makes the win it prints that
            if (verifyVictory(turn)) {
                System.out.println(turn + " wins");
                gameOver = true;
            } else if (fullBoard()) {
                System.out.println("Draw");
                gameOver = true;
            } else {
                // Change turn only if the game continues
                //Assigns current letter to 'turn'
                turn = turn == 'X' ? 'O' : 'X';
            }
        }
    }

    /**
     * Prompts the user and validates the input coordinates.
     *
     * @param scanner the input scanner
     * @return an array with the valid row and column indices (0-based)
     */
    public static int[] getValidMove(final Scanner scanner) {
        int validRow = -1;
        int validCol = -1;
        boolean valid = false;

        while (!valid) {
            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }

            int row = scanner.nextInt();
            int column = scanner.nextInt();

            if (!isValid(row, column)) {
                System.out.println("This cell is not valid!");
            } else if (!isFree(row, column)) {
                System.out.println("This cell is occupied! "
                        + "Choose another one!");
            } else {
                validRow = row - 1;
                validCol = column - 1;
                valid = true;
            }
        }
        //I created a new array (object) to store the coordinates)
        return new int[]{validRow, validCol};
    }

    /** Initializes the board with empty spaces. */
    public static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                BOARD[i][j] = ' ';
            }
        }
    }

    /** Prints the board to the console using nested loops. */
    public static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(BOARD[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    /**
     * Checks if the board is full.
     *
     * @return true if there are no free spaces
     */
    public static boolean fullBoard() {
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                if (BOARD[r][c] == ' ') {
                    // If there are blanks, returns false
                    return false;
                }
            }
        }
        // If there are no blanks, returns true
        return true;
    }

    /**
     * Verifies if someone won.
     *
     * @param p is X or O
     * @return true if a player wins
     */
    public static boolean verifyVictory(final char p) {
        return BOARD[0][0] == p && BOARD[0][1] == p && BOARD[0][2] == p
                || BOARD[1][0] == p && BOARD[1][1] == p && BOARD[1][2] == p
                || BOARD[2][0] == p && BOARD[2][1] == p && BOARD[2][2] == p
                || BOARD[0][0] == p && BOARD[1][0] == p && BOARD[2][0] == p
                || BOARD[0][1] == p && BOARD[1][1] == p && BOARD[2][1] == p
                || BOARD[0][2] == p && BOARD[1][2] == p && BOARD[2][2] == p
                || BOARD[0][0] == p && BOARD[1][1] == p && BOARD[2][2] == p
                || BOARD[0][2] == p && BOARD[1][1] == p && BOARD[2][0] == p;
    }

    /**
     * Verifies if coordinates are valid.
     *
     * @param row coordinate
     * @param column coordinate
     * @return true if its valid
     */
    public static boolean isValid(final int row, final int column) {
        return row >= 1 && row <= BOARD_SIZE
                && column >= 1 && column <= BOARD_SIZE;
    }

    /**
     * Verifies if its free.
     *
     * @param row 1 to 3
     * @param column 1 to 3
     * @return true if coordinate is available
     */
    public static boolean isFree(final int row, final int column) {
        return BOARD[row - 1][column - 1] == ' ';
    }

    /**
     * Injects a specific board configuration for unit testing.
     *
     * @param testBoard the mock board
     */
    //testBoard is just a temporal parameter that receives info from tests.
    public static void setTestBoard(final char[][] testBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            // We copy
            System.arraycopy(testBoard[i], 0, BOARD[i], 0, BOARD_SIZE);
        }
    }
}
