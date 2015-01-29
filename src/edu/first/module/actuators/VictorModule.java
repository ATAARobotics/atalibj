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
public class VictorModule extends Module.StandardModule implements SpeedController {

    private final Victor victor;

    /**
     * Constructs the module with the victor object underneath this class to
     * call methods from.
     *
     * @throws NullPointerException when victor is null
     * @param victor the composing instance which perform the functions
     */
    protected VictorModule(Victor victor) {
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
     * {@inheritDoc}
     */
    @Override
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Stops the victor from moving.
     */
    @Override
    protected void disableModule() {
        victor.disable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void setSpeed(double speed) {
        ensureEnabled();
        victor.set(speed);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void setRawSpeed(int speed) {
        ensureEnabled();
        victor.setRaw(speed);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double getSpeed() {
        ensureEnabled();
        return victor.getSpeed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public int getRawSpeed() {
        ensureEnabled();
        return victor.getRaw();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method does not need to be called on a {@code Victor}, but if
     * something freezes it may help relieve it.
     */
    @Override
    public void update() {
        victor.Feed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void setRate(double rate) {
        ensureEnabled();
        victor.set(rate);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void set(double value) {
        ensureEnabled();
        victor.set(value);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double getRate() {
        ensureEnabled();
        return victor.getSpeed();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double get() {
        ensureEnabled();
        return victor.getSpeed();
    }
}
