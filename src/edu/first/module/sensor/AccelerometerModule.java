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
     * @param button actual underlying object used
     */
    public AccelerometerModule(ADXL345_I2C accelerometer, int filterLength) {
        super(accelerometer, filterLength);
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
     * @return acceleration measured by sensor
     */
    public double getAcceleration(ADXL345_I2C.Axes axes) {
        return isEnabled() ? super.getAcceleration(axes) : 0;
    }
}

class ForwardingAccelerometer implements Accelerometer {

    private final edu.wpi.first.wpilibj.ADXL345_I2C accelerometer;
    private final int filterLength;
    private final double[] sum;
    private int current = 0;

    /**
     * Constructs the object by using composition, using the given accelerometer
     * object to control methods in this class.
     *
     * @param button actual underlying object used
     */
    ForwardingAccelerometer(ADXL345_I2C accelerometer, int filterLength) {
        this.accelerometer = accelerometer;
        this.filterLength = filterLength;
        this.sum = new double[filterLength];
    }

    /**
     * Returns the acceleration in G's.
     *
     * @return acceleration measured by sensor
     */
    public double getAcceleration(ADXL345_I2C.Axes axes) {
        if (current >= sum.length - 1) {
            current = 0;
        }
        sum[++current] = accelerometer.getAcceleration(axes);
        double s = 0;
        double l = 0;
        for (int x = 0; x < sum.length; x++) {
            if (sum[x] != 0) {
                s += sum[x];
                l++;
            }
        }
        return s / l;
    }

    /**
     * Returns the acceleration for use with PID.
     *
     * @return acceleration
     */
    public double pidGet() {
        return accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
    }
}