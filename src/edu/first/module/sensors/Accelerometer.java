package edu.first.module.sensors;

import edu.first.identifiers.Input;

/**
 * General interface for accelerometer sensors.
 *
 * @since June 07 13
 * @author Joel Gallant
 */
public interface Accelerometer extends Input {

    /**
     * Returns the current acceleration as measured by the accelerometer.
     *
     * @return acceleration in G's
     */
    public double getAcceleration();

    /**
     * Returns the current acceleration as measured by the accelerometer.
     *
     * @return acceleration in G's
     * @see #getAcceleration()
     */
    @Override
    public double get();
}
