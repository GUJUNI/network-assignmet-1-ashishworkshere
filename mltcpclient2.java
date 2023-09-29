#TCP Client
import java.io.*;
import java.net.*;

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
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

        public void run() {
            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received message from " + clientSocket.getInetAddress().getHostAddress() + ": " + inputLine);
                    out.println("Echo: " + inputLine);
                }
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