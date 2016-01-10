package edu.first.module.sensors;

import edu.first.identifiers.Input;
import edu.first.module.Module;

/**
 * Basic module that takes input from an analog input.
 *
 * @since June 10 13
 * @author Joel Gallant
 */
public class AnalogInput extends Module.StandardModule implements Input {

    private final edu.wpi.first.wpilibj.AnalogInput input;

    /**
     * Constructs the module with the analog channel object underneath this
     * class to call methods from.
     *
     * @param input underlying object to get input from
     */
    protected AnalogInput(edu.wpi.first.wpilibj.AnalogInput input) {
        this.input = input;
    }

    /**
     * Constructs the module using the sensor's channel.
     *
     * @param channel channel on the analog breakout
     */
    public AnalogInput(int channel) {
        this(new edu.wpi.first.wpilibj.AnalogInput(channel));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void disableModule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    /**
     * Returns the voltage of the input. Using averaging will cause this value
     * to be more stable, but it will update more slowly.
     *
     * @throws IllegalStateException when module is not enabled
     * @return voltage measured by input
     */
    public double getAverage() {
        ensureEnabled();
        return input.getAverageVoltage();
    }

    /**
     * Returns the voltage of the input.
     *
     * @throws IllegalStateException when module is not enabled
     * @return voltage measured by input
     */
    @Override
    public double get() {
        ensureEnabled();
        return input.getVoltage();
    }
}
