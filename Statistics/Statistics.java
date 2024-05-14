// Calculates a running mean and variance of some doubles in a way that avoids accumulating floating point error.
// Prints out the calculated sample mean, variance, and deviation.
// Uses the algorithm described in Knuth's The Art of Computer Programming, Volume 2, 3rd edition (Page 232).
// I index from 0 instead
package Statistics;

import java.util.Scanner;
import java.lang.Math;

public class Statistics {
    public static void main(String[] args) {
        double mean = 0.0; // M[0] = 0
        double variance = 0.0; // S[0] = 0
        long size = args.length;

        if (size > 0) {
            for (int k = 0; k < size; k++) {
                try {
                    args[k] = args[k].replaceAll("[^0-9.-]", "");

                    double current = Double.parseDouble(args[k]);
                    double previousMean = mean;

                    mean += (current - mean) / (k + 1); // M[k] = M[k-1] + (x[k] - M[k-1]) / k for k >= 1.

                    if (k > 0) {
                        // S[k] = S[k-1] + (x[k] - M[k-1]) * (x[k] - M[k]) for k >= 1.
                        variance += (current - previousMean) * (current - mean);
                    }
                } catch (Exception e) {
                    size--;
                }
            }

            if (size > 1) {
                variance /= size - 1;
            }
        } else {
            Scanner input = new Scanner(System.in);

            while (true) {
                System.out.print("Enter a number (type `exit` to stop): ");
                try {
                    double current = input.nextDouble();
                    double previousMean = mean;
                    mean += (current - mean) / (size + 1);

                    if (size != 0) {
                        variance += (current - previousMean) * (current - mean);
                    }

                    size++;
                } catch (Exception e) {
                    if (size > 1) {
                        variance /= size - 1;
                    }
                    break;
                }
            }
            input.close();
        }

        if (size != 0) {
            System.out.printf("The sample mean is %.6f.\n", mean);
            System.out.printf("The sample variance is %.6f.\n", variance);
            System.out.printf("The sample deviation is %.6f.\n", Math.sqrt(variance));
        } else {
            System.out.println("The mean and variance of no numbers is undefined.");
        }

    }
}
