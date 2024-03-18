package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RPSGameClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String serverAddress = "rps_server_container";
        int port = 8888;

        // Prompt the user for server address
        while (serverAddress.isEmpty()) {
            System.out.print("Enter server address: ");
            serverAddress = scanner.nextLine().trim();
            if (serverAddress.isEmpty()) {
                System.out.println("Server address cannot be empty.");
            }
        }

        // Prompt the user for port
        while (port == 0) {
            System.out.print("Enter port: ");
            try {
                port = Integer.parseInt(scanner.nextLine().trim());
                if (port <= 0 || port > 65535) {
                    System.out.println("Invalid port number. Please enter a valid port.");
                    port = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number. Please enter a valid port.");
            }
        }

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to the server!");

            // Get input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int clientWins = 0;
            int serverWins = 0;

            while (clientWins < 3 && serverWins < 3) {
                System.out.print("Round: " + (clientWins + serverWins) + " | Enter your choice (rock, paper, scissors, lizard, spock): ");
                String choice = scanner.nextLine();

                // Validate user input
                if (!isValidChoice(choice)) {
                    System.out.println("Invalid input. Please choose rock, paper, or scissors.");
                    continue; // Skip this round
                }

                // Send user choice to the server
                out.println(choice);

                // Receive and print the result from the server
                String result = in.readLine();

                if (result.startsWith("You win!")) {
                    clientWins++;
                } else if (result.startsWith("You lose!")) {
                    serverWins++;                    
                }

                System.out.println(" " + clientWins + ":" + serverWins + "     | Result from the server: " + result);
            }

            if (clientWins >= 3) {
                System.out.println("You win the match!");
            } else {
                System.out.println("You lose the match!");
            }

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the scanner
            scanner.close();
        }
    }

    private static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors") || choice.equals("lizard") || choice.equals("spock");
    }
}
