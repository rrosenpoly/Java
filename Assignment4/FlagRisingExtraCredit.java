import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FlagRisingExtraCredit extends Application {
    private double flagSpeed = 7;
    private double flagY;
    private boolean running = true;

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        ImageView imageView = new ImageView("image/us.gif");
        imageView.setX(25);
        double flagHeight = imageView.getLayoutBounds().getHeight();
        pane.getChildren().add(imageView);

        new Thread(() -> {
            try {
                while (running) {
                    if ((-flagHeight / 2 - 200) < (imageView.getY())) {
                        flagY = (imageView.getY() - flagSpeed);
                    } else {
                        flagY = (200 + flagHeight / 2);
                    }
                    Platform.runLater(() -> imageView.setY(flagY));
                    Thread.sleep(90);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();

        Scene scene = new Scene(pane, 250, 200);

        // Add key event handlers to increase/decrease flag speed
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                flagSpeed = Math.min(flagSpeed + 1, 20);
            } else if (e.getCode() == KeyCode.DOWN) {
                flagSpeed = Math.max(flagSpeed - 1, 1);
            }
        });

        primaryStage.setTitle("FlagRisingAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        running = false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
