package edu.first.identifiers;

/**
 * The general contract for classes that can access the rate of something. Uses
 * a number as the rate.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface RateSensor extends Input {

    /**
     * Returns the current rate of the sensor. This method should return the
     * most up-to-date possible value. The units that this number is in should
     * be documented in the implementation.
     *
     * @return current rate of sensor
     */
    public double getRate();
}
