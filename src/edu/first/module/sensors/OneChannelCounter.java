package edu.first.module.sensors;

import edu.first.identifiers.PositionalSensor;
import edu.first.identifiers.RateSensor;
import edu.first.module.Module;
import edu.wpi.first.wpilibj.Counter;

/**
 * Basic one-channel counter that measures pulses in a digital channel to give a
 * rate and position. This is typically used for once-per-rev sensors like
 * encoders or hall effect sensors.
 *
 * @since June 10 13
 * @author Joel Gallant
 */
public class OneChannelCounter extends Module.StandardModule implements RateSensor, PositionalSensor {

    private final Counter counter;

    /**
     * Constructs the module with the counter object underneath this class to
     * call methods from.
     *
     * @param counter the composing instance which will return values
     */
    protected OneChannelCounter(Counter counter) {
        this.counter = counter;
    }

    /**
     * Constructs the counter with the channel it is on.
     *
     * @param channel channel on the digital sidecar
     */
    public OneChannelCounter(int channel) {
        this(new Counter(channel));
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
     * Resets the count available in {@link #getPosition()}.
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void reset() {
        ensureEnabled();
        counter.reset();
    }

    /**
     * Returns the current count of the sensor. This is indicative of how many
     * times a signal has been turned on (from an off position). On a
     * once-per-rev sensor, this is the total revolutions since the last
     * {@link #reset()}.
     *
     * @throws IllegalStateException when module is not enabled
     * @return how many rising edges have been counted
     */
    @Override
    public double getPosition() {
        ensureEnabled();
        return counter.get();
    }

    /**
     * Returns the time between rising edges in seconds.
     *
     * @throws IllegalStateException when module is not enabled
     * @return how long between pulses
     */
    public double getPeriod() {
        ensureEnabled();
        return counter.getPeriod();
    }

    /**
     * Returns the pulses per minute of the sensor.
     *
     * @throws IllegalStateException when module is not enabled
     * @return current rate of pulses
     */
    @Override
    public double getRate() {
        ensureEnabled();
        return 60 / counter.getPeriod();
    }

    /**
     * Returns the pulses per minute of the sensor.
     *
     * @throws IllegalStateException when module is not enabled
     * @return current rate of pulses
     * @see #getRate()
     */
    @Override
    public double get() {
        ensureEnabled();
        return 60 / counter.getPeriod();
    }
}
