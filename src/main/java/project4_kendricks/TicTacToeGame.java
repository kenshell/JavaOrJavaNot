package project4_kendricks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TicTacToeGame {

    private char[][] board;
    private char currentPlayer;
    private BufferedReader reader;
    private PrintWriter writer;

    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }
    private void makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

   

    private boolean isBoardFull() {
        // Implement full-board-checking logic
        // ...
        return false;
    }
    
 // Add this method to the TicTacToeGame class

    public void makeMove(String move) {
        String[] parts = move.split(" ");
        if (parts.length == 2) {
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            makeMove(row, col);
        } else {
            System.out.println("Invalid move format. Please enter row and column separated by a space.");
        }
    }


    public void runGame(BufferedReader clientReader, PrintWriter clientWriter) throws IOException {
        while (true) {
            printBoard();

            if (currentPlayer == 'X') {
                // Human player's turn
                System.out.println("Your move. Enter row (0-2) and column (0-2) separated by a space:");
                String move = clientReader.readLine();
                if (move.equalsIgnoreCase("exit")) {
                    break;
                }
                makeMove(move);
                clientWriter.println("move " + move);
            } else {
                // Server's turn
                String serverMove = clientReader.readLine();
                if (serverMove.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Server's move: " + serverMove);
                processMove(serverMove);
            }

            if (checkForWin()) {
                printBoard();
                System.out.println(currentPlayer + " WINS!!!");
                break;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }
    }


    private void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void processMove(String move) {
        String[] parts = move.split(" ");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        makeMove(row, col);
    }

  


// Add these methods to the TicTacToeGame class

// Check if there is a winner
private boolean checkForWin() {
    return checkRows() || checkColumns() || checkDiagonals();
}

// Check rows for a win
private boolean checkRows() {
    for (int i = 0; i < 3; i++) {
        if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
            return true;
        }
    }
    return false;
}

// Check columns for a win
private boolean checkColumns() {
    for (int i = 0; i < 3; i++) {
        if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
            return true;
        }
    }
    return false;
}

// Check diagonals for a win
private boolean checkDiagonals() {
    return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
}

// Check if three values are equal and not empty
private boolean checkRowCol(char c1, char c2, char c3) {
    return (c1 != '-' && c1 == c2 && c2 == c3);
}
}






