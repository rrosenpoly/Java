import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TestDBConnectionPane extends Application { 
      // declare variables
    private Connection connection;
    private Label statusLabel = new Label();
    private TextField tfUsername = new TextField();
    private PasswordField pfPassword = new PasswordField();
    private ComboBox<String> cboURL = new ComboBox<>();
    private ComboBox<String> cboDriver = new ComboBox<>();
    
  @Override
  public void start(Stage primaryStage) {   
     GridPane gridPane = new GridPane();
    cboURL.getItems().addAll(FXCollections.observableArrayList(
        "jdbc:mysql://localhost/test",
        "jdbc:sqlite:sample.db"));
      cboURL.getSelectionModel().selectFirst();
      
      cboDriver.getItems().addAll(FXCollections.observableArrayList(
        "com.mysql.cj.jdbc.Driver", "org.sqlite.jdbc"));
        statusLabel.setText("No connection");
      cboDriver.getSelectionModel().selectFirst();
        gridPane.add(cboDriver, 1, 0);
        gridPane.add(cboURL, 1, 1);
        gridPane.add(tfUsername, 1, 2);
        gridPane.add(pfPassword, 1, 3);
        gridPane.add(new Label("Database URL"), 0, 0);
        gridPane.add(new Label("JDBC Driver"), 0, 1);
        gridPane.add(new Label("Username"), 0, 2);
        gridPane.add(new Label("Password"), 0, 3);

        // Button to connect to Database
        Button btConnectDB = new Button("Connect to DB");
    

        // Position the Buttons
        HBox hboxButton1 = new HBox(10, btConnectDB);
        hboxButton1.setAlignment(Pos.CENTER_RIGHT);

        VBox vBoxConnection = new VBox(5);
        vBoxConnection.getChildren().addAll(
          statusLabel,gridPane,hboxButton1);

        // ActionEvent for connect to DB button
        btConnectDB.setOnAction(e ->{
            connectToDB();
        });
        

        Scene scene = new Scene(vBoxConnection, 320, 160);
        primaryStage.setTitle("DB Connection");
        primaryStage.setScene(scene);
        primaryStage.show();
  }

  private void connectToDB() {
    String driver = cboDriver.getSelectionModel().getSelectedItem();
    String url = cboURL.getSelectionModel().getSelectedItem();
    String username = tfUsername.getText().trim();
    String password = pfPassword.getText().trim();

    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(url, username, password);
      statusLabel.setText("Connected to " + url);
    }
    catch (java.lang.Exception ex) {
      ex.printStackTrace();
    }
  }


  public static void main(String[] args) {
    launch(args);
  }
}
