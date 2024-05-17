// Calculates the Euclidean distance between two vectors.
package EuclideanDistance;

public class EuclideanDistance {
    public static void main(String[] args) {
        double[] a = { 1.0, 2.0, 3.0, 1.0 };
        double[] b = { 0.1, 0.2, 0.3, 1.0 };

        System.out.printf("The distance is %.2f.\n", distance(a, b));

        double[] badA = { 1, 2, 3 };
        double[] badB = { 4, 5 };

        try {
            System.out.printf("The distance is %.2f.\n", distance(badA, badB));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static double distance(double[] a, double[] b) {
        double distance = 0.0;

        if (a.length != b.length) {
            throw new IllegalArgumentException(
                    "Incompatible vectors of lengths " + a.length + " and " + b.length + ".");
        }

        for (int i = 0; i < a.length; i++) {
            double diff = a[i] - b[i];
            distance += diff * diff;
        }

        return Math.sqrt(distance);
    }
}
