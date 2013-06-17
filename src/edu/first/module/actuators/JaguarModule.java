package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.Jaguar;

/**
 * The general purpose class that manipulates Jaguar speed controllers made by
 * Texas Instruments. Should work for all models.
 *
 * @since May 28 13
 * @author Joel Gallant
 */
public class JaguarModule extends Module.StandardModule implements SpeedController {

    private final Jaguar jaguar;

    /**
     * Constructs the module with the jaguar object underneath this class to
     * call methods from.
     *
     * @throws NullPointerException when jaguar is null
     * @param jaguar the composing instance which perform the functions
     */
    protected JaguarModule(Jaguar jaguar) {
        if (jaguar == null) {
            throw new NullPointerException("Null jaguar given");
        }
        this.jaguar = jaguar;
    }

    /**
     * Constructs the module with the port on the digital sidecar.
     *
     * @param channel port on sidecar
     */
    public JaguarModule(int channel) {
        this(new Jaguar(channel));
    }

    /**
     * Constructs the module with the port on the digital sidecar and which slot
     * the sidecar is in.
     *
     * @param channel port on sidecar
     * @param slot slot in cRIO (1 = default)
     */
    public JaguarModule(int channel, int slot) {
        this(new Jaguar(slot, channel));
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p> Stops the jaguar from moving.
     */
    protected void disableModule() {
        jaguar.disable();
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void setSpeed(double speed) {
        ensureEnabled();
        jaguar.set(speed);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void setRawSpeed(int speed) {
        ensureEnabled();
        jaguar.setRaw(speed);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double getSpeed() {
        ensureEnabled();
        return jaguar.getSpeed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public int getRawSpeed() {
        return jaguar.getRaw();
    }

    /**
     * {@inheritDoc}
     *
     * <p> This method does not need to be called on a {@code Jaguar}, but if
     * something freezes it may help relieve it.
     */
    public void update() {
        jaguar.Feed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void setRate(double rate) {
        ensureEnabled();
        jaguar.set(rate);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void set(double value) {
        ensureEnabled();
        jaguar.set(value);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double getRate() {
        ensureEnabled();
        return jaguar.getSpeed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double get() {
        ensureEnabled();
        return jaguar.getSpeed();
    }
}
