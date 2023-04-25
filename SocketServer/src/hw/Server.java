package hw;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(555);
            System.out.println("Server started");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 555");
            System.exit(1);
        }

        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        out.println("Welcome to the server!");

        boolean loggedIn = false;
        while (!loggedIn) {
            out.println("Please enter your username:");
            String username = in.readLine();
            out.println("Please enter your password:");
            String password = in.readLine();
            if (username.equals("CMPE322") && password.equals("bilgiuni")) {
                loggedIn = true;
                out.println("You are now logged in. Available commands: date, time, capTurkey, quit");
            } else {
                out.println("Invalid username or password. Please try again.");
            }
        }

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equalsIgnoreCase("date")) {
                out.println(new java.util.Date().toString());
            } else if (inputLine.equalsIgnoreCase("time")) {
                out.println(System.currentTimeMillis());
            } else if (inputLine.equalsIgnoreCase("capTurkey")) {
                out.println("Ankara");
            } else if (inputLine.equalsIgnoreCase("quit")) {
                out.println("Bye bye");
                break;
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

