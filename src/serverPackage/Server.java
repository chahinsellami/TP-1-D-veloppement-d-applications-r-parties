package serverPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        System.out.println("server works ");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader input = new BufferedReader(
                         new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {
                System.out.println("un client est connect");

                String clientMessage = input.readLine();
                if (clientMessage != null) {
                    System.out.println("Message : " + clientMessage);
                }

                output.println("Connexion estableshed.");
            }
        } catch (IOException exception) {
            System.err.println("Erreur du serveur : " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
