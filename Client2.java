import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
  public static void main(String[] args) {
    try {

      // connecting to server
      Socket socket = new Socket("localhost", 8009);

      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("Connected to server running at port: " + socket.getPort());
      String userInput;

      // continuously read user input and send to server
      while ((userInput = stdIn.readLine()) != null) {
        out.println(userInput);

        // exit if the user types "exit"
        if (userInput.equals("exit")) {
          break;
        }

        // receive response from server and print to console
        String serverResponse = in.readLine();
        System.out.println("Server response: " + serverResponse);

      }
    } catch (UnknownHostException e) {
      System.err.println("Unknown host");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection");
      System.exit(1);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
