import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;

class MainTest{

    @Test
    //Cuando esta vacio rentorna falso
    void testTableroLlenoVacio() {
        //Preparamos un tablero totalmente vacio
        char[][] tableroVacio = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        //Verificamos, esperamos que sea Falso que el tablero este lleno
        boolean resultado = Main.tableroLleno(tableroVacio);
        assertFalse(resultado, "El tablero vacio "
               +                 "no deberia estar lleno");
    }

    @Test
    //Retorna verdadero cuando esta lleno
    void testTableroLlenoLleno () {
        //preparamos un tablero sin ningun espacio en blanco
        char[][] tableroCompletamenteLleno = {
                {'X', 'O', 'X'},
                {'X', 'O', 'O'},
                {'O', 'X', 'X'}
        };

        //Verificamos, esperamos que sea Verdadero que el tablero este lleno
        boolean resultado = Main.tableroLleno(tableroCompletamenteLleno);
        assertTrue(resultado, "El tablero sin espacios "
                                + "deberia marcarse como lleno");

    }

    //Probamos que funcione X pueda ganar de manera horizontal
    @Test
    void testVictoriaHorizontal() {
        char [][] tableroHorizontal = {
                {'X', 'X', 'X'},
                {' ', 'O', ' '},
                {' ', ' ', 'O'}
        };
        //Esperamos que X gane, y que O no gane en el mismo tablero
        assertTrue(Main.verificarVictoria(tableroHorizontal, 'X'),
                "X deberia ganar en la primera fila");
    }

    //Probamos que funcione X pueda ganar de manera horizontal
    @Test
    void testVictoriaVertical() {
        char [][] tableroVertical = {
                {'X', ' ', ' '},
                {'X', 'O', ' '},
                {'X', ' ', 'O'}
        };
        //Esperamos que X gane, y que O no gane en el mismo tablero
        assertTrue(Main.verificarVictoria(tableroVertical, 'X'),
                "X deberia ganar en la primera columna");
    }

    //Probamos que funcione X pueda ganar de manera horizontal
    @Test
    void testVictoriaDiagonal() {
        char [][] tableroDiagonal = {
                {'X', ' ', ' '},
                {'O', 'X', ' '},
                {'O', ' ', 'X'}
        };
        //Esperamos que X gane, y que O no gane en el mismo tablero
        assertTrue(Main.verificarVictoria(tableroDiagonal, 'X'),
                "X deberia ganar en la primera columna");
    }

    //Probamos que no termine el juego si nadie ha ganado y el tablero esta incompleto
    @Test
    void testIncompleto() {
        char [][] tableroIncompleto = {
                {'X', 'O', ' '},
                {'O', 'O', 'X'},
                {'O', 'X', 'X'}
        };
        //Esperamos que X gane, y que O no gane en el mismo tablero
        assertFalse(Main.verificarVictoria(tableroIncompleto, 'X'),
                "X no deberia ganar aun");
        assertFalse(Main.verificarVictoria(tableroIncompleto, 'O'),
                "O no deberia ganar aun");
    }

    //Probmos coordenadas validas e invalidas
    @Test
    void testCoordenadaValida(){
        assertTrue(Main.coordenadaValida(1, 1), "1,1 deberia ser valido");
        assertTrue(Main.coordenadaValida(3, 3), "3,3 deberia ser valido");

        assertFalse(Main.coordenadaValida(0, 2), "0,2 fuera de rango");
        assertFalse(Main.coordenadaValida(4, 2), "4,2 fuera de rango");
        assertFalse(Main.coordenadaValida(2, 4), "2,4 fuera de rango");
    }

    //Verificamos si sirve el detecatador de casillas libres
    @Test
    void testCassillaLibre(){
        char[][] tableroPrueba = {
                {'X', ' ', ' '},
                {' ', 'O', ' '},
                {' ', ' ', 'X'}
        };

        assertTrue(Main.casillaLibre(tableroPrueba, 1,2), "Esta casilla deberia estar libre");
        assertFalse(Main.casillaLibre(tableroPrueba, 1,1), "Esta casilla esta ocupada por X");
        assertFalse(Main.casillaLibre(tableroPrueba, 2,2), "Esta casilla esta ocupada por X");
    }

    //Simulamos una partida completa donde X gana
    @Test
    void testJuego_X_Gana(){
        //simulamos los enters usando \n
        //Jugadas: X(1,1), O(2,1), X(1,2), O(2,2), X(1,3) -> X gana en la fila 1
        String entradasSimuladas = "1 1\n2 1\n1 2\n2 2\n1 3\n";

        // Guardamos el teclado real (System.in) para no descomponerlo
        InputStream inOriginal = System.in;

        try{
            //Remplazamos el teclado por nuestro string simulador
            System.setIn(new ByteArrayInputStream(entradasSimuladas.getBytes(StandardCharsets.UTF_8)));

            //ejecutamos el juego completo
            Main.main(new String[]{});
        }finally{
            //restauramos el teclado real al terminar la prueba
            System.setIn(inOriginal);
        }
    }

    //Simulamos errores y empate
    @Test
    void testJuegoEmpateY_Errores(){
        //simulamos los enters usando \n
        //"letra" (inválido), "4 1" (fuera de rango), "1 1" (X), "1 1" (ocupado)
        String entradas = "letra\n4 1\n1 1\n1 1\n1 2\n1 3\n2 1\n2 3\n2 2\n3 1\n3 3\n3 2\n";

        // Guardamos el teclado real (System.in) para no descomponerlo
        InputStream inOriginal = System.in;

        try{
            //Remplazamos el teclado por nuestro string simulador
            System.setIn(new ByteArrayInputStream(entradas.getBytes(StandardCharsets.UTF_8)));

            //ejecutamos el juego completo
            Main.main(new String[]{});
        }finally{
            //restauramos el teclado real al terminar la prueba
            System.setIn(inOriginal);
        }
    }


}