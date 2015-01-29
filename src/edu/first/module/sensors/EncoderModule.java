package edu.first.module.sensors;

import edu.first.identifiers.Input;
import edu.first.module.Module;

/**
 * Module for standard encoders. Measures the distance and rate of something
 * that moves. Usually used for wheel movement.
 *
 * @since June 03 13
 * @author Joel Gallant
 */
public class EncoderModule extends Module.StandardModule implements Encoder {

    private static final InputType DEFAULT_INPUT_TYPE = InputType.DISTANCE;
    private final edu.wpi.first.wpilibj.Encoder encoder;
    private InputType inputType;

    /**
     * Constructs the encoder using the {@link edu.wpi.first.wpilibj.Encoder}
     * object, the input type and whether it should reverse the output.
     *
     * @throws NullPointerException when encoder is null
     * @param encoder underlying object to get input from
     * @param inputType which kind of input to accept
     * @param reverseDirection if input should be reversed
     */
    protected EncoderModule(edu.wpi.first.wpilibj.Encoder encoder,
            InputType inputType, boolean reverseDirection) {
        if (encoder == null) {
            throw new NullPointerException("Null encoder given");
        }
        encoder.setReverseDirection(reverseDirection);
        this.encoder = encoder;
        this.inputType = inputType;
    }

    /**
     * Constructs the encoder using the {@link edu.wpi.first.wpilibj.Encoder}
     * object and the input type.
     *
     * @param encoder underlying object to get input from
     * @param inputType which kind of input to accept
     */
    protected EncoderModule(edu.wpi.first.wpilibj.Encoder encoder,
            InputType inputType) {
        this(encoder, inputType, false);
    }

    /**
     * Constructs the encoder using the {@link edu.wpi.first.wpilibj.Encoder}
     * object.
     *
     * @param encoder underlying object to get input from
     */
    protected EncoderModule(edu.wpi.first.wpilibj.Encoder encoder) {
        this(encoder, DEFAULT_INPUT_TYPE);
    }

    /**
     * Constructs the encoder using its address, input type and if input should
     * be reversed.
     *
     * @param aChannel first channel in digital sidecar
     * @param bChannel second channel in digital sidecar
     * @param inputType which kind of input to accept
     * @param reverseDirection if input should be reversed
     */
    public EncoderModule(int aChannel, int bChannel, InputType inputType,
            boolean reverseDirection) {
        this(new edu.wpi.first.wpilibj.Encoder(aChannel, bChannel), inputType,
                reverseDirection);
    }

    /**
     * Constructs the encoder using its address and input type.
     *
     * @param aChannel first channel in digital sidecar
     * @param bChannel second channel in digital sidecar
     * @param inputType which kind of input to accept
     */
    public EncoderModule(int aChannel, int bChannel, InputType inputType) {
        this(aChannel, bChannel, inputType, false);
    }

    /**
     * Constructs the encoder using its address.
     *
     * @param aChannel first channel in digital sidecar
     * @param bChannel second channel in digital sidecar
     */
    public EncoderModule(int aChannel, int bChannel) {
        this(aChannel, bChannel, DEFAULT_INPUT_TYPE);
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
     * Resets the encoder's count.
     */
    @Override
    protected void disableModule() {
        encoder.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    /**
     * Resets the count of the encoder. Counting will still continue.
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
    public void reset() {
        ensureEnabled();
        encoder.reset();
    }

    /**
     * Sets the distance that one pulse should be considered. This sets the
     * multiplier used to determine the distance driven based on the count value
     * from the encoder. Do not include the decoding type in this scale. The
     * library already compensates for the decoding type. Set this value based
     * on the encoder's rated Pulses per Revolution and factor in gearing
     * reductions following the encoder shaft. This distance can be in any units
     * you like, linear or angular.
     *
     * <p>
     * A general guideline for RPM is to set this to 1/CountsPerRev.
     *
     * @param distance how much to consider one pulse
     */
    public void setDistancePerPulse(double distance) {
        encoder.setDistancePerPulse(distance);
    }

    /**
     * Sets whether the input should be reversed.
     *
     * @param reverse if input should be reversed
     */
    public void setReverseDirection(boolean reverse) {
        encoder.setReverseDirection(reverse);
    }

    /**
     * Sets the absolute minimum rate that {@link #getRate()} can return to be
     * considered moving. If the rate is smaller than the minimum rate,
     * {@link #isStopped()} will return true.
     *
     * @param rate lowest speed considered moving
     */
    public void setMinRate(double rate) {
        encoder.setMinRate(rate);
    }

    /**
     * Sets which {@link InputType} that will be used to determine the value of
     * {@link #get()}.
     *
     * @param inputType which input type to get
     */
    public void setInputType(InputType inputType) {
        this.inputType = inputType;
    }

    /**
     * Using value given by {@link #setMinRate(double)}, determines if the
     * encoder is currently stopped.
     *
     * @throws IllegalStateException when module is not enabled
     * @return if rate is smaller than minimum rate
     */
    public boolean isStopped() {
        ensureEnabled();
        return encoder.getStopped();
    }

    /**
     * Returns the actual count of the encoder, with no scaling.
     *
     * @throws IllegalStateException when module is not enabled
     * @return ticks counted by the encoder
     */
    @Override
    public int getRaw() {
        ensureEnabled();
        return encoder.getRaw();
    }

    /**
     * Returns the distance that the encoder has traveled, as scaled by
     * {@link #setDistancePerPulse(double)}.
     *
     * @throws IllegalStateException when module is not enabled
     * @return current distance
     */
    public double getDistance() {
        ensureEnabled();
        return encoder.getDistance();
    }

    /**
     * Returns the distance that the encoder has traveled, as scaled by
     * {@link #setDistancePerPulse(double)}.
     *
     * @throws IllegalStateException when module is not enabled
     * @return current distance
     */
    @Override
    public double getPosition() {
        ensureEnabled();
        return encoder.getDistance();
    }

    /**
     * Returns the current rate of the encoder, based on it's period and period
     * multiplier. Units are distance per second as scaled by the value from
     * {@link #setDistancePerPulse(double)}.
     *
     * @throws IllegalStateException when module is not enabled
     * @return current rate calculated by encoder
     */
    @Override
    public double getRate() {
        ensureEnabled();
        return encoder.getRate();
    }

    public Input rate() {
        return new Input() {
            @Override
            public double get() {
                return getRate();
            }
        };
    }

    public Input distance() {
        return new Input() {
            @Override
            public double get() {
                return getDistance();
            }
        };
    }

    /**
     * Returns the current input type that {@link #get()} should return.
     *
     * @return type of input to return in {@code get()}
     */
    public InputType getInputType() {
        return inputType;
    }

    /**
     * Returns a value based on the {@link InputType} given in the constructor
     * or
     * {@link #setInputType(edu.first.module.sensors.EncoderModule.InputType)}.
     *
     * <p>
     * By default, input type is {@link InputType#DISTANCE}.
     *
     * @return input based on input type
     */
    @Override
    public double get() {
        if (inputType.equals(InputType.DISTANCE)) {
            return getPosition();
        } else if (inputType.equals(InputType.RATE)) {
            return getRate();
        } else {
            return 0;
        }
    }

    /**
     * Enum representing which input type the encoder should return in
     * {@link EncoderModule#get()}.
     */
    public static enum InputType {

        DISTANCE, RATE;
    }
}
