package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
        DataInputStream input = null;
        DataOutputStream output = null;

        try (Socket socket = new Socket(host, DEFAULT_PORT)) {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            System.out.println("=== Calculatrice TCP ===");
            System.out.println("Choisissez une operation:");
            System.out.println("1. Addition (+5)");
            System.out.println("2. Soustraction (-5)");
            System.out.println("3. Multiplication (*5)");
            System.out.println("4. Division (/5)");

            while (true) {
                System.out.print("Choix: ");
                String choice = scanner.next();
                byte op;
                try {
                    op = (byte) Integer.parseInt(choice);
                } catch (NumberFormatException e) {
                    System.out.println("Choix invalide. Entrez 1-4.");
                    continue;
                }

                if (op < 1 || op > 4) {
                    System.out.println("Choix invalide. Entrez 1-4.");
                    continue;
                }

                System.out.print("x = ");
                int x = scanner.nextInt();

                output.writeByte(op);
                output.writeInt(x);

                try {
                    int result = input.readInt();
                    String opName = (op == 1) ? "+" : (op == 2) ? "-" : (op == 3) ? "*" : "/";
                    System.out.println("Result: " + x + " " + opName + " 5 = " + result);
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
