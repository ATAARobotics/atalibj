package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.PWM;

/**
 * Standard servo that rotates ~170 degrees. Most servos should function using
 * this class. For custom servos, implement {@link PWM} yourself and create the
 * module.
 *
 * @since June 06 13
 * @author Joel Gallant
 */
public class ServoModule extends Module.StartardModule implements Servo {

    private final edu.wpi.first.wpilibj.Servo servo;

    /**
     * Constructs the module using the servo object to call functions from.
     *
     * @param servo the composing instance which perform the functions
     */
    protected ServoModule(edu.wpi.first.wpilibj.Servo servo) {
        this.servo = servo;
    }

    /**
     * Constructs the servo using its channel on the digital sidecar.
     *
     * @param channel port on sidecar
     */
    public ServoModule(int channel) {
        this(new edu.wpi.first.wpilibj.Servo(channel));
    }

    /**
     * Constructs the servo using its channel on the digital sidecar.
     *
     * @param channel port on sidecar
     * @param slot slot in cRIO (1 = default)
     */
    public ServoModule(int channel, int slot) {
        this(new edu.wpi.first.wpilibj.Servo(slot, channel));
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
     * {@inheritDoc}
     */
    public void setPosition(double position) {
        ensureEnabled();
        servo.set(position);
    }

    /**
     * {@inheritDoc}
     */
    public void set(double position) {
        ensureEnabled();
        servo.set(position);
    }

    /**
     * {@inheritDoc}
     */
    public double getPosition() {
        ensureEnabled();
        return servo.get();
    }

    /**
     * {@inheritDoc}
     */
    public double get() {
        ensureEnabled();
        return servo.get();
    }
}
