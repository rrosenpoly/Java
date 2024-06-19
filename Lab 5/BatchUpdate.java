import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class BatchUpdate extends Application{

    // declare variables
    private Connection connection;
    private Label statusLabel = new Label();
    
    private TextArea textArea = new TextArea();
    private DBConnectionPane dbcPane = new DBConnectionPane();

    @Override
    public void start(Stage primaryStage){
    

    // Create Buttons Objects
    HBox buttonBox1 = new HBox();
    HBox buttonBox2 = new HBox();
    HBox buttonBox3 = new HBox();
    
    // initializing buttons objects
    Button btConnectToDatabase = new Button("Connect to Database");
    Button btBatchUpdate = new Button("Batch Update");
    Button btNonBatchUpdate = new Button("Non Batch-Update");

    // Add Batch Update and Non Batch Update buttons at the bottom of the application
    buttonBox1.getChildren().addAll(btBatchUpdate, btNonBatchUpdate);
    buttonBox1.setSpacing(10);
    buttonBox1.setAlignment(Pos.BOTTOM_CENTER);

    // add Connect to Database button on the top right with text Box
    buttonBox2.getChildren().addAll(statusLabel);
    buttonBox2.setAlignment(Pos.TOP_LEFT);

    buttonBox3.getChildren().add(btConnectToDatabase);
    buttonBox3.setAlignment(Pos.TOP_RIGHT);

    // create the text Area for output
    BorderPane borderPane  = new BorderPane();
    borderPane.setCenter(textArea);
    

    // Add buttons to Vbox
    VBox vbox1 = new VBox(10);
    vbox1.getChildren().addAll(buttonBox2, buttonBox3, borderPane, buttonBox1);

    // Set up scene and show the application
    Scene scene1 = new Scene(vbox1,380,255);
    primaryStage.setScene(scene1);
    primaryStage.setTitle("Batch Update vs. Non-Batch Update");
    primaryStage.show();

    // setting up second stage with close dialog button
    Stage stage2 = new Stage();
    VBox vbox = new VBox(10);
    Button btClose = new Button("Close Dialog");
    vbox.getChildren().addAll(dbcPane, btClose);
    stage2.setTitle("Connect to Database");
    stage2.setScene(new Scene(vbox,320,200));
    
    // ActionEvent for Connect to DataBase Button
    btConnectToDatabase.setOnAction(e -> {
        // SecondScene();
        stage2.show();
    });

    // ActionEvent for Close Button
    btClose.setOnAction(e->{
      connection = dbcPane.getConnection();
      System.out.println(connection); // check connection
      stage2.close();
    });

    // ActionEvent for Batch Update Button
    btBatchUpdate.setOnAction(e ->{
      Thread thread1 = new Thread(new Runnable() { // declare thread as Runnable
        @Override
        public void run() {
           try{
        Statement statement = connection.createStatement();
        statement.addBatch("drop table if exists TBBATCH");
        statement.addBatch("create table TBBATCH(num1 double, num2 double, num3 double)");
        
        long startTime = System.currentTimeMillis();
        for(int i = 0; i<5000; i++){
           statement.addBatch("INSERT INTO TBBATCH VALUES(" + Math.random() * 1000 + ", " + Math.random() * 100 + ", " + Math.random() * 10 + ")");
          
        }
        statement.executeBatch();
        
  
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        textArea.appendText("Batch update completed\n");
        textArea.appendText("The elapsed time is " + totalTime +"\n");
        Platform.runLater(()->statusLabel.setText("Batch Update succeeded"));
    
      } catch (SQLException ex){
        ex.printStackTrace();
        }
      }
    });
    thread1.start(); // run the thread
     
    
    });

    // ActionEvent for Non-Batch Update Button
    btNonBatchUpdate.setOnAction(e -> {
      Thread thread2 = new Thread(new Runnable() { // declare thread as runnable
        @Override
        public void run() {
           try{
        Statement statement = connection.createStatement();
        statement.addBatch("drop table if exists TBNONBATCH");
        statement.addBatch("create table TBNONBATCH(num1 double, num2 double, num3 double)");
        
        long startTime = System.currentTimeMillis();
        for(int i = 0; i<5000; i++){
           statement.addBatch("INSERT INTO TBNONBATCH VALUES(" + (Math.random() * 1000 + 1000) + ", " + Math.random() * 100 + ", " + Math.random() * 10 + ")");
          
        }
        statement.executeBatch();
        
  
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        textArea.appendText("Non-batch update completed\n");
        textArea.appendText("The elapsed time is " + totalTime + "\n");
        Platform.runLater(()->statusLabel.setText("Non-batch Update succeeded"));
    
      } catch (SQLException ex){
        ex.printStackTrace();
        }
      }
    });
    thread2.start(); // run the thread

    });
}
    // to run application
    public static void main(String[] args) {
            launch(args);
          }
}
