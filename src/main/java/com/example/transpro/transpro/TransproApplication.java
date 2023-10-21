package com.example.transpro.transpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



@SpringBootApplication
public class TransproApplication {

        private static final boolean isServer = true;
	public static void main(String[] args) {
	
    // Set this flag based on the condition whether the application should act as a server or a client

        SpringApplication.run(TransproApplication.class, args);

        if (isServer) {
            startServer();
        } else {
            startClient();
        }
    }

    private static void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8081); // You can change the port as needed

            System.out.println("Server listening on port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Read data from the client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String data = reader.readLine();
                System.out.println("Received data from client: " + data);

                // Process the received data or respond to the client as needed

                // Close the socket and the reader
                clientSocket.close();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void startClient() {
        try {
            Socket socket = new Socket("localhost", 8080); // Connect to the server on localhost:8080

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Hello from client!");

            // Close the socket and the writer
            socket.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
