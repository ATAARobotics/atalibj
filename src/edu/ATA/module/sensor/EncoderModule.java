package edu.ATA.module.sensor;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Module designed to receive input from encoders that are described by
 * {@link Encoder}. When enabled, gets input from the sensor, and when disabled,
 * stops accumulating and resets the count to 0, making it always return 0.
 *
 * @author Joel Gallant
 */
public class EncoderModule extends ForwardingEncoder implements Module.DisableableModule {

    /**
     * Uses {@link edu.wpi.first.wpilibj.Encoder#getDistance()} for
     * {@link PIDSource#pidGet()}.
     */
    public static final Encoder.PIDSourceParameter DISTANCE = Encoder.PIDSourceParameter.kDistance;
    /**
     * Uses {@link edu.wpi.first.wpilibj.Encoder#getRate()} for
     * {@link PIDSource#pidGet()}.
     */
    public static final Encoder.PIDSourceParameter RATE = Encoder.PIDSourceParameter.kRate;
    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given encoder
     * object to control methods in this class. <i> Automatically uses distance
     * for PID.</i>
     *
     * @param encoder actual underlying object used
     */
    public EncoderModule(Encoder encoder) {
        super(encoder, DISTANCE);
    }

    /**
     * Constructs the object by using composition, using the given encoder
     * object to control methods in this class. Use
     * {@link EncoderModule#DISTANCE} or {@link EncoderModule#RATE} as the PID
     * source.
     *
     * @param encoder actual underlying object used
     * @param pidSource source for PID (distance or rate)
     */
    public EncoderModule(Encoder encoder, Encoder.PIDSourceParameter pidSource) {
        super(encoder, pidSource);
    }

    /**
     * Starts the encoder's counter, and enables all methods to return proper
     * values.
     *
     * @return if encoder was successfully enabled
     */
    public boolean enable() {
        start();
        return (enabled = true);
    }

    /**
     * Returns whether or not the module is enabled.
     *
     * @return if module has been enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Stops the encoder's counter, and resets the count. Prevents methods from
     * returning proper values.
     *
     * @return if module was successfully disabled
     */
    public boolean disable() {
        stop();
        reset();
        return !(enabled = false);
    }

    /**
     * If the module is enabled, returns the current rate of the encoder. Units
     * are distance per second as scaled by the value from
     * {@code setDistancePerPulse()}.
     *
     * @return the current rate of the encoder.
     */
    public double getRate() {
        return isEnabled() ? super.getRate() : 0;
    }
}
