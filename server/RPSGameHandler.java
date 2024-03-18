package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class RPSGameHandler implements Runnable {
    private Socket clientSocket;
    private int clientWins;
    private int serverWins;

    public RPSGameHandler(Socket socket) {
        this.clientSocket = socket;
        this.clientWins = 0;
        this.serverWins = 0;
    }

    @Override
    public void run() {
        try {
            // Get input and output streams for the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Play until one side wins 3 rounds
            while (clientWins < 3 && serverWins < 3) {
                // Generate a random choice for the server
                String[] choices = {"rock", "paper", "scissors", "lizard", "spock"};
                Random random = new Random();
                String serverChoice = choices[random.nextInt(choices.length)];

                // Read the client's choice
                String clientChoice = in.readLine();
                System.out.println("Client choice: " + clientChoice);

                // If clientChoice is null, end the game
                if (clientChoice == null) {
                    break;
                }

                // Determine the winner and send the result back to the client
                String result = determineWinner(clientChoice, serverChoice);
                out.println(result);
            }

            // Send final result after game ends
            if (clientWins >= 3) {
                out.println("You win the best of 5!");
            } else if (serverWins >= 3) {
                out.println("You lose the best of 5!");
            } else {
                out.println("It's a tie in the best of 5!");
            }

            // Close the socket
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String determineWinner(String clientChoice, String serverChoice) {
        // Game Logic
        if (clientChoice.equals(serverChoice)) {
            return "It's a tie! Server chose " + serverChoice;
        } else if ((clientChoice.equals("rock") && serverChoice.equals("scissors")) ||
                (clientChoice.equals("paper") && serverChoice.equals("rock")) ||
                (clientChoice.equals("scissors") && serverChoice.equals("paper")) ||
                (clientChoice.equals("scissors") && serverChoice.equals("lizard")) ||
                (clientChoice.equals("lizard") && serverChoice.equals("paper")) ||
                (clientChoice.equals("paper") && serverChoice.equals("spock")) ||
                (clientChoice.equals("spock") && serverChoice.equals("rock")) ||
                (clientChoice.equals("rock") && serverChoice.equals("lizard")) ||
                (clientChoice.equals("lizard") && serverChoice.equals("spock")) ||
                (clientChoice.equals("spock") && serverChoice.equals("scissors"))) {
            clientWins++;
            return "You win! Server chose " + serverChoice;
        } else {
            serverWins++;
            return "You lose! Server chose " + serverChoice;
        }
    }
}
