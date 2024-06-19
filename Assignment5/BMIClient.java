import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BMIClient extends Application {

    private TextField tfHeight = new TextField();
    private TextField tfWeight = new TextField();
    private Button btSubmit = new Button("Submit");
    

    TextArea textArea = new TextArea();
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    //String host = "localhost";

    @Override
    public void start(Stage primaryStage) {
        GridPane gridpane = new GridPane();
        gridpane.setVgap(15);
        gridpane.setHgap(15);
        gridpane.add(new Label("Weight/pound: "),0,0);
        gridpane.add(tfWeight,1,0);
        gridpane.add(new Label("Height/inch: "),0,1);
        gridpane.add(tfHeight,1,1);
        gridpane.add(btSubmit,1,2);
        gridpane.add(textArea,1,3);
        
        gridpane.setAlignment(Pos.CENTER);
        tfWeight.setPrefColumnCount(15);
        tfHeight.setPrefColumnCount(15);
        textArea.setPrefColumnCount(30);

        btSubmit.setOnAction(new ButtonListener());

        Scene scene = new Scene(gridpane, 470,320);
        primaryStage.setTitle("BMIClient Extra Credit");
        primaryStage.setScene(scene);
        primaryStage.show();

       
    //try-catch block to connect to the server
    try {
      Socket socket = new Socket("localhost", 8000);
      fromServer = new DataInputStream(socket.getInputStream());
      toServer = new DataOutputStream(socket.getOutputStream());

  } catch (IOException ex) {
      textArea.appendText(ex.toString() + "\n");
      }
  }

    // class for Button
    private class ButtonListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
          try{
            // To get height
            double Height = Double.parseDouble(tfHeight.getText().trim());

            // to get weight
            Double Weight = Double.parseDouble(tfWeight.getText().trim());

            // For sending the data to the server
            toServer.writeDouble(Weight);
            toServer.writeDouble(Height);
            toServer.flush();

            double BMI = fromServer.readDouble(); // send BMI to client
            String status = fromServer.readUTF(); // send status to Client

            //Output 
            textArea.appendText("Weight: " + Weight + " pounds" + "\n");
            textArea.appendText("Hegiht: " + Weight + " inches" + "\n");
            textArea.appendText("bmi: " + BMI + "\n");
            textArea.appendText("Status: " + status + "\n");

          }
          catch (IOException ex) {
            ex.printStackTrace();
          }
        }
    }
  

    public static void main(String[] args) {
        launch(args);
    }
}
