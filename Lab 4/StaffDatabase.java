import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;


public class StaffDatabase extends Application {

    // declare variables
    private Statement stmt;
    private Label statusLabel = new Label();
    private TextField idField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField miField = new TextField();
    private TextField addressField = new TextField();
    private TextField cityField = new TextField();
    private TextField stateField = new TextField();
    private TextField telephoneField = new TextField();
    private TextField emailField = new TextField();
    private Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // calling function to connect to Database server
        initializeDB();
    
        // Set up layout of flowPane
        FlowPane flowPane1 = new FlowPane(6, 6);
        FlowPane flowPane2 = new FlowPane(6, 6);
        FlowPane flowPane3 = new FlowPane(6, 6);
        FlowPane flowPane4 = new FlowPane(6, 6);
        FlowPane flowPane5 = new FlowPane(6, 6);
       
        // creating label objects and initializing
        Label label1 = new Label("ID");
        Label label2 = new Label("Last Name");
        Label label3 = new Label("First Name");
        Label label4 = new Label("MI");
        Label label5 = new Label("Address");
        Label label6 = new Label("City");
        Label label7 = new Label("State");
        Label label8 = new Label("Telephone");
        Label label9 = new Label("Email");

        // adding labels and textFields to the flow pane
        flowPane1.getChildren().addAll( label1, idField); // to place the label and text fields into the flow pane
        flowPane2.getChildren().addAll(label2, lastNameField, label3, firstNameField, label4, miField); // same as above
        flowPane3.getChildren().addAll(label5,addressField); // same as above
        flowPane4.getChildren().addAll(label6, cityField, label7, stateField); // same as above
        flowPane5.getChildren().addAll(label8, telephoneField, label9, emailField); // same as abovew

        // set preferred width
        firstNameField.setPrefWidth(100); // specify the preferred width for first name text field
        lastNameField.setPrefWidth(100); // specify the preferred width for last name text field
        miField.setPrefWidth(40); // specify the preferred width for mi text field
        addressField.setPrefWidth(300); // specify prefered width for address text field
    
        // Create Buttons Object
        HBox buttonBox = new HBox();
        Button viewButton = new Button("View");
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");
    
        // Add Buttons to Pane
        buttonBox.getChildren().addAll(viewButton, insertButton, updateButton, clearButton);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
    
        // Add status label and button box to VBox
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(statusLabel, flowPane1,flowPane2, flowPane3, flowPane4, flowPane5, buttonBox);
    
        // Set up scene and show window
        Scene scene = new Scene(vbox,410,250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Staff Database");
        primaryStage.show();

        // function to view records in the database
        viewButton.setOnAction(e -> {
            try {
                String id = idField.getText();
                String query = "SELECT * FROM staff WHERE id = " + id;
                ResultSet resultSet = stmt.executeQuery(query);
        
                if (resultSet.next()) {
                    lastNameField.setText(resultSet.getString("lastName")); // sets last name to to its corresponding textField
                    firstNameField.setText(resultSet.getString("firstName")); // sets first name to its corresponding textField
                    miField.setText(resultSet.getString("mi")); // sets mi to its corresponding textField
                    addressField.setText(resultSet.getString("address")); // sets address to its corresponding textField
                    cityField.setText(resultSet.getString("city")); // sets city to its corresponding textField
                    stateField.setText(resultSet.getString("state")); // sets State to its corresponding textField
                    telephoneField.setText(resultSet.getString("telephone")); // sets Telephone number to its corresponding textField
                    emailField.setText(resultSet.getString("email")); // sets email to its corresponding textField
                    statusLabel.setText("Record with Id " + id + " found"); // to specify records found
                } else {
                    //clearFields();
                    statusLabel.setText("Record with Id " + id + " not found"); // to specify records not found
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // function to insert a new record
        insertButton.setOnAction(e -> {
            try {
                // Get the values from the text fields
                String id = idField.getText(); // get string for id
                String lastName = lastNameField.getText(); // get string for last name
                String firstName = firstNameField.getText(); // get string for first name
                String mi = miField.getText(); // get string for mi
                String address = addressField.getText(); // get string for address
                String city = cityField.getText(); // get string for city
                String state = stateField.getText(); // get string for state
                String telephone = telephoneField.getText(); // get value for telephone
                String email = emailField.getText(); // get string for email 
        
                // Building the SQL querry usinf info from the database
                String query = "INSERT INTO staff VALUES ('" + id + "', '" + lastName + "', '" + firstName + "', '" + mi + "', '" + address + "', '" + city + "', '" + state + "', '" + telephone + "', '" + email + "')";
        
                // To execute the querry
                int insertedValue = stmt.executeUpdate(query);
        
                // Check if any rows were affected
                if (insertedValue > 0) {
                    statusLabel.setText("Record Inserted"); // update status label
                } else {
                    statusLabel.setText("Insert Failed"); // update status label if Insert is unsuccessful
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    
        // function update records in the database
        updateButton.setOnAction(e -> {
            try {
                // to retrieve the records
                String id = idField.getText();
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                String mi = miField.getText();
                String address = addressField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String telephone = telephoneField.getText();
                String email = emailField.getText();

                // Updating multiple records using single statement
                String query = "UPDATE staff SET lastName='" + lastName + "', firstName='" + firstName + "', mi='" + mi + "', address='" + address + "', city='" + city + "', state='" + state + "', telephone='" + telephone + "', email='" + email + "' WHERE id=" + id;
        
                int result = stmt.executeUpdate(query);  // to update query
                if (result == 1) {
                    statusLabel.setText("Record updated"); // update status label
                } else {
                    statusLabel.setText("Record with Id " + id + " is not updated"); // Of query is unable to update
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });        
        

        // function to clear Fields
        clearButton.setOnAction (e -> {
            clearFields(); // calls clearFields() meethod to clear all fields
        });

    }
    // function to connect to Database
    private void initializeDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/lab4", "root", "Eugene97"); // Credetials to connect to Database

            stmt = connection.createStatement(); // needed for creating statement later in program

            // Set the text of the status label to "Database connected"
            statusLabel.setText("Database connected"); // to show status of application
        
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // method to clear fields
    private void clearFields() {
        idField.clear();
        lastNameField.clear();
        firstNameField.clear();
        miField.clear();
        addressField.clear();
        cityField.clear();
        stateField.clear();
        telephoneField.clear();
        emailField.clear();
        statusLabel.setText("");
    }
    
        // to execute Application
        public static void main(String[] args) {
            launch(args);
          }
        }

