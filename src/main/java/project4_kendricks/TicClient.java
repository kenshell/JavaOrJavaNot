package project4_kendricks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            // Read the hello message from the server
            String helloMessage = serverReader.readLine();
            System.out.println(helloMessage);

            // Create and run the game
            TicTacToeGame game = new TicTacToeGame();
            game.runGame(new BufferedReader(new InputStreamReader(System.in)), serverWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
