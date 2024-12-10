package project4_kendricks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is running...");
            Socket clientSocket = serverSocket.accept();

            BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            // Send hello message to client
            clientWriter.println("Hello! You are player 2");

            // Create and run the game
            TicTacToeGame game = new TicTacToeGame();
            game.runGame(clientReader, clientWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}