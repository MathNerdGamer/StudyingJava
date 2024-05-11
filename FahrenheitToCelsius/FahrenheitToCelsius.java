// Converts FahrenheitToCelsius.
package FahrenheitToCelsius;

import java.util.Scanner;

public class FahrenheitToCelsius {
    public static void main(String[] args) {
        double fahrenheit = 100.0;
        if (args.length > 0) {
            fahrenheit = Double.parseDouble(args[0]);
        } else {
            Scanner input = new Scanner(System.in);
            boolean done = false;

            while (!done) {
                try {
                    System.out.print("Enter a temperature in Fahrenheit: ");
                    fahrenheit = input.nextDouble();
                    done = true;
                } catch (Exception e) {
                    System.out.println("Enter a valid number.");
                    input.nextLine();
                }
            }

            input.close();
        }

        // Print out the temperatures up to two places after the decimal point.
        System.out.printf("%.2f F is %.2f C.\n", fahrenheit, (5.0 / 9) * (fahrenheit - 32));
    }
}
