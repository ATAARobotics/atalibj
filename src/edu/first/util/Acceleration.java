package edu.first.util;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

/**
 * Singleton instance of the built in roboRIO accelerometer.
 *
 * @since Feb 4 2015
 * @author Joel Gallant
 */
public final class Acceleration {

    private static final BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();

    /**
     * Returns X acceleration.
     *
     * @return the acceleration of the RoboRIO along the X axis in g-forces
     */
    public static double getX() {
        return accelerometer.getX();
    }

    /**
     * Returns Y acceleration.
     *
     * @return the acceleration of the RoboRIO along the Y axis in g-forces
     */
    public static double getY() {
        return accelerometer.getY();
    }

    /**
     * Returns Z acceleration.
     *
     * @return the acceleration of the RoboRIO along the Z axis in g-forces
     */
    public static double getZ() {
        return accelerometer.getZ();
    }

    /**
     * Sets the measuring range of an accelerometer.
     *
     * @param range the maximum acceleration, positive or negative, that the
     * accelerometer will measure
     */
    public static void setRange(Range range) {
        accelerometer.setRange(range);
    }
}
