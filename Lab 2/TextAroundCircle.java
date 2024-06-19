import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TextAroundCircle extends Application {

    @Override
    public void start(Stage primaryStage){
        
        // create a pane object
        Pane pane = new Pane();
        String[] java = "WELCOME TO JAVA".split("");
        Font font = Font.font("Arial", FontWeight.EXTRA_BOLD, 35);

        // create a Point2D object for the center of the circle
        Point2D center = new Point2D(200, 200);
        // initialize double variables
        double radius = 80;
        double angle = 180;
        double rotate = -90;

        // create a for loop to iterate through the string array
        for (int i = 0; i < java.length; i++, angle += 22, rotate += 22){
            double x = center.getX() + radius * Math.cos(Math.toRadians(angle)); // calculate x coordinate

            double y = center.getY() + radius * Math.sin(Math.toRadians(angle)); // calculate y coordinate
            
            // create a Text object
            Text text = new Text(x,y,java[i]);
            text.setRotate(rotate);
            text.setFont(font);
            pane.getChildren().add(text);
        }
        // create a scene
        Scene scene = new Scene(pane, 400, 400); // set the dimensions of the scene
        primaryStage.setTitle("Text around a circle"); // set the title
        primaryStage.setScene(scene); // set the scene
        primaryStage.show(); // Display the stage

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
