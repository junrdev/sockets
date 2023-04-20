import java.io.*;
import java.net.*;

public class Server {
  public static void main(String[] args) throws IOException {

    final int port = 8000;

    ServerSocket serverSocket = new ServerSocket(port);

    System.out.println("Server listening on port " + port);

    Socket clientSocket = serverSocket.accept();
    System.out.println("Client connected");

    InputStream input = clientSocket.getInputStream();
    OutputStream output = clientSocket.getOutputStream();

    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    PrintWriter writer = new PrintWriter(output, true);

    String message;

    do {

      message = reader.readLine();
      System.out.println("Received message: " + message);
      writer.println("You said: " + message);

    } while (!message.equals("bye"));

    System.out.println("Closing connection");

    clientSocket.close();
    serverSocket.close();

  }
}
