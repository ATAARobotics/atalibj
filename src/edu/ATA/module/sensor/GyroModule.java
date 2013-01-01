package edu.ATA.module.sensor;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.Gyro;

/**
 * Module designed to receive input from gyroscope sensors that are described by
 * {@link Gyro}. When enabled, can receive input from the sensor, but otherwise
 * will always return 0 if it is disabled.
 *
 * @author Joel Gallant
 */
public class GyroModule extends ForwardingGyro implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given gyro object
     * to control methods in this class.
     *
     * @param gyro actual underlying object used
     */
    public GyroModule(Gyro gyro) {
        super(gyro);
    }

    /**
     * Makes the class capable of getting values from the sensor.
     *
     * @return if module was enabled successfully
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether or not the module is currently enabled. If it is, methods
     * will give valid results from the sensor.
     *
     * @return if module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Resets the accumulation from the sensor and prevents methods from getting
     * input from the sensor. They will return 0 until module is enabled.
     *
     * @return if module is disabled successfully
     */
    public boolean disable() {
        reset();
        return !(enabled = false);
    }

    /**
     * If the module is enabled, returns the actual angle in degrees that the
     * robot is currently facing.
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
    public double getAngle() {
        return isEnabled() ? super.getAngle() : 0;
    }

    /**
     * Returns {@link GyroModule#getAngle()}.
     *
     * @return value of {@code getAngle()}
     */
    public double pidGet() {
        return getAngle();
    }
}
