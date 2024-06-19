import java.util.Scanner;
public class PrimeSum {
    //Judge if n is a prime number and return the Boolean result to caller
    public static boolean bePrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    //return the sum of all prime numbers less than m
    public static int primeSum(int m) {
        // for loop to traverse all integers from 2 to m
        // Call bePrime() in the loop body, preferably using an if statement

        int sum = 0;
        for (int i = 2; i <= m; i++) {
            if (bePrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // create a scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter an integer no less than 2: ");

        // get input and declare variable result
        int input = scanner.nextInt();
        int result = primeSum(input);

        // Output
        System.out.println("The sum of all prime numbers between 2 and " + input + " is " + result);
    }
}
