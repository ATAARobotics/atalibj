package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.Victor;

/**
 * The general purpose class that manipulates Victor speed controllers made by
 * IFI. Should work for all models.
 *
 * @since May 28 13
 * @author Joel Gallant
 */
public class VictorModule extends Module.StartardModule implements SpeedController {

    private final Victor victor;

    /**
     * Constructs the module with the victor object underneath this class to
     * call methods from.
     *
     * @param victor the composing instance which perform the functions
     */
    public VictorModule(Victor victor) {
        if (victor == null) {
            throw new NullPointerException("Null victor given");
        }
        this.victor = victor;
    }

    /**
     * Constructs the module with the port on the digital sidecar.
     *
     * @param channel port on sidecar
     */
    public VictorModule(int channel) {
        this(new Victor(channel));
    }

    /**
     * Constructs the module with the port on the digital sidecar and which slot
     * the sidecar is in.
     *
     * @param channel port on sidecar
     * @param slot slot in cRIO (1 = default)
     */
    public VictorModule(int channel, int slot) {
        this(new Victor(slot, channel));
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p> Stops the victor from moving.
     */
    protected void disableModule() {
        victor.disable();
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
        victor.set(speed);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public void setRawSpeed(int speed) {
        ensureEnabled();
        victor.setRaw(speed);
    }

    /**
     * {@inheritDoc}
     */
    public double getSpeed() {
        return victor.getSpeed();
    }

    /**
     * {@inheritDoc}
     */
    public int getRawSpeed() {
        return victor.getRaw();
    }

    /**
     * {@inheritDoc}
     *
     * <p> This method does not need to be called on a {@code Victor}, but if
     * something freezes it may help relieve it.
     */
    public void update() {
        victor.Feed();
    }

    /**
     * {@inheritDoc}
     */
    public void setRate(double rate) {
        setSpeed(rate);
    }

    /**
     * {@inheritDoc}
     */
    public void set(double value) {
        setSpeed(value);
    }

    /**
     * {@inheritDoc}
     */
    public double getRate() {
        return getSpeed();
    }

    /**
     * {@inheritDoc}
     */
    public double get() {
        return getSpeed();
    }
}
