// Testing out using Matrix in another program.
// In this program, we compute the Fibonacci using a matrix formula.
// Setting Q = [1 1; 1 0], Q^k gives [ F[k+1] F[k]; F[k] F[k-1] ], where F[n] = nth Fibonacci number.
// This *includes* negatives!
package Matrix;

public class FibonacciMatrix {
    public static void main(String[] args) {
        int N = 10;

        if (args.length > 0) {
            try {
                args[0] = args[0].replaceAll("[^0-9-]", "");
                N = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("Unable to parse an integer from input. Defaulting to 10.");
            }
        }

        boolean negative = false;

        if (N < 0) {
            N *= -1;
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

        // Multiply by the Fibonacci matrix over and over.
        for (int i = 0; i < N; i++) {
            fibMatrix = fibMatrix.multiply(initMatrix);
        }

        // Get the correct suffix for the ordinal value.
        String suffix = "th";

        switch (N % 10) {
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
            if (N % 2 == 0) {
                result *= -1;
            }

            N *= -1;
        }

        System.out.println("The " + N + suffix + " Fibonacci number is " + result + ".");
    }
}
