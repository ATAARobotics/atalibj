package edu.first.module.sensors;

import edu.first.module.Module;

/**
 * The standard representation of accelerometers that plug into the analog
 * channel.
 *
 * @since June 07 13
 * @author Joel Gallant
 */
public class StandardAccelerometerModule extends Module.StandardModule implements Accelerometer {

    private final edu.wpi.first.wpilibj.Accelerometer accelerometer;

    /**
     * Constructs the module with the accelerometer object underneath this class
     * to call methods from.
     *
     * @param accelerometer the composing instance which will return values
     */
    protected StandardAccelerometerModule(edu.wpi.first.wpilibj.Accelerometer accelerometer) {
        this.accelerometer = accelerometer;
    }

    /**
     * Constructs the module with the channel on the analog breakout.
     *
     * @param channel port on breakout
     */
    public StandardAccelerometerModule(int channel) {
        this(new edu.wpi.first.wpilibj.Accelerometer(channel));
    }

    /**
     * Constructs the module with the channel on the analog breakout.
     *
     * @param channel port on breakout
     * @param slot slot on cRIO (1 = default)
     */
    public StandardAccelerometerModule(int channel, int slot) {
        this(new edu.wpi.first.wpilibj.Accelerometer(slot, channel));
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     */
    protected void disableModule() {
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * Sets the voltage that corresponds to 0 G. The zero G voltage varies by
     * accelerometer model.
     *
     * @param zero the zero G voltage
     */
    public void setZero(double zero) {
        accelerometer.setZero(zero);
    }

    /**
     * Sets the accelerometer sensitivity. The sensitivity varies by
     * accelerometer model.
     *
     * @param s the sensitivity of accelerometer in Volts per G
     */
    public void setSensitivity(double s) {
        accelerometer.setSensitivity(s);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double getAcceleration() {
        ensureEnabled();
        return accelerometer.getAcceleration();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double get() {
        ensureEnabled();
        return accelerometer.getAcceleration();
    }
}
