import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class moveBall extends Application{  

    @Override
    public void start(Stage primaryStage){

            // declare width and height for the Pane
            double width = 400 ;
            double height = 400 ;

            // create BallPane 
            BallPane ballPane = new BallPane(width / 2, height / 2, Math.min(width,height) / 25);
            
            // to display buttons
            Button btUp = new Button("Up");
            Button btDown = new Button("Down");
            Button btLeft = new Button("Left");
            Button btRight = new Button("Right");

            // to move the ball
            btUp.setOnAction(e -> ballPane.moveUp());
            btDown.setOnAction(e -> ballPane.moveDown());
            btLeft.setOnAction(e -> ballPane.moveLeft());
            btRight.setOnAction(e -> ballPane.moveRight());

            // create HBox
            HBox hBox = new HBox(btUp, btDown, btLeft, btRight);
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.BOTTOM_CENTER);
            hBox.setPadding(new Insets(10,10,10,10));

            // create a BorderPane
            BorderPane pane = new BorderPane();
            pane.setCenter(ballPane);
            pane.setBottom(hBox);

            // create a Scene and show it
            Scene scene = new Scene(pane, width, height);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Move the ball");
            primaryStage.show();
    }
            // create BallPane class
            private class BallPane extends Pane {
                private Circle circle;

                // methods
                public BallPane(double centerX, double centerY, double radius) {
                    circle = new Circle(centerX, centerY, radius);
                    getChildren().add(circle);
                }
        
                public void moveUp() {
                    if (circle.getCenterY() - circle.getRadius()  - 10 < 0) return;
                    circle.setCenterY(circle.getCenterY() - 10);
                }
                public void moveDown() {
                    if (circle.getCenterY() + circle.getRadius() + 10 > getHeight()) return;
        
                    circle.setCenterY(circle.getCenterY() + 10);
                }
                public void moveRight() {
                    if (circle.getCenterX() + circle.getRadius() + 10 > getWidth()) return;
                    circle.setCenterX(circle.getCenterX() + 10);
                }
                public void moveLeft() {
                    if (circle.getCenterX() - circle.getRadius()  - 10 < 0) return;
                    circle.setCenterX(circle.getCenterX() - 10);
        

                }
}
    public static void main(String[] args){
        launch(args);
    }
}
