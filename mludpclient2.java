#UDP Client
import java.io.*;
import java.net.*;

public class MultiThreadedUDPClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        
        while (true) {
            System.out.print("Please enter a message to send to the server (or type 'quit' to exit): ");
            String message = inFromUser.readLine();
            if (message.equals("quit")) {
                break;
            }
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String response = new String(receivePacket.getData()).trim();
            System.out.println("FROM SERVER: " + response);
        }
        clientSocket.close();
    }
}