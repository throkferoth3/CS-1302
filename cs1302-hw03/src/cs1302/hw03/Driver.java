package cs1302.hw03;

import cs1302.hw03.contract.Drivable;
import cs1302.hw03.impl.Car;
import cs1302.hw03.impl.Truck;

/**
 * A driver program to test the functionality of
 * {@code cs1302.hw03.impl.Car}
 * and {@code cs1302.hew03.impl.Truck}  which implement
 * {@code cs1302.hw03.contract.Drivable}.
 */
public class Driver {

    /**
     * Attempts to speedup and slowdown the {@link cs1302.hw03.impl.Car}
     * or {@link cs1302.hw03.impl.Truck} object
     * by a specified amount. If the {@link cs1302.hw03.impl.Car}
     * or {@link cs1302.hw03.impl.Truck} object is unable
     * to speedup or slowdown by the specified amount, an appropriate message is
     * printed.
     *
     * @param vehicle the truck or car to test
     * @param speedupAmount the amount to speedup the car
     * @param slowdownAmount the amount to slowdown the car
     */
    public static void test(Drivable vehicle, double speedupAmount, double slowdownAmount) {
        if (vehicle.getClass().getName().equals("cs1302.hw03.impl.Car")) {

            System.out.println(vehicle);

            if (vehicle.speedUp(speedupAmount)) {
                System.out.println("The car sped up by " + speedupAmount + " mph");
            } else {
                System.out.println("The car cannot go that fast");
            } // if

            if (vehicle.slowDown(slowdownAmount)) {
                System.out.println("The car slowed down by " + slowdownAmount + " mph");
            } else {
                System.out.println("The car is cannot slow down by that amount");
            } // if

            System.out.println(vehicle);

        } else {

            System.out.println(vehicle);

            if (vehicle.speedUp(speedupAmount)) {
                System.out.println("The truck sped up by " + speedupAmount + " mph");
            } else {
                System.out.println("The truck cannot go that fast");
            } // if

            if (vehicle.slowDown(slowdownAmount)) {
                System.out.println("The truck slowed down by " + slowdownAmount + " mph");
            } else {
                System.out.println("The truck is cannot slow down by that amount");
            } // if

            System.out.println(vehicle);
        }

    } // test

    /**
     * Main method that constructs 2 {@link cs1302.hw03.impl.Car} objects
     * and 2 {@link cs1302.hw03.impl.Truck} objects.
     * Then tests the objects
     * with {@link test} method.
     *
     * @param args the command-line arguments to the program
     */
    public static void main(String[] args) {

        Drivable fastCar = new Car(185.5);
        Drivable slowCar = new Car(75.0);
        Drivable slowTruck = new Truck(100);
        Drivable fastTruck = new Truck(200);

        System.out.println("--------------------");

        test(fastCar, 200.5, 20);
        System.out.println("--------------------");

        test(fastCar, 125, 20);
        System.out.println("--------------------");

        test(slowCar, 85, 20);
        System.out.println("--------------------");

        test(slowCar, 65, 65);
        System.out.println("--------------------");

        test(fastTruck, 200.5, 20);
        System.out.println("--------------------");

        test(fastTruck, 125, 20);
        System.out.println("--------------------");

        test(slowTruck, 85, 20);
        System.out.println("--------------------");

        test(slowTruck, 65, 65);
        System.out.println("--------------------");

    } // main
} // Driver
