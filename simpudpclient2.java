#UDP Client
import java.io.*;
import java.net.*;

public class UDPFileClient {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        String fileName = "example.txt";
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] fileData = new byte[(int)file.length()];
        fis.read(fileData);
        fis.close();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeUTF(fileName);
        oos.write(fileData);
        oos.flush();
        oos.close();
        byte[] sendFileData = bos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(sendFileData, sendFileData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        System.out.println("File sent: " + fileName);
        clientSocket.close();
    }
}
