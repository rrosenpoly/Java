import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ConcurrentOutput extends Application {

    private TextArea outputArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {  

        Runnable printA = new PrintChar('a', 100);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(100);

        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        thread3.start(); // Start the thread that prints numbers first
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the thread that prints b's
        thread2.start();

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the thread that prints a's
        thread1.start();

        // set WrapText
        outputArea.setWrapText(true);

        // set properties of stackpane
        Scene scene = new Scene(outputArea, 400, 160);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Concurrent Output");
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
        for (int i = 0; i < times; i++) synchronized(outputArea){
            outputArea.appendText(""+charToPrint);
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
public static void main(String[] args) {
    launch(args);
    }
}
