package edu.first.module.sensors;

import edu.first.identifiers.PositionalSensor;
import edu.first.identifiers.RateSensor;

/**
 * General interface to represent encoders. Encoders should be able to measure
 * distance and rate.
 *
 * @since June 03 13
 * @author Joel Gallant
 */
public interface Encoder extends PositionalSensor, RateSensor {

    /**
     * Resets the distance of the encoder.
     */
    public void reset();

    /**
     * Returns an un-scaled distance value.
     *
     * @return unchanged distance value
     */
    public int getRaw();

    /**
     * Returns the distance that the encoder has measured.
     *
     * @return distance measured
     */
    @Override
    public double getPosition();

    /**
     * Returns how fast encoder pulses are happening.
     *
     * @return current speed of pulses
     */
    @Override
    public double getRate();

    /**
     * Returns a value indicative of the status of the encoder. Should be
     * implementation-specific.
     *
     * @return value of encoder
     */
    @Override
    public double get();
}
