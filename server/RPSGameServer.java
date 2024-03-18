package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RPSGameServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println("Server is waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to a client!");

                // Start a new thread to handle the client
                Thread clientThread = new Thread(new RPSGameHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
