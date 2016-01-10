package edu.first.module.sensors;

import edu.first.identifiers.Position;
import edu.first.module.Module;

/**
 * Basic module that takes input from a digital channel.
 *
 * @since June 10 13
 * @author Joel Gallant
 */
public class DigitalInput extends Module.StandardModule implements Position {

    private final edu.wpi.first.wpilibj.DigitalInput input;

    /**
     * Constructs the module with the digital input object underneath this class
     * to call methods from.
     *
     * @param input underlying object to get input from
     */
    protected DigitalInput(edu.wpi.first.wpilibj.DigitalInput input) {
        this.input = input;
    }

    /**
     * Constructs the module using the sensor's channel.
     *
     * @param channel channel on the digital sidecar
     */
    public DigitalInput(int channel) {
        this(new edu.wpi.first.wpilibj.DigitalInput(channel));
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
     * Returns whether the input is receiving a signal.
     *
     * @throws IllegalStateException when module is not enabled
     * @return if input has current running
     */
    @Override
    public boolean getPosition() {
        ensureEnabled();
        return input.get();
    }
}
