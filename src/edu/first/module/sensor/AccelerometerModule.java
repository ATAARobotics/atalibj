package edu.first.module.sensor;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 * Module that represents accelerometer sensors. When this module is disabled,
 * it will always return 0.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AccelerometerModule extends ForwardingAccelerometer implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given accelerometer
     * object to control methods in this class.
     *
     * @param accelerometer sensor to use
     */
    public AccelerometerModule(ADXL345_I2C accelerometer) {
        super(accelerometer);
    }

    /**
     * Disables the module. This prevents the switch from returning anything but
     * 0 in {@link AccelerometerModule#getAcceleration()}.
     *
     * @return if module was successfully disabled
     */
    public boolean disable() {
        return !(enabled = false);
    }

    /**
     * Enables the module. This enables
     * {@link AccelerometerModule#getAcceleration()} to function properly.
     *
     * @return if module was successfully enabled
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether or not the module has been enabled yet. If it is not
     * enabled, {@link AccelerometerModule#getAcceleration()} will not function
     * properly. (will always return 0)
     *
     * @return whether module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the acceleration in G's. Returns 0 if it is disabled.
     *
     * @param axes
     * @return acceleration measured by sensor
     */
    public double getAcceleration(ADXL345_I2C.Axes axes) {
        return isEnabled() ? super.getAcceleration(axes) : 0;
    }
}

class ForwardingAccelerometer implements Accelerometer {

    private final edu.wpi.first.wpilibj.ADXL345_I2C accelerometer;

    /**
     * Constructs the object by using composition, using the given accelerometer
     * object to control methods in this class.
     *
     * @param button actual underlying object used
     */
    ForwardingAccelerometer(ADXL345_I2C accelerometer) {
        this.accelerometer = accelerometer;
    }

    /**
     * Returns the acceleration in G's.
     *
     * @return acceleration measured by sensor
     */
    public double getAcceleration(ADXL345_I2C.Axes axes) {
        return accelerometer.getAcceleration(axes);
    }

    /**
     * Returns the acceleration for use with PID.
     *
     * @return acceleration
     */
    public double pidGet() {
        return getAcceleration(ADXL345_I2C.Axes.kX);
    }
}