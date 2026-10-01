# second_project
Internship

# Tic-Tac-Toe - Java Implementation

A console-based Tic-Tac-Toe implementation developed in Java. This project is part of my development roadmap at JetBrains Academy, designed to practice 2D array manipulation, execution flow control, and defensive user input handling.

## Architecture & Implementation Logic

The project is designed around a synchronous console model. Key structural and technical decisions include:

* **State Representation (`char[][]`):** Rather than working with a 1D array or parsing strings on every turn, I selected a `3x3` matrix initialized with space characters (`' '`). This provides direct positional lookup and clean indexing.
* **Coordinate Mapping:** User-facing 1-based coordinates are translated to Java's 0-based matrix indices via `i = row - 1` and `j = column - 1`.
* **Validation & Input Handling:** A nested validation loop prevents runtime crashes by validating input prior to state updates:
  * Uses `Scanner.hasNextInt()` to catch non-numeric tokens before parsing.
  * Explicitly flushes the buffer using `Scanner.nextLine()` to avoid infinite loops on invalid input.
  * Validates bounds within the `[1, 3]` range and verifies cell availability before committing a move.
* **Game Loop Control:** A primary `while` loop handles turn alternation (`'X'` / `'O'`). After each valid move, the board state is evaluated across all 8 win conditions and open spaces to determine terminal states (`X wins`, `O wins`, `Draw`).

## Execution Flow

1. Matrix initialization (`3x3`) with empty characters and initial grid rendering.
2. Entry into the main game loop.
3. Capture and validation of coordinates for the active player.
4. Matrix state update and updated grid rendering.
5. Evaluation of end-game conditions (Win / Draw).
6. Turn toggle if the game remains active.

## Compilation & Running Instructions

From the terminal in the root directory containing the source code:

```bash
javac Main.java
java Main
