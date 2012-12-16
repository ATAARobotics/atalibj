package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.Encoder;

/**
 * Module that represents encoders. Uses {@link Encoder} underneath to get
 * input.
 *
 * @author Joel Gallant
 */
public class EncoderModule extends Module implements Module.Disableable {

    private final Encoder encoder;
    private boolean enabled;

    /**
     * Creates the module with a name and the {@link Encoder} object.
     *
     * @param name name of the module
     * @param encoder encoder to be used
     */
    public EncoderModule(String name, Encoder encoder) {
        super(name);
        this.encoder = encoder;
    }

    /**
     * Creates the encoder with the a and b channels.
     *
     * @see Encoder#Encoder(int, int)
     * @param name name of the module
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     */
    public EncoderModule(String name, int aChannel, int bChannel) {
        this(name, new Encoder(aChannel, bChannel));
    }

    /**
     * Creates the encoder with the a and b channels.
     *
     * @see Encoder#Encoder(int, int)
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     */
    public EncoderModule(int aChannel, int bChannel) {
        this("Encoder A=" + aChannel + " B=" + bChannel, new Encoder(aChannel, bChannel));
    }

    /**
     * Creates the encoder with the a and b channels.
     *
     * @see Encoder#Encoder(int, int, boolean)
     * @param name name of the module
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     * @param reverseDirection whether to switch negative to positive values
     * (and vise-versa)
     */
    public EncoderModule(String name, int aChannel, int bChannel, boolean reverseDirection) {
        this(name, new Encoder(aChannel, bChannel, reverseDirection));
    }

    /**
     * Creates the encoder with the a and b channels.
     *
     * @see Encoder#Encoder(int, int, boolean)
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     * @param reverseDirection whether to switch negative to positive values
     * (and vise-versa)
     */
    public EncoderModule(int aChannel, int bChannel, boolean reverseDirection) {
        this("Encoder A=" + aChannel + " B=" + bChannel, new Encoder(aChannel, bChannel, reverseDirection));
    }

    /**
     * Creates the encoder with the a and b channels and slot on the CRIO.
     *
     * @see Encoder#Encoder(int, int, int, int)
     * @param name name of the module
     * @param slot slot on the CRIO of all channels
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     */
    public EncoderModule(String name, int slot, int aChannel, int bChannel) {
        this(name, new Encoder(slot, aChannel, slot, bChannel));
    }

    /**
     * Creates the encoder with the a and b channels and slot on the CRIO.
     *
     * @see Encoder#Encoder(int, int, int, int)
     * @param slot slot on the CRIO of all channels
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     */
    public EncoderModule(int slot, int aChannel, int bChannel) {
        this("Encoder Slot" + slot + " A=" + aChannel + " B=" + bChannel,
                new Encoder(slot, aChannel, slot, bChannel));
    }

    /**
     * Creates the encoder with the a and b channels.
     *
     * @see Encoder#Encoder(int, int, int, int, boolean)
     * @param name name of the module
     * @param slot slot on the CRIO of all channels
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     * @param reverseDirection whether to switch negative to positive values
     * (and vise-versa)
     */
    public EncoderModule(String name, int slot, int aChannel, int bChannel, boolean reverseDirection) {
        this(name, new Encoder(slot, aChannel, slot, bChannel, reverseDirection));
    }

    /**
     * Creates the encoder with the a and b channels.
     *
     * @see Encoder#Encoder(int, int, int, int, boolean)
     * @param slot slot on the CRIO of all channels
     * @param aChannel The a channel digital input channel
     * @param bChannel The b channel digital input channel
     * @param reverseDirection whether to switch negative to positive values
     * (and vise-versa)
     */
    public EncoderModule(int slot, int aChannel, int bChannel, boolean reverseDirection) {
        this("Encoder Slot" + slot + " A=" + aChannel + " B=" + bChannel,
                new Encoder(slot, aChannel, slot, bChannel, reverseDirection));
    }

    public void enable() {
        encoder.start();
        enabled = true;
    }

    public void disable() {
        encoder.stop();
        encoder.reset();
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the {@link Encoder} object being used underneath this class.
     *
     * <p> <i> Will throw an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()}) </i>
     *
     * @return encoder being used
     */
    public Encoder getEncoder() {
        if (isEnabled()) {
            return encoder;
        } else {
            throw new IllegalStateException("Encoder module being accessed is not enabled - " + getName());
        }
    }

    /**
     * Temporarily stops the incrementing of the encoder's distance.
     *
     * <p> <i> Will throw an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()}) </i>
     *
     * @see Encoder#stop()
     */
    public void pause() {
        getEncoder().stop();
    }

    /**
     * Resets the value of the accumulated distance to 0.
     */
    public void reset() {
        getEncoder().reset();
    }

    /**
     * Returns how quickly the encoder is being 'spun'.
     *
     * @see Encoder#getRate()
     * @return rate of movement (speed)
     */
    public double getRate() {
        return getEncoder().getRate();
    }

    /**
     * Returns the accumulated distance since enabling.
     *
     * @see Encoder#getDistance()
     * @return distance since start
     */
    public double getDistance() {
        return getEncoder().getDistance();
    }
}