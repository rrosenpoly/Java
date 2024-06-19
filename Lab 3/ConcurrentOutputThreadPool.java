import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.control.*;


public class ConcurrentOutputThreadPool  extends Application{
    private TextArea outputArea = new TextArea();
    @Override
    public void start(Stage primaryStage){
    Runnable printA = new PrintChar('a', 100);
    Runnable printB = new PrintChar('b', 100);
    Runnable print100 = new PrintNum(100);

    ExecutorService executor = Executors.newFixedThreadPool(3);

    executor.submit(printA);
    executor.submit(printB);
    executor.submit(print100);

    executor.shutdown();

    // set Wraptext
    outputArea.setWrapText(true);
    
    // Create a scene
    Scene scene = new Scene(outputArea, 400, 160);
    primaryStage.setTitle("Extra Concurrent Output");
    primaryStage.setScene(scene);
    primaryStage.show();
}

class PrintChar implements Runnable {
  private char charToPrint;
  private int times;

  public PrintChar(char c, int t) {
    charToPrint = c;
    times = t;
  }

  @Override
  public void run() {
    for (int i = 0; i < times; i++) synchronized(outputArea) {
      outputArea.appendText("" + charToPrint);
    }
  }
}

class PrintNum implements Runnable {
  private int lastNum;

  public PrintNum(int n) {
    lastNum = n;
  }

  @Override
  public void run() {
    for (int i = 1; i <= lastNum; i++) synchronized(outputArea) {
      outputArea.appendText(" " + i);
    }
  }
}
public static void main(String[] args){
    launch(args);
    }
}
