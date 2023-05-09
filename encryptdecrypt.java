import java.util.Scanner;

public class encryption {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a message: ");
        String message = input.nextLine();

        System.out.print("Enter a key: ");
        int key = input.nextInt();

        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted message: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Decrypted message: " + decryptedMessage);
    }

    public static String encrypt(String message, int key) {
        String encryptedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                c = (char) (c + key);
                if (Character.isLowerCase(message.charAt(i)) && c > 'z'
                        || Character.isUpperCase(message.charAt(i)) && c > 'Z') {
                    c = (char) (c - 26);
                }
            }
            encryptedMessage += c;
        }
        return encryptedMessage;
    }

    public static String decrypt(String message, int key) {
        String decryptedMessage = "";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (Character.isLetter(c)) {
                c = (char) (c - key);
                if (Character.isLowerCase(message.charAt(i)) && c < 'a'
                        || Character.isUpperCase(message.charAt(i)) && c < 'A') {
                    c = (char) (c + 26);
                }
            }
            decryptedMessage += c;
        }
        return decryptedMessage;
    }
}