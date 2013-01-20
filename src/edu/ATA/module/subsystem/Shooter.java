package edu.ATA.module.subsystem;

import edu.ATA.module.sensor.EncoderModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.target.BangBangModule;

/**
 * Basic module to control a one-wheeled shooter with an encoder to measure its
 * speed.
 *
 * @author joel
 */
public class Shooter extends BangBangModule {

    private final EncoderModule encoder;
    private final SpeedControllerModule motor;

    /**
     * Constructs object using an encoder and speed controller. Requires them to
     * be modules so that enabling and disabling can be guaranteed. The module
     * aspect of the given objects is handled by the inner methods of this
     * class. (enabling, disabling) It's generally advisable to only use the
     * modules here, and not use them elsewhere, as references to the same
     * objects can cause conflicts.
     *
     * <p> <b> Please make sure the encoder given uses
     * {@link EncoderModule#RATE} in its constructor.</b>
     *
     * @param encoder encoder to measure speed
     * @param motor motor to control
     */
    public Shooter(EncoderModule encoder, SpeedControllerModule motor) {
        super(encoder, motor);
        this.encoder = encoder;
        this.motor = motor;
    }

    public synchronized boolean enable() {
        encoder.enable();
        motor.enable();
        return super.enable();
    }

    public synchronized boolean disable() {
        encoder.disable();
        motor.disable();
        return super.disable();
    }

    /**
     * Changes the speed of the motor.
     *
     * @param speed speed to shoot at
     */
    public void setSpeed(double speed) {
        motor.set(speed);
    }
}
