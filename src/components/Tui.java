package components;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import packets.RoutingEntry;

public class Tui implements Runnable {
    public void handleConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.print("> ");
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                line = line.trim();
                System.out.println("Command entered: " + line);

                if (line.startsWith("exit")) {
                    return;
                } else if (line.startsWith("help")) {
                    System.out.println("Available commands:\n" +
                            "exit,\n" +
                            "help,\n" +
                            "connect <IP> <port>,\n" +
                            "disconnect <name>,\n" +
                            "show_routingtable,\n" +
                            "force_update,\n" +
                            "send <name> <message>,");
                } else if (line.startsWith("dbg1")) {
                    connect("127.0.0.1", "12345", "AAA");
                } else if (line.startsWith("dbg2")) {
                    connect("127.0.0.1", "12346", "BBB");
                } else if (line.startsWith("dbg3")) {
                    connect("127.0.0.1", "12347", "CCC");
                } else if (line.startsWith("connect")) {
                    String[] args = line.split("\\s+");
                    if (args.length < 4) {
                        System.out.println("Invalid connect command");
                        continue;
                    }
                    connect(args[1], args[2], args[3]);
                } else if (line.startsWith("disconnect")) {
                    String[] args = line.split("\\s+");
                    if (args.length < 2) {
                        System.out.println("Invalid disconnect command");
                        continue;
                    }
                    disconnect(args[1]);
                } else if (line.startsWith("show_routingtable") || line.startsWith("list_routingtable")) {
                    // @TODO
                } else if (line.startsWith("force_update")) {
                    System.out.println("Forcing update");
                    // @TODO
                } else if (line.startsWith("send")) {
                    String[] args = line.split("\\s+", 3);
                    if (args.length < 3) {
                        System.out.println("Invalid send command");
                        continue;
                    }
                    send(args[1], args[2]);
                } else {
                    System.out.println("Unknown command: " + line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void connect(String destination, String port, String targetName) {
        System.out.println("Connecting to " + destination + " on port " + port);
        // @TODO
    }

    private void disconnect(String destination) {
        System.out.println("Disconnecting from " + destination);
        // Handle disconnection logic @TODO
    }

    private void send(String destination, String message) {
        System.out.println("Sending message to " + destination + ": " + message);
        // @TODO
    }

    @Override
    public void run() {
        handleConsole();
    }
}
