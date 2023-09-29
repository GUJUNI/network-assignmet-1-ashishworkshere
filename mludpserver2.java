#UDP Server
import java.io.*;
import java.net.*;

public class MultiThreadedUDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String message = new String(receivePacket.getData()).trim();
            System.out.println("Received message: " + message);
            
            Thread worker = new WorkerThread(serverSocket, receivePacket);
            worker.start();
        }
    }
}

class WorkerThread extends Thread {
    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    
    public WorkerThread(DatagramSocket serverSocket, DatagramPacket receivePacket) {
        this.serverSocket = serverSocket;
        this.receivePacket = receivePacket;
    }
    
    public void run() {
        try {
            Thread.sleep(5000); // simulate long processing time
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String response = "Hello from the server after processing for 5 seconds!";
            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            System.out.println("Sent message: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}