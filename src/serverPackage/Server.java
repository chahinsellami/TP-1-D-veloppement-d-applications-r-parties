package serverPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

                    InputStream input = clientSocket.getInputStream();
                    OutputStream output = clientSocket.getOutputStream();

                    while (true) {
                        try {
                            int x = input.read();
                            if (x == -1) {
                                break;
                            }
                            if (x >= 128) {
                                x -= 256;
                            }

                            int result = x + 5;
                            output.write(result);
                            output.flush();
                            System.out.println(x + " + 5 = " + result);
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
