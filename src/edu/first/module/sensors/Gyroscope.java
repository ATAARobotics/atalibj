package edu.first.module.sensors;

import edu.first.identifiers.PositionalSensor;

/**
 * Gyroscope sensors that return a position.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface Gyroscope extends PositionalSensor {

    /**
     * Return the actual angle in degrees that the gyroscope is currently facing
     * relative to its starting position.
     *
     * @return angle from last reset or when robot was turned on
     */
    public double getAngle();

    /**
     * Resets the angle to zero.
     */
    public void reset();

    /**
     * Sets the sensitivity of the gyroscope for calculating the angle in
     * degrees. Units are volts / degrees / second.
     *
     * @param voltsPerDegreePerSecond sensitivity to set the gyro to
     */
    public void setSensitivity(double voltsPerDegreePerSecond);
}
