#TCP Client
import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 5000);
        System.out.println("Connected to server at " + clientSocket.getInetAddress().getHostAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            out.println(inputLine);
            String response = in.readLine();
            System.out.println("Server says: " + response);
            if (inputLine.equals("bye")) {
                break;
            }
        }
        in.close();
        out.close();
        clientSocket.close();
        System.out.println("Connection closed.");
    }
}