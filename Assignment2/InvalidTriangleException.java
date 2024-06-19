public class InvalidTriangleException extends RuntimeException {
    private double a, b, c;

    public InvalidTriangleException(){}

    public InvalidTriangleException(double a, double b, double c) {
        super("Invalid triangle with sides: " + a + ", " + b + ", " + c);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // public getters
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
