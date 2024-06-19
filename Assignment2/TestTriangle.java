
public class TestTriangle {
    public static void main(String[] args) {
        Triangle c1 = null, c2 = null, c3 = null;
        try {
            c1 = new Triangle(3, 4, 5);
            c2 = new Triangle(1, 2, 3);
            c3 = new Triangle(10, 4, 5);
        } catch (InvalidTriangleException ex) {
            System.out.println(ex);
        }
            System.out.println("Created triangle with sides: " + c1.a + ", " + c1.b + ", " + c1.c);
        }
};
