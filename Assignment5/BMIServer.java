import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class BMIServer extends Application {
    private TextArea text = new TextArea();
    private int clientNo = 0;
    String host = "localhost";

    @Override
    public void start(Stage primaryStage){
        Scene scene = new Scene(new ScrollPane(text), 450, 200);
        primaryStage.setTitle("BMI Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread( () -> {
            try {
              ServerSocket serverSocket = new ServerSocket(8000);
              text.appendText("BMI Server started at " + new Date() + '\n');
          
              while (true) {
                Socket socket = serverSocket.accept();
                clientNo++;

                Platform.runLater( () -> {
                  text.appendText("Starting thread for client at " + new Date() + '\n');
      
                  InetAddress inetAddress = socket.getInetAddress();
                  text.appendText("Client " + clientNo + "'s host name is "+ host + "\n");
                  text.appendText("Client " + clientNo + "'s IP Address is " + inetAddress.getHostAddress() + "\n");
                });
                
                new Thread(new HandleAClient(socket)).start();
              }
            }
            catch(IOException ex) {
              System.err.println(ex);
            }
          }).start();
        }
        
        class HandleAClient implements Runnable {
          private Socket socket;
      
          public HandleAClient(Socket socket) {
            this.socket = socket;
          }
      
          public void run() {
            try {
              DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
              DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
      
              while (true) {
                double Weight = inputFromClient.readDouble(); // get input from client for Weight
                double Height = inputFromClient.readDouble(); // get input from client for Height

                BMI bmi = new BMI(Weight, Height); // create BMI object
                     
                // output back to the client
                outputToClient.writeDouble(bmi.getBMI());
                outputToClient.writeUTF(bmi.getStatus());
                

                Platform.runLater(() -> {
                  text.appendText("Weight: " + Weight + '\n');
                  text.appendText("Height: " + Height + '\n');
                  text.appendText("bmi: " + bmi.getBMI() + '\n');
                  text.appendText("Status: " + bmi.getStatus() + '\n');

                });
              }
            }
            catch(IOException ex) {
              ex.printStackTrace();
            }
          }
        }
        
        public static void main(String[] args) {
          launch(args);
    }
}
