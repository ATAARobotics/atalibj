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
public final class EncoderModule extends ForwardingEncoder implements Module.DisableableModule {

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

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.Encoder}.
 *
 * @author Joel Gallant
 */
class ForwardingEncoder implements edu.ATA.module.sensor.Encoder {

    private final edu.wpi.first.wpilibj.Encoder encoder;

    /**
     * Constructs the object by using composition, using the given encoder
     * object to control methods in this class.
     *
     * @param encoder actual underlying object used
     */
    ForwardingEncoder(edu.wpi.first.wpilibj.Encoder encoder, edu.wpi.first.wpilibj.Encoder.PIDSourceParameter pidSource) {
        this.encoder = encoder;
        encoder.setPIDSourceParameter(pidSource);
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.Encoder}.
     *
     * @return composition object under this one
     */
    protected edu.wpi.first.wpilibj.Encoder getEncoder() {
        return encoder;
    }

    /**
     * Start the Encoder. Starts counting pulses on the Encoder device.
     */
    public void start() {
        encoder.start();
    }

    /**
     * Stops counting pulses on the Encoder device. The value is not changed.
     */
    public void stop() {
        encoder.stop();
    }

    /**
     * Reset the Encoder distance to zero. Resets the current count to zero on
     * the encoder.
     */
    public void reset() {
        encoder.reset();
    }

    /**
     * Sets the maximum period for stopped detection. Sets the value that
     * represents the maximum period of the Encoder before it will assume that
     * the attached device is stopped. This timeout allows users to determine if
     * the wheels or other shaft has stopped rotating. This method compensates
     * for the decoding type.
     *
     *
     * @param maxPeriod the maximum time between rising and falling edges before
     * the FPGA will report the device stopped. This is expressed in seconds.
     */
    public void setMaxPeriod(double maxPeriod) {
        encoder.setMaxPeriod(maxPeriod);
    }

    /**
     * Determine if the encoder is stopped. Using the MaxPeriod value, a boolean
     * is returned that is true if the encoder is considered stopped and false
     * if it is still moving. A stopped encoder is one where the most recent
     * pulse width exceeds the MaxPeriod.
     *
     * @return true if the encoder is considered stopped.
     */
    public boolean getStopped() {
        return encoder.getStopped();
    }

    /**
     * Get the distance the robot has driven since the last reset.
     *
     * @return the distance driven since the last reset as scaled by the value
     * from setDistancePerPulse().
     */
    public double getDistance() {
        return encoder.getDistance();
    }

    /**
     * Get the current rate of the encoder. Units are distance per second as
     * scaled by the value from setDistancePerPulse().
     *
     * @return the current rate of the encoder.
     */
    public double getRate() {
        return encoder.getRate();
    }

    /**
     * Set the direction sensing for this encoder. This sets the direction
     * sensing on the encoder so that it could count in the correct software
     * direction regardless of the mounting.
     *
     * @param reverseDirection true if the encoder direction should be reversed
     */
    public void setReverseDirection(boolean reverseDirection) {
        encoder.setReverseDirection(reverseDirection);
    }

    /**
     * {@inheritDoc}
     */
    public double pidGet() {
        return encoder.pidGet();
    }

    /**
     * Set the distance per pulse for this encoder. This sets the multiplier
     * used to determine the distance driven based on the count value from the
     * encoder. Do not include the decoding type in this scale. The library
     * already compensates for the decoding type. Set this value based on the
     * encoder's rated Pulses per Revolution and factor in gearing reductions
     * following the encoder shaft. This distance can be in any units you like,
     * linear or angular.
     *
     * @param distancePerPulse The scale factor that will be used to convert
     * pulses to useful units.
     */
    public void setDistancePerPulse(double distancePerPulse) {
        encoder.setDistancePerPulse(distancePerPulse);
    }
}
