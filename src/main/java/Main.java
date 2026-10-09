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

        //validacion de tod0
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


                //Cambiamos el if por el nuevo metodo booleano de validacion
                //de si la casilla esta libre o no
                if (!coordenadaValida(fila, columna)) {
                    System.out.println("This cell is not valid!");
                    continue;
                }

                if (!casillaLibre(tablero, fila, columna)) {
                    System.out.println("This cell is occupied!"
                            + "Choose another one!");
                    continue;
                }

                // conversion
                i = fila - 1;
                j = columna - 1;
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



            // Evaluar el estado del juego
            if (verificarVictoria(tablero, 'X')) {
                System.out.println("X wins");
                break;
            } else if (verificarVictoria(tablero, 'O')) {
                System.out.println("O wins");
                break;
            } else if (tableroLleno(tablero)) {
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
    
    /**
     * Saber si hay empate, solia tenerlo contador y ahora como boolean  //esto es un Javadoc
    *@param tablero la matriz del juego
    *@return true si no hay espacios vacios, false si hay al menos uno
     */
    public static boolean tableroLleno(final char[][] tablero) {
            for (int r = 0; r < BOARD_SIZE; r++) {
                for (int c = 0; c < BOARD_SIZE; c++) {
                    if (tablero[r][c] == ' ') {
                        return false;
                    }
                }
            }
            return true;
        // saber si hay empate NUEVOOO
    }

    /**
     * Verifica si un jugador ha ganado
     *
     * @param tablero la matriz del juego
     * @param p es el caracter del jugador, X u O
     * @return true si el jugador gano
     */
    public static boolean verificarVictoria(final char[][] tablero, final char p){
        return tablero[0][0] == p && tablero[0][1] == p && tablero[0][2] == p
                || tablero[1][0] == p && tablero[1][1] == p && tablero[1][2] == p
                || tablero[2][0] == p && tablero[2][1] == p && tablero[2][2] == p
                || tablero[0][0] == p && tablero[1][0] == p && tablero[2][0] == p
                || tablero[0][1] == p && tablero[1][1] == p && tablero[2][1] == p
                || tablero[0][2] == p && tablero[1][2] == p && tablero[2][2] == p
                || tablero[0][0] == p && tablero[1][1] == p && tablero[2][2] == p
                || tablero[0][2] == p && tablero[1][1] == p && tablero[2][0] == p;
        // VERIFICAR VICTORIA O EMPATE NUEVOO

    }

    /**
     * Verifica si las coordenadas son validas
     *
     * @param fila coordenada de la fila
     * @param columna coordenada de la columna
     * @return true si es valida, false si esta fuera de rango
     */
    public static boolean coordenadaValida(final int fila,final int columna) {
        return fila >= 1 && fila <= BOARD_SIZE && columna >= 1 && columna <= BOARD_SIZE;
    }


    /**
     * Verifica si la casilla esta libre
     *
     * @param tablero en matriz
     * @param fila del 1 al 3
     * @param columna del 1 al 3
     * @return true si la casilla esta disponible
     */

    public static boolean casillaLibre(final char[][] tablero, final int fila,
                                       final int columna) {
        return tablero[fila - 1][columna - 1] == ' ';
    }

}
