package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.Talon;

/**
 * The general purpose class that manipulates Talon speed controllers made by
 * IFI / CTRE. Should work for all models.
 *
 * @since May 28 13
 * @author Joel Gallant
 */
public class TalonModule extends Module.StandardModule implements SpeedController {

    private final Talon talon;

    /**
     * Constructs the module with the talon object underneath this class to call
     * methods from.
     *
     * @param talon the composing instance which perform the functions
     */
    protected TalonModule(Talon talon) {
        if (talon == null) {
            throw new NullPointerException("Null talon given");
        }
        this.talon = talon;
    }

    /**
     * Constructs the module with the port on the digital sidecar.
     *
     * @param channel port on sidecar
     */
    public TalonModule(int channel) {
        this(new Talon(channel));
    }

    /**
     * Constructs the module with the port on the digital sidecar and which slot
     * the sidecar is in.
     *
     * @param channel port on sidecar
     * @param slot slot in cRIO (1 = default)
     */
    public TalonModule(int channel, int slot) {
        this(new Talon(slot, channel));
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p> Stops the talon from moving.
     */
    protected void disableModule() {
        talon.disable();
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
        talon.set(speed);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void setRawSpeed(int speed) {
        ensureEnabled();
        talon.setRaw(speed);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double getSpeed() {
        ensureEnabled();
        return talon.getSpeed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public int getRawSpeed() {
        ensureEnabled();
        return talon.getRaw();
    }

    /**
     * {@inheritDoc}
     *
     * <p> This method does not need to be called on a {@code Talon}, but if
     * something freezes it may help relieve it.
     */
    public void update() {
        talon.Feed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void setRate(double rate) {
        ensureEnabled();
        talon.set(rate);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void set(double value) {
        ensureEnabled();
        talon.set(value);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double getRate() {
        ensureEnabled();
        return talon.getSpeed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double get() {
        ensureEnabled();
        return talon.getSpeed();
    }
}
