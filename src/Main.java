import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Matrix 1 init
        System.out.println("-----------------------------------------------------");
        System.out.println("matrix1:");
        System.out.println("-----------------------------------------------------");
        List<Complex> list1 = List.of(new Complex(1, 2), new Complex(3, 4),
                new Complex(-1), new Complex( 2),
                new Complex(1, 1), new Complex(10, 23));
        Matrix matrix1 = new Matrix(3, 2, list1);
        System.out.print(matrix1);
        System.out.println("-----------------------------------------------------");

        // Matrix 2 init
        System.out.println("matrix2:");
        System.out.println("-----------------------------------------------------");
        List<Complex> list2 = List.of(new Complex(6), new Complex(7, 2),
                new Complex(-5, 12), new Complex( 5));
        Matrix matrix2 = new Matrix(2, 2, list2);
        System.out.print(matrix2);
        System.out.println("-----------------------------------------------------");

        // Multiplying matrices
        System.out.println("matrix1 * matrix2");
        System.out.println("-----------------------------------------------------");
        matrix1.multiply(matrix2);
        System.out.print(matrix1);
        System.out.println("-----------------------------------------------------");

        //Matrix 3 init
        System.out.println("matrix3:");
        System.out.println("-----------------------------------------------------");
        List<Complex> list3 = List.of(new Complex(1), new Complex(22, 1),
                new Complex(44, -213), new Complex(91, -12));
        Matrix matrix3 = new Matrix(2, 2, list3);
        System.out.print(matrix3);
        System.out.println("-----------------------------------------------------");

        //adding matrices
        System.out.println("matrix2 + matrix3");
        System.out.println("-----------------------------------------------------");
        matrix2.add(matrix3);
        System.out.print(matrix2);
        System.out.println("-----------------------------------------------------");

        //transponating matrix
        System.out.println("matrix3 transponated:");
        System.out.println("-----------------------------------------------------");
        matrix3.transponate();
        System.out.print(matrix3);
        System.out.println("-----------------------------------------------------");

    }
}