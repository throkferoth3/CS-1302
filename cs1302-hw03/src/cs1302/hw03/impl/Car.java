package cs1302.hw03.impl;

import cs1302.hw03.contract.Drivable;

/**
 * This class represents a car. It implements
 * the {@link cs1302.hw03.contract.Drivable} interface.
 *
 */
public class Car implements Drivable {
    private double speed; // in mph
    private double maxSpeed; // in mph

    /**
     * Constructs a {@code Car} object with a specified
     * maximum speed. The maximum speed of the object
     * will default to zero if a negative value is given.
     * @param maxSpeed the maximum speed of the {@code Car}
     */
    public Car(double maxSpeed) {
        speed = 0;
        if (maxSpeed >= 0) {
            this.maxSpeed = maxSpeed;
        } // if
    } //Car

    /**
     * Speeds up the {@code Car} object a specified speed.
     *
     * @param amount the amount the {@code Car} increases speed
     * @return boolean returns true if {@code speedUp} executed
     * successfully and false if otherwise.
     */
    public boolean speedUp(double amount) {
        boolean success = false;
        if (speed + amount <= maxSpeed) {
            speed += amount;
            success = true;
        } // if
        return success;
    } // speedUp

    /**
     * Slows down the {@code Car} a specified amount.
     *
     * @param amount the amount that {@code Car} decreases speed
     * @return boolean returns true if {@code slowdown} was executed
     * successfully and false if otherwise.
     */
    public boolean slowDown(double amount) {
        boolean success = false;
        if (speed - amount >= 0) {
            speed -= amount;
            success = true;
        }
        return success;
    } // slowDown

    /**
     * Changes speed of {@code Car} to 0.
     *
     * @return boolean returns true if {@code stop} was executed
     * successfully and false if otherwise
     */
    public boolean stop() {
        boolean success = false;
        speed = 0;

        if (speed == 0) {
            success = true;
        }

        return success;
    }

    /**
     * Returns a {@code String} representation of this {@code Car}
     * in the format Car(speed: speed, maxspeed: maxSpeed).
     *
     * @return the {@code String} representation of this object.
     */
    public String toString() {
        return String.format("Car(speed: %.2f, maxspeed: %.2f)", speed, maxSpeed);
    }
} // Car
