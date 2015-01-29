package edu.first.module.sensors;

import edu.first.identifiers.PositionalSensor;

/**
 * Interface representing a sensor that can detect the distance from it to the
 * nearest object. <i>Usually</i>, these are ultrasonic.
 *
 * @since June 08 13
 * @author Joel Gallant
 */
public interface RangeFinder extends PositionalSensor {

    /**
     * Returns the distance from the sensor to the nearest object in inches.
     *
     * @return distance to object
     */
    public double getInches();

    /**
     * Returns the distance from the sensor to the nearest object in
     * millimeters.
     *
     * @return distance to object
     */
    public double getMillimeters();

    /**
     * Returns the distance from the sensor to the nearest object.
     *
     * @return distance to object
     * @see #getInches()
     * @see #getMillimeters()
     */
    @Override
    public double getPosition();

    /**
     * Returns the distance from the sensor to the nearest object.
     *
     * @return distance to object
     * @see #getInches()
     * @see #getMillimeters()
     */
    @Override
    public double get();
}
