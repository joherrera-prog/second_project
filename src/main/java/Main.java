import java.util.Scanner;

/**
 * Main class for Tic-Tac-Toe game.
 */
public final class Main {

    private Main() {
        // Constructor vacio para ocultar el publico por defecto
    }
    /** Constant defining the size of the board. */
    private static final int BOARD_SIZE = 3;
    /**
     * Entry point of the application.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] tablero = new char[BOARD_SIZE][BOARD_SIZE];


        // INICIALIZAR EL TABLERO VACIO
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                tablero[i][j] = ' '; // Llenamos con espacios en blanco
            }
        }

        // Imprimir tablero inicial
        System.out.println("---------");
        System.out.println("| " + tablero[0][0] + " "
                            + tablero[0][1]
                            + " " + tablero[0][2] + " |");
        System.out.println("| " + tablero[1][0] + " "
                            + tablero[1][1]
                            + " " + tablero[1][2] + " |");
        System.out.println("| " + tablero[2][0] + " "
                            + tablero[2][1]
                            + " " + tablero[2][2] + " |");
        System.out.println("---------");

        char turno = 'X'; // La 'X' empieza

        //validacion de todo
        while (true) {
            int i = 0;
            int j = 0;

            // validacion de inputs
            boolean jugadaValida = false;
            while (!jugadaValida) {
                if (!scanner.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine();
                    continue;
                }

                int fila = scanner.nextInt();
                int columna = scanner.nextInt();

                if (fila < 1 || fila > BOARD_SIZE || columna < 1
                        || columna > BOARD_SIZE) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                // conversion
                i = fila - 1;
                j = columna - 1;

                if (tablero[i][j] == 'X' || tablero[i][j] == 'O') {
                    System.out.println("This cell is occupied! "
                                        + "Choose another one!");
                    continue;
                }

                // Jugada valida
                jugadaValida = true;

            }

            // REGISTRAR JUGADA Y CAMBIAR TURNO
            tablero[i][j] = turno;

            // Imprimir el tablero actualizado
            System.out.println("---------");
            System.out.println("| " + tablero[0][0] + " " + tablero[0][1]
                                + " " + tablero[0][2] + " |");
            System.out.println("| " + tablero[1][0] + " " + tablero[1][1]
                                + " " + tablero[1][2] + " |");
            System.out.println("| " + tablero[2][0] + " " + tablero[2][1]
                                + " " + tablero[2][2] + " |");
            System.out.println("---------");

            // 4. VERIFICAR VICTORIA O EMPATE
            boolean xWins = tablero[0][0] == 'X'
                    && tablero[0][1] == 'X'
                    && tablero[0][2] == 'X'
                    || tablero[1][0] == 'X' && tablero[1][1] == 'X'
                    && tablero[1][2] == 'X'
                    || tablero[2][0] == 'X' && tablero[2][1] == 'X'
                    && tablero[2][2] == 'X'
                    || tablero[0][0] == 'X' && tablero[1][0] == 'X'
                    && tablero[2][0] == 'X'
                    || tablero[0][1] == 'X' && tablero[1][1] == 'X'
                    && tablero[2][1] == 'X'
                    || tablero[0][2] == 'X' && tablero[1][2] == 'X'
                    && tablero[2][2] == 'X'
                    || tablero[0][0] == 'X' && tablero[1][1] == 'X'
                    && tablero[2][2] == 'X'
                    || tablero[0][2] == 'X' && tablero[1][1] == 'X'
                    && tablero[2][0] == 'X';

            boolean oWins = tablero[0][0] == 'O'
                    && tablero[0][1] == 'O'
                    && tablero[0][2] == 'O'
                    || tablero[1][0] == 'O' && tablero[1][1] == 'O'
                            && tablero[1][2] == 'O'
                    || tablero[2][0] == 'O' && tablero[2][1] == 'O'
                    && tablero[2][2] == 'O'
                    || tablero[0][0] == 'O' && tablero[1][0] == 'O'
                    && tablero[2][0] == 'O'
                    || tablero[0][1] == 'O' && tablero[1][1] == 'O'
                    && tablero[2][1] == 'O'
                    || tablero[0][2] == 'O' && tablero[1][2] == 'O'
                    && tablero[2][2] == 'O'
                    || tablero[0][0] == 'O' && tablero[1][1] == 'O'
                    && tablero[2][2] == 'O'
                    || tablero[0][2] == 'O' && tablero[1][1] == 'O'
                    && tablero[2][0] == 'O';

            // saber si hay empate
            int vacios = 0;
            for (int r = 0; r < BOARD_SIZE; r++) {
                for (int c = 0; c < BOARD_SIZE; c++) {
                    if (tablero[r][c] == ' ') {
                        vacios++;
                    }

                }
            }

            // Evaluar el estado del juego
            if (xWins) {
                System.out.println("X wins");
                break;
            } else if (oWins) {
                System.out.println("O wins");
                break;
            } else if (vacios == 0) {
                System.out.println("Draw");
                break;
            }

            // continua el juego
            if (turno == 'X') {
                turno = 'O';
            } else {
                turno = 'X';
            }
        }
    }
}
