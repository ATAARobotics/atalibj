package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.Gyro;

/**
 * Module used to represent gyroscopes ({@link Gyro}).
 *
 * @author Joel Gallant
 */
public final class GyroModule extends Module implements Module.Disableable {

    private final Gyro gyro;
    private boolean enabled = false;

    /**
     * Creates the module using a name and the port on the analog breakout.
     *
     * @param name name of the module shown to the user
     * @param port channel on the analog sidecar
     */
    public GyroModule(String name, int port) {
        super(name);
        this.gyro = new Gyro(port);
    }

    /**
     * Creates the module using the port on the analog breakout.
     *
     * @param port channel on the analog sidecar
     */
    public GyroModule(int port) {
        super("Gyro Port " + port);
        this.gyro = new Gyro(port);
    }

    /**
     * Creates the module using a name, slot on the CRIO and port on the analog
     * breakout.
     *
     * @param name name of the module shown to the user
     * @param slot slot on the CRIO
     * @param port channel on the analog sidecar
     */
    public GyroModule(String name, int slot, int port) {
        super(name);
        this.gyro = new Gyro(slot, port);
    }

    /**
     * Creates the module using the slot on the CRIO and port on the analog
     * breakout.
     *
     * @param slot slot on the CRIO
     * @param port channel on the analog sidecar
     */
    public GyroModule(int slot, int port) {
        super("Gyro Slot " + slot + " Port " + port);
        this.gyro = new Gyro(slot, port);
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the {@link Gyro} object used underneath this class.
     *
     * <p> <i> Will throw an {@link IllegalStateException} if this module is not
     * enabled ({@link Module#enable()}).</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return gyro object used
     */
    public Gyro getGyro() throws IllegalStateException {
        if (isEnabled()) {
            return gyro;
        } else {
            throw new IllegalStateException("Gyro being accessed is not enabled - " + getName());
        }
    }

    /**
     * Returns the angle that the gyroscope has accumulated in degrees.
     *
     * @see Gyro#getAngle()
     * @throws IllegalStateException thrown when module is not enabled
     * @return angle using degrees (negative = left, positive = right)
     */
    public double getAngle() throws IllegalStateException {
        return getGyro().getAngle();
    }
}