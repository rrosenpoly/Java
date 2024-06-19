import java.util.Scanner;

public class AnnualTax {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your income and age:");
        double income = input.nextDouble();
        double age = input.nextInt();

        // declare variables and initialize
        double taxRate, taxAmount, seniorDiscountPercentageRate = 0, seniorTaxAmount;

        // calculate tax
        if (income <= 50000) {
            taxRate = 0;
            taxAmount = 0.0;
        } else if (income <= 100000) {
            taxRate = 7;
            taxAmount = income * taxRate;
        } else {
            taxRate = 9;
            taxAmount = income * taxRate;
        }
        // calculate tax for senior discount
        if (taxRate > 0 && age >= 60) {
            seniorDiscountPercentageRate = taxRate - 3;
            seniorTaxAmount = income * seniorDiscountPercentageRate;
            seniorTaxAmount /= 100;
            System.out.println("Your tax amount is: " + seniorTaxAmount);
            System.out.println("You are qualified for senior discount. Your senior discount percentage rate is: " + String.format("%.2f" , seniorDiscountPercentageRate) + " %");

        } else {
            // Output results
            taxAmount /= 100;
            System.out.println("Your tax amount is: " + String.format("%.2f", taxAmount));
            System.out.println("Your tax rate is: " + String.format("%.2f" , taxRate) + " %");
        }
    }
}
