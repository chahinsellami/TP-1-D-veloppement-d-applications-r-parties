package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Port invalide. Utilise: java Server [port]");
                return;
            }
        }

        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
        } catch (IOException e) {
            System.err.println("Erreur adresse locale: " + e.getMessage());
            return;
        }

        String host = localAddress.getHostAddress();
        System.out.println("Serveur de calculatrice en attente sur " + host + ":" + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connecté: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                    DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

                    while (true) {
                        try {
                            byte operation = input.readByte();
                            int x = input.readInt();

                            int result;
                            switch (operation) {
                                case 1: result = x + 5; break;
                                case 2: result = x - 5; break;
                                case 3: result = x * 5; break;
                                case 4:
                                    if (x == 0) {
                                        result = 0; // Division by zero → 0
                                    } else {
                                        result = 5 / x;
                                    }
                                    break;
                                default: result = 0; // Unknown operation → 0
                            }

                            output.writeInt(result);
                            System.out.println("Op " + operation + " (" + x + ") = " + result);
                        } catch (IOException e) {
                            System.err.println("Erreur communication client.");
                            break;
                        }
                    }
                } catch (IOException e) {
                    // Accept loop error
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
