import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.paint.*;

public class InvestmentCalculator extends Application{

    // declare variables
    private TextField tfinvestmentAmount = new TextField();
    private TextField tfnumberOfYears = new TextField();
    private TextField tfannualInterestRate = new TextField();
    private TextField tffutureValue = new TextField();
    private Button btCalculate = new Button("Calculate");

    @Override
    public void start(Stage primaryStage){
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);

    // using gridpane and label to creale a label and set the color.
    Label label1 = new Label("Investment Amount: ");
    label1.setTextFill(Color.BLUEVIOLET);
    gridPane.add(label1, 0, 0);
    gridPane.add(tfinvestmentAmount, 1, 0);

   // using gridpane and label to creale a label and set the color.
    Label label2 = new Label("Number of Years: ");
    label2.setTextFill(Color.RED);
    gridPane.add(label2, 0, 1);
    gridPane.add(tfnumberOfYears, 1, 1);

    // using gridpane and label to creale a label and set the color.
    Label label3 = new Label("Annual Interest Rate: ");
    label3.setTextFill(Color.MAROON);
    gridPane.add(label3, 0, 2);
    gridPane.add(tfannualInterestRate, 1, 2);

    // using gridpane and label to creale a label and set the color.
    Label label4 = new Label("Future Value: ");
    label4.setTextFill(Color.GREEN);
    gridPane.add(label4, 0, 3);
    gridPane.add(tffutureValue, 1, 3);
    
    // adding the Button and changing the background color.
    btCalculate.setTextFill(Color.BLUE);
    gridPane.add(btCalculate, 1, 5);

    // setting up background
    gridPane.setStyle("-fx-background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQu00NWXAMFnMVu7tQgWJWK4dMKqz0W31NoWtUrWkkH&s')");

    // changing the application icon
    Image image = new Image("https://cdn-icons-png.flaticon.com/512/3000/3000903.png");
    primaryStage.getIcons().add(image);
   
    // change the color of the TextFields
    // create a new label variables and then use the setStyle method

    

    gridPane.setAlignment(Pos.CENTER);
    tfinvestmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
    tfnumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
    tfannualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
    tffutureValue.setAlignment(Pos.BOTTOM_RIGHT);
    tffutureValue.setEditable(false);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);

    btCalculate.setOnAction(e -> calculateLoanPayment());

    // create a scene and place it in the stage

    Scene scene = new Scene(gridPane, 400, 250);
    primaryStage.setTitle("Investment Calculator");
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    // create a function to calculate loan payment
    private void calculateLoanPayment(){
        double interest = Double.parseDouble(tfinvestmentAmount.getText());
        int year = Integer.parseInt(tfnumberOfYears.getText());
        double Annual = Double.parseDouble(tfannualInterestRate.getText());

        Loan loan = new Loan(interest, year, Annual);

        tfannualInterestRate.setText(String.format("%.1f", loan.getAnnualInterestRate()));
        tffutureValue.setText(String.format("$%.2f",
        loan.getFutureValue()));
    }

    public static void main(String[] args) {
        launch(args);

    }

}

// create a class to represent a loan
class Loan {
    private double investment_amount;
    private int number_of_years;
    private double annual_interest_rate;
    private java.util.Date loanDate;

    // constructors

    public Loan(double investment_amount, int number_of_years, double annual_interest_rate){
        this.investment_amount = investment_amount;
        this.number_of_years = number_of_years;
        this.annual_interest_rate = annual_interest_rate;
        loanDate = new java.util.Date();

    }
    
    // mutators and accessors

    public double getAnnualInterestRate() {
        return annual_interest_rate;
    }
    public void setAnnualInterestRate(double annual_interest_rate) {
        this.annual_interest_rate = annual_interest_rate;
    }
    public int getNumberOfYears() {
        return number_of_years;
    }
    public void setNumberOfYears(int number_of_years) {
        this.number_of_years = number_of_years;
    }
    public double getInvestmentAmount() {
        return investment_amount;
    }
    public void setInvestmentAmount(double investment_amount) {
        this.investment_amount = investment_amount;
    }

    // formula to calculate future value
    public double getFutureValue(){
        double monthlyInterestRate = annual_interest_rate / 1200;
        double futureValue = investment_amount * Math.pow(1 + monthlyInterestRate, (number_of_years * 12));
        return futureValue;
    }

    public java.util.Date getLoanDate() {
        return loanDate;
    }

}
