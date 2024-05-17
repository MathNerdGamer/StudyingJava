// Testing out using Matrix in another program.
// In this program, we compute the Fibonacci using a matrix formula.
// Setting Q = [1 1; 1 0], Q^k gives [ F[k+1] F[k]; F[k] F[k-1] ], where F[n] = nth Fibonacci number.
// This *includes* negatives!
package Matrix;

public class FibonacciMatrix {
    public static void main(String[] args) {
        int fibonacciIndex = 10;

        if (args.length > 0) {
            try {
                args[0] = args[0].replaceAll("[^0-9-]", "");
                fibonacciIndex = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("Unable to parse an integer from input. Defaulting to 10.");
            }
        }

        boolean negative = false;

        if (fibonacciIndex < 0) {
            fibonacciIndex *= -1;
            negative = true;
        }

        // Matrix [1 1; 0 1]
        Matrix initMatrix = new Matrix();
        initMatrix.set(0, 0, 1);
        initMatrix.set(0, 1, 1);
        initMatrix.set(1, 0, 1);
        initMatrix.set(1, 1, 0);

        // Starts as the identity matrix.
        Matrix fibMatrix = Matrix.identityMatrix(2);

        for (int i = 0; i < fibonacciIndex; i++) {
            fibMatrix = fibMatrix.multiply(initMatrix);
        }

        String suffix = "th";

        switch (fibonacciIndex % 10) {
            case 1:
                suffix = "st";
                break;

            case 2:
                suffix = "nd";
                break;

            case 3:
                suffix = "rd";
                break;
        }

        long result = fibMatrix.get(0, 1);

        if (negative) {
            if (fibonacciIndex % 2 == 0) {
                result *= -1;
            }

            fibonacciIndex *= -1;
        }

        System.out.println("The " + fibonacciIndex + suffix + " Fibonacci number is " + result + ".");
    }
}
