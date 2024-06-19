//TestPanda class
import java.util.Scanner;

public class TestPanda {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        //create two Panda objects, one from the no-argument constructor, and the other from the standard constructor
        System.out.println("panda1 is created using the no-arg constructor.\n");
        Panda panda1 = new Panda();

        System.out.println("panda2 will be created using the standard constructor, please enter its weight, age, and number of legs: ");
        double weight = input.nextDouble();
        double age = input.nextDouble();
        //int num_leg = input.nextInt();
        Panda panda2 = new Panda(weight, Panda.num_leg, age);

        System.out.println();
        //call every method from each object
        System.out.println("panda1 toPrint:");
        panda1.toPrint();
        System.out.println("panda1 climb:");
        panda1.climb();

        System.out.println("panda2 toPrint:");
        panda2.toPrint();
        System.out.println("panda2 climb:");
        panda2.climb();

        //change every property's value using assignment statements, with values read from the user
        System.out.println("Let's change the properties of panda1, please enter its weight, age, and number of legs: ");
        panda1.weight = input.nextDouble();
        panda1.age = input.nextDouble();
        //panda1.num_leg = input.nextInt();

        System.out.println("Let's change the properties of panda2, please enter its weight, age, and number of legs: ");
        panda2.weight = input.nextDouble();
        panda2.age = input.nextDouble();
        //panda2.num_leg = input.nextInt();

        //call every method from each object one more time
        System.out.println("panda1 toPrint:");
        panda1.toPrint();
        System.out.println("panda1 climb:");
        panda1.climb();

        System.out.println("panda2 toPrint:");
        panda2.toPrint();
        System.out.println("panda2 climb:");
        panda2.climb();


    }

}
