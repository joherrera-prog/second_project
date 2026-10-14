import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
        // Resets the board before every test to avoid conflicts
    void setUp() {
        Main.initializeBoard();
    }

    @Test
    void testEmpty() {
        char[][] emptyBoard = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        //We send this empty board to testBoard which will be sent to BOARD
        Main.setTestBoard(emptyBoard);
        assertFalse(Main.fullBoard(), "Empty board should not be full");
    }

    @Test
    void testFull() {
        char[][] fullyFullBoard = {
                {'X', 'O', 'X'},
                {'X', 'O', 'O'},
                {'O', 'X', 'X'}
        };
        Main.setTestBoard(fullyFullBoard);
        assertTrue(Main.fullBoard(), "Board must be full");
    }

    @Test
    void testHorizontal() {
        char[][] horizontalBoard = {
                {'X', 'X', 'X'},
                {' ', 'O', ' '},
                {' ', ' ', 'O'}
        };
        Main.setTestBoard(horizontalBoard);
        assertTrue(Main.verifyVictory('X'), "X should win in the first row");
    }

    @Test
    void testVertical() {
        char[][] verticalBoard = {
                {'X', ' ', ' '},
                {'X', 'O', ' '},
                {'X', ' ', 'O'}
        };
        Main.setTestBoard(verticalBoard);
        assertTrue(Main.verifyVictory('X'), "X should win in the first column");
    }

    @Test
    void testDiagonal() {
        char[][] diagonalBoard = {
                {'X', ' ', ' '},
                {'O', 'X', ' '},
                {'O', ' ', 'X'}
        };
        Main.setTestBoard(diagonalBoard);
        assertTrue(Main.verifyVictory('X'), "X should win in the first diagonal");
    }

    @Test
    void testIncomplete() {
        char[][] incompleteBoard = {
                {'X', 'O', ' '},
                {'O', 'O', 'X'},
                {'O', 'X', 'X'}
        };
        Main.setTestBoard(incompleteBoard);
        assertFalse(Main.verifyVictory('X'), "X should not win yet");
        assertFalse(Main.verifyVictory('O'), "O should not win yet");
    }

    @Test
    void testCoordinate() {
        assertTrue(Main.isValid(1, 1), "1,1 should be valid");
        assertTrue(Main.isValid(3, 3), "3,3 should be valid");

        assertFalse(Main.isValid(0, 2), "0,2 should be invalid");
        assertFalse(Main.isValid(4, 2), "4,2 should be invalid");
        assertFalse(Main.isValid(2, 4), "2,4 should be invalid");
    }

    @Test
    void testFree() {
        char[][] freeBoard = {
                {'X', ' ', ' '},
                {' ', 'O', ' '},
                {' ', ' ', 'X'}
        };
        Main.setTestBoard(freeBoard);
        assertTrue(Main.isFree(1, 2), "This box should be empty");
        assertFalse(Main.isFree(1, 1), "This box should be occupied by X");
        assertFalse(Main.isFree(2, 2), "This box should be occupied by X");
    }

    @Test
    void testGame() {
        String testMoves = "1 1\n2 1\n1 2\n2 2\n1 3\n";
        //We save the keyboard input location
        InputStream inOriginal = System.in;

        try {
            //We change the input type to take the string
            System.setIn(new ByteArrayInputStream(testMoves.getBytes(StandardCharsets.UTF_8)));
            //We run the whole game
            Main.main(new String[]{});
        } finally {
            //We change back to keyboard
            System.setIn(inOriginal);
        }
    }

    @Test
    void testGameTwo() {
        String inputs = "letter\n4 1\n1 1\n1 1\n1 2\n1 3\n2 1\n2 3\n2 2\n3 1\n3 3\n3 2\n";
        InputStream inOriginal = System.in;

        try {
            System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
            Main.main(new String[]{});
        } finally {
            System.setIn(inOriginal);
        }
    }
}
