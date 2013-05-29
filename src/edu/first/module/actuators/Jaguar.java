package edu.first.module.actuators;

import edu.first.module.Module;

public class Jaguar extends Module.StartardModule implements SpeedController {

    private final edu.wpi.first.wpilibj.Jaguar jaguar;

    /**
     * Constructs the module with the jaguar object underneath this class to
     * call methods from.
     *
     * @param jaguar  the composing instance which perform the functions
     */
    public Jaguar(edu.wpi.first.wpilibj.Jaguar jaguar) {
        this.jaguar = jaguar;
    }

    /**
     * Constructs the module with the port on the digital sidecar.
     *
     * @param channel port on sidecar
     */
    public Jaguar(int channel) {
        this(new edu.wpi.first.wpilibj.Jaguar(channel));
    }

    /**
     * Constructs the module with the port on the digital sidecar and which slot
     * the sidecar is in.
     *
     * @param channel port on sidecar
     * @param slot slow in cRIO (1 = default)
     */
    public Jaguar(int channel, int slot) {
        this(new edu.wpi.first.wpilibj.Jaguar(slot, channel));
    }

    /**
     * {@inheritDoc}
     */
    public void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p> Stops the jaguar from moving.
     */
    public void disableModule() {
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
     */
    public double getSpeed() {
        return jaguar.getSpeed();
    }

    /**
     * {@inheritDoc}
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
}
