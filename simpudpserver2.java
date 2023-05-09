#UDP Server 2
import java.io.*;
import java.net.*;

public class UDPFileServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            byte[] fileData = receivePacket.getData();
            ByteArrayInputStream bis = new ByteArrayInputStream(fileData);
            ObjectInputStream ois = new ObjectInputStream(bis);
            String fileName = ois.readUTF();
            FileOutputStream fos = new FileOutputStream("received/" + fileName);
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = ois.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("File received: " + fileName);
            fos.close();
        }
    }
}