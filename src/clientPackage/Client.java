package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader input = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
            output.println("test hello");

            String serverMessage = input.readLine();
            if (serverMessage != null) {
                System.out.println("resposne: " + serverMessage);
            }
        } catch (IOException exception) {
            System.err.println("Erreur du client : " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
