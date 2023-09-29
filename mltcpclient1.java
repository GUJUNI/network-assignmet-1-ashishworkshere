#TCP Client
import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 5000);
        System.out.println("Connected to server at " + clientSocket.getInetAddress().getHostAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String welcome = in.readLine();
        System.out.println("Server says: " + welcome);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String name = stdIn.readLine();
        out.println(name);

        String greeting = in.readLine();
        System.out.println("Server says: " + greeting);

        in.close();
        out.close();
        clientSocket.close();
        System.out.println("Connection closed.");
    }
}
