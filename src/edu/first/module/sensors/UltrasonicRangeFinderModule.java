package edu.first.module.sensors;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * The standard module for ultrasonic range finders. Uses one input and one
 * output to send and receive signals.
 *
 * @since June 08 13
 * @author Joel Gallant
 */
public class UltrasonicRangeFinderModule extends Module.StandardModule implements RangeFinder {

    private final Ultrasonic ultrasonic;

    /**
     * Constructs the module with the ultrasonic object underneath this class to
     * call methods from.
     *
     * @param ultrasonic the composing instance which will return values
     */
    protected UltrasonicRangeFinderModule(Ultrasonic ultrasonic) {
        this.ultrasonic = ultrasonic;
    }

    /**
     * Constructs the range finder using the ping and echo channel. Ping is used
     * to send signals and echo is used to receive the reflected signal.
     *
     * @param pingChannel channel on digital sidecar for pinging
     * @param echoChannel channel on digital sidecar for receiving
     */
    public UltrasonicRangeFinderModule(int pingChannel, int echoChannel) {
        this(new Ultrasonic(pingChannel, echoChannel));
    }

    /**
     * Constructs the range finder using the ping and echo channel. Ping is used
     * to send signals and echo is used to receive the reflected signal.
     *
     * @param pingChannel channel on digital sidecar for pinging
     * @param pingSlot slot on the cRIO (1 = default)
     * @param echoChannel channel on digital sidecar for receiving
     * @param echoSlot slot on the cRIO (1 = default)
     */
    public UltrasonicRangeFinderModule(int pingChannel, int pingSlot, int echoChannel, int echoSlot) {
        this(new Ultrasonic(pingSlot, pingChannel, echoSlot, echoChannel));
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
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double getInches() {
        ensureEnabled();
        pingAndWait();
        return ultrasonic.getRangeInches();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public double getMillimeters() {
        ensureEnabled();
        pingAndWait();
        return ultrasonic.getRangeMM();
    }

    /**
     * Returns the distance from the sensor to the nearest object in inches.
     *
     * @return distance to object
     * @throws IllegalStateException when module is not enabled
     */
    public double getPosition() {
        ensureEnabled();
        pingAndWait();
        return ultrasonic.getRangeInches();
    }

    /**
     * Returns the distance from the sensor to the nearest object in inches.
     *
     * @return distance to object
     * @throws IllegalStateException when module is not enabled
     */
    public double get() {
        ensureEnabled();
        pingAndWait();
        return ultrasonic.getRangeInches();
    }

    private void pingAndWait() {
        ultrasonic.ping();
        while (!ultrasonic.isRangeValid()) {
            Timer.delay(0.02);
        }
    }
}
