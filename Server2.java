import java.net.*;
import java.io.*;

public class Server2 {
  private ServerSocket serverSocket;

  public static void main(String[] args) throws IOException {
    int port = 8009;
    Server2 server = new Server2();
    server.start(port);
  }

  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    System.out.println("Dev Server started on port " + port);

    // accepting connections
    while (true) {
      Socket clientSocket = serverSocket.accept();
      System.out.println("Accepted connection from " + clientSocket.getRemoteSocketAddress());
      new ClientHandler(clientSocket).start();
    }

  }

  // a class to handle the connection between the client and server
  private static class ClientHandler extends Thread {

    private Socket clientSocket;

    public ClientHandler(Socket socket) {
      this.clientSocket = socket;
    }

    public void run() {
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          System.out.println("Received message from client: " + inputLine);
          out.println("Server received: " + inputLine);
        }
      } catch (IOException e) {
        System.out.println("Error handling client: " + e);
      } finally {
        try {
          clientSocket.close();
        } catch (IOException e) {
          System.out.println("Error closing client socket: " + e);
        }
      }
    }
  }
}
