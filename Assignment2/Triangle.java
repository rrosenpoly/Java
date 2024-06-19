
public class Triangle extends GeometricObject{

    // declare variables
    double a, b, c;

    // Constructor
    public Triangle(double a, double b, double c) throws InvalidTriangleException{
        // TODO:
        // if invalid throw InvalidTriangleException
        // else assign the tree sides respectively
            if (a + b <= c || a + c <= b || b + c <= a){
            throw new InvalidTriangleException(a, b, c);
            }
                this.a = a;
                this.b = b;
                this.c = c;
            }

};
