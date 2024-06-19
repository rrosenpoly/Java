import java.util.Random;
import java.util.Scanner;
public class outOfBoundException {
    public static void main(String[] args) {
        // create Scanner object
        Scanner input = new Scanner(System.in);

        // get user input for size of the array
        System.out.println("Please enter the length of an array: ");
        int n;

        // use a do while loop to ask for input until valid input
        do{
            n = input.nextInt();
            if(n<=0){
                System.out.println("Please enter the length of an array: ");
            }
        }while(n<=0);

        // Generate random numbers to be in array
        int arrSize[] = new int[n];
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            arrSize[i]= rand.nextInt(n) + 1;
        }

        // declare index
        int indexNumber;
        do{
            // get input for index
            System.out.println("Please enter an index: ");
            indexNumber = input.nextInt();

            // check if input is out of bounds
            if(indexNumber < 0 || indexNumber >= n){
                System.out.println("Index " + indexNumber + " is out of bound [0," + (n-1) + "]");
                break;
            }
            // Output the elements
            System.out.println("The array element at index " + indexNumber + " is " + arrSize[indexNumber]);

        }while(true);

    }
}
