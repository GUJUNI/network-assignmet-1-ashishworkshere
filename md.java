import java.io.*;
import java.security.*;

public class md {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage: java md <filename>");
      System.exit(1);
    }

    String filename = args;
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    try (InputStream is = new FileInputStream(filename)) {
      byte[] buffer = new byte;
      int read = 0;
      while ((read = is.read(buffer)) != -1) {
        md.update(buffer, 0, read);
      }
    }

    byte[] digest = md.digest();
    StringBuilder sb = new StringBuilder();
    for (byte b : digest) {
      sb.append(String.format("%02x", b));
    }

    System.out.println("Message digest (SHA-256) of " + filename + ":");
    System.out.println(sb.toString());
  }
}