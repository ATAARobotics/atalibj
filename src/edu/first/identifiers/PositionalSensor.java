package edu.first.identifiers;

/**
 * The general contract for classes that can access the position of something.
 * Uses a number as the position.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface PositionalSensor extends Input {

    /**
     * Returns the current position of the sensor. This method should return the
     * most up-to-date possible value. The units that this number is in should
     * be documented in.
     *
     * @return current position of sensor
     */
    public double getPosition();
}
