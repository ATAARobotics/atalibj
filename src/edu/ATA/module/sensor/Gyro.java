package edu.ATA.module.sensor;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * Interface that represents gyro sensors. Is meant to describe all of their
 * functionality, as well as making them a {@link PIDSource}.
 *
 * @author Joel Gallant
 */
interface Gyro extends PIDSource {

    /**
     * Return the actual angle in degrees that the robot is currently facing.
     *
     * <p> The angle is based on the current accumulator value corrected by the
     * oversampling rate, the gyro type and the A/D calibration values. The
     * angle is continuous, that is can go beyond 360 degrees. This make
     * algorithms that wouldn't want to see a discontinuity in the gyro output
     * as it sweeps past 0 on the second time around.
     *
     * @return the current heading of the robot in degrees. This heading is
     * based on integration of the returned rate from the gyro.
     */
    public double getAngle();

    /**
     * Resets the gyro to a heading of zero. This can be used if there is
     * significant drift in the gyro and it needs to be calibrated after it has
     * been running.
     */
    public void reset();

    /**
     * Get the angle of the gyro for use with PIDControllers
     *
     * @return the current angle according to the gyro
     */
    public double pidGet();
}
