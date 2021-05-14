package cs1302.hw03.contract;

/**
 * Represents the interface for an object that is drivable.
 * Drivable objects can {@code speedUp} and {@code slowDown}.
 */
public interface Drivable {

    /**
     * Increases the speed of the object by the specified amount.
     * @param amount the specified amount in mph
     * @return true when the operation was successful; false otherwise
     */
    public boolean speedUp(double amount);

    /**
     * Decreases the speed of the object by the specified amount.
     * @param amount the specified amount in mph
     * @return true when the operation was successful; false otherwise
     */
    public boolean slowDown(double amount);

    /**
     * Decreases the speed of the object down to 0.
     *
     *@return true when operation was successful and false if otherwise
     */
    public boolean stop();

} // Drivable
