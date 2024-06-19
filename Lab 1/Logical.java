import java.util.Scanner;

public class Logical {
    public static void main(String[] args) {

        // create Scanner object
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter three integers: ");

        // get input
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();

        // determines
        boolean divisibleByBoth = (a%b==0) && (a%c==0);
        boolean divisibleByEither = (a%b==0) || (a%c==0);
        boolean divisibleByEitherButNotBoth = (a%b==0) ^ (a%c==0);

        // Output
        System.out.println("Is " + a + " divisible by " + b + " and " + c + "? " + divisibleByBoth);
        System.out.println("Is " + a + " divisible by " + b + " or " + c + "? " + divisibleByEither);
        System.out.println("Is " + a + " divisible by " + b + " or " + c + ", but not both? " + divisibleByEitherButNotBoth);
    }

}
