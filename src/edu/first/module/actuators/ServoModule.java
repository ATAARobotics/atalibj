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
public class ServoModule extends Module.StandardModule implements Servo {

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
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void setPosition(double position) {
        ensureEnabled();
        servo.set(position);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void set(double position) {
        ensureEnabled();
        servo.set(position);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double getPosition() {
        ensureEnabled();
        return servo.get();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public double get() {
        ensureEnabled();
        return servo.get();
    }
}
