import java.util.Scanner;


/**
 * Main class for Tic-Tac-Toe game.
 */
public final class Main {

    private Main() {
        // Empty constructor to hide public
        // Prevent instantiation of utility class
    }
    /** Constant defining the size of the board. */
    private static final int BOARD_SIZE = 3;
    /**
     * Entry point of the application.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in,
                java.nio.charset.StandardCharsets.UTF_8);
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];


        // Starts new board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' '; // we fill the blanks
            }
        }

        // Prints empty board
        System.out.println("---------");
        System.out.println("| " + board[0][0] + " "
                            + board[0][1]
                            + " " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " "
                            + board[1][1]
                            + " " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " "
                            + board[2][1]
                            + " " + board[2][2] + " |");
        System.out.println("---------");

        char turn = 'X'; // X starts

        // Game validation
        while (true) {
            int i = 0;
            int j = 0;

            // Input validation
            boolean validMove = false;
            while (!validMove) {
                if (!scanner.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                    continue;
                }


                int row = scanner.nextInt();
                int column = scanner.nextInt();


                //checking in moves are valid
                if (!isValid(row, column)) {
                    System.out.println("This cell is not valid!");
                    continue;
                }

                if (!isFree(board, row, column)) {
                    System.out.println("This cell is occupied!"
                            + "Choose another one!");
                    continue;
                }

                // conversion
                i = row - 1;
                j = column - 1;
                validMove = true;
            }



            // Register move and change turn
            board[i][j] = turn;

            // Print updated board
            System.out.println("---------");
            System.out.println("| " + board[0][0] + " " + board[0][1]
                                + " " + board[0][2] + " |");
            System.out.println("| " + board[1][0] + " " + board[1][1]
                                + " " + board[1][2] + " |");
            System.out.println("| " + board[2][0] + " " + board[2][1]
                                + " " + board[2][2] + " |");
            System.out.println("---------");



            // Evaluate the state of the game
            if (verifyVictory(board, 'X')) {
                System.out.println("X wins");
                break;
            } else if (verifyVictory(board, 'O')) {
                System.out.println("O wins");
                break;
            } else if (fullBoard(board)) {
                System.out.println("Draw");
                break;
            }

            // continua el juego
            if (turn == 'X') {
                turn = 'O';
            } else {
                turn = 'X';
            }
        }
    }
    /**
     * Changed for boolean values.
    *@param board is the matrix
    *@return true if there arent free spaces
     */
    public static boolean fullBoard(final char[][] board) {
            for (int r = 0; r < BOARD_SIZE; r++) {
                for (int c = 0; c < BOARD_SIZE; c++) {
                    if (board[r][c] == ' ') {
                        return false;
                    }
                }
            }
            return true;
    }

    /**
     * Verifies if someone won.
     *
     * @param board is the matrix
     * @param p is X or O
     * @return true if a player wins
     */
    public static boolean verifyVictory(final char[][] board, final char p) {
        return board[0][0] == p && board[0][1] == p && board[0][2] == p
                || board[1][0] == p && board[1][1] == p && board[1][2] == p
                || board[2][0] == p && board[2][1] == p && board[2][2] == p
                || board[0][0] == p && board[1][0] == p && board[2][0] == p
                || board[0][1] == p && board[1][1] == p && board[2][1] == p
                || board[0][2] == p && board[1][2] == p && board[2][2] == p
                || board[0][0] == p && board[1][1] == p && board[2][2] == p
                || board[0][2] == p && board[1][1] == p && board[2][0] == p;
        // VERIFIES victory or draw

    }

    /**
     * Verifies if coordenates are valid.
     *
     * @param row coordinate
     * @param column coordinate
     * @return true if its valid
     */
    public static boolean isValid(final int row, final int column) {
        return row >= 1 && row <= BOARD_SIZE && column >= 1 && column
                <= BOARD_SIZE;
    }


    /**
     * Verifies if its free.
     *
     * @param board matrix
     * @param row 1 to 3
     * @param column 1 to 3
     * @return true if coordinate is available
     */

    public static boolean isFree(final char[][] board, final int row,
                                       final int column) {
        return board[row - 1][column - 1] == ' ';
    }

}
