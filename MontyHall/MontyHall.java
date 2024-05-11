// Does simulations of the MontyHall problem.
package MontyHall;

import java.util.Random;

public class MontyHall {
    public static void main(String[] args) {
        Random rng = new Random();

        int simulations = 10000;

        if (args.length > 0) {
            try {
                simulations = Integer.parseInt(args[1]);
            } catch (Exception e) {
                simulations = 10000;
            }
        }

        boolean[] doors = { false, false, false };

        int successes = 0;

        for (int i = 0; i < simulations; ++i) {
            int car = rng.nextInt(3); // Pick a random door.

            doors[car] = true; // Put a car behind that door.

            int pick = rng.nextInt(3); // Pick a door.

            if (!doors[pick]) {
                // If this door has a goat, then the
                // always swap strategy will win.
                ++successes;
            }

            doors[car] = false; // Reset the simulation.
        }

        double swapWinRate = 100.0 * (((double) successes) / simulations);

        System.out.printf("The \"always swap\" strategy wins %.2f%% of the time.\n", swapWinRate);
    }
}
