#TCP Server
import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is running and waiting for a connection...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection established with " + clientSocket.getInetAddress().getHostAddress());
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println("Welcome to the server! Please enter your name:");
                String name = in.readLine();
                out.println("Hello, " + name + "!");

                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Connection closed with " + clientSocket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}