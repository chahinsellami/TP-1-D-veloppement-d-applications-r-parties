package clientPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];
        }

        Scanner scanner = new Scanner(System.in);
        InputStream input = null;
        OutputStream output = null;

        try (Socket socket = new Socket(host, DEFAULT_PORT)) {
            input = socket.getInputStream();
            output = socket.getOutputStream();

            System.out.println("=== Calculatrice TCP - protocole 1 octet ===");
            System.out.println("Le serveur ajoute 5 a chaque nombre.");
            System.out.println("Entrez un nombre entre -128 et 122, ou 999 pour quitter.");

            while (true) {
                System.out.print("x = ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Entrez un nombre entier.");
                    scanner.next();
                    continue;
                }

                int x = scanner.nextInt();
                if (x == 999) {
                    break;
                }
                if (x < -128 || x > 122) {
                    System.out.println("Le nombre doit etre entre -128 et 122.");
                    continue;
                }

                output.write(x);
                output.flush();

                try {
                    int result = input.read();
                    if (result >= 128) {
                        result -= 256;
                    }
                    System.out.println("Result: " + x + " + 5 = " + result);
                } catch (IOException e) {
                    System.err.println("Erreur communication.");
                    break;
                }
            }
        } catch (IOException exception) {
            System.err.println("Erreur client: " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            scanner.close();
            if (input != null) {
                try { input.close(); } catch (IOException ignored) {}
            }
            if (output != null) {
                try { output.close(); } catch (IOException ignored) {}
            }
        }
    }
}
