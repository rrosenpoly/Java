package lab02;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;


public class RandomCard extends Application {

    @Override
    public void start(Stage primaryStage){
        
        // create arraylist for card numbers
        ArrayList<Integer> cards = getCards();

        // Hbox places nodes vertically
        HBox pane = new HBox(10);
        pane.setPadding(new Insets(5, 5, 5, 5));
        
        // access local images from local folders and add nodes to the pane
        for(int i = 0; i < 3; i++) {
            pane.getChildren().add(new ImageView(new Image("image/card/" + cards.get(i) + ".png")));
        }

        // create a scene object
        Scene scene = new Scene(pane);

        // set the scene to stage
        primaryStage.setTitle("Show 3 cards"); // set the title
        primaryStage.setScene(scene); // set the scene
        primaryStage.show(); // show the scene
    }

    // To return the arraylist of card numbers
    private ArrayList<Integer> getCards() {
        ArrayList<Integer> cards = new ArrayList<>();
       
        // create a for loop to get the cards
        for (int i = 0; i < 52; i++) {
            cards.add(i + 1);
        }

        // use Collections.shuffle to shuffle the cards
        java.util.Collections.shuffle(cards);
        return cards;
    }

    
}
