//Panda class
public class Panda {

    //properties
    public double weight;
    public double age;
    public static final int num_leg = 4;

    //blank constructor
    public Panda(){
        weight = 100.50;
        age = 2.50;
        //num_leg = 4;

    }

    //standard constructor
    public Panda(double weight, int num_leg, double age){
        this.weight = weight;
        //this.num_leg = num_leg;
        this.age = age;
    }

    //methods
    public void toPrint(){
        System.out.printf("weight: " + String.format("%.2f" , weight) + "\n");
        System.out.printf("age: " + String.format("%.2f" , age) + "\n");
        System.out.println("number of legs: " + num_leg);
    }

    public void climb(){
        System.out.println("I slowly climb just after a meal of bamboo shooters.\n");
    }

}
