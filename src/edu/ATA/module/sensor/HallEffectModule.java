package edu.ATA.module.sensor;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Module representing hall effect sensors. To get the current rate use
 * {@link HallEffectModule#getRate()} When enabled, can receive input from the
 * sensor, but otherwise will always return 0 if it is disabled.
 *
 * @author Team 4334
 */
public class HallEffectModule extends ForwardingHallEffectModule {

    /**
     * Constructs the object by using composition, using the given digital input
     * object to control methods in this class.
     *
     * @param hallEffect actual underlying object used
     */
    public HallEffectModule(DigitalInput hallEffect) {
        super(hallEffect);
    }

    /**
     * Constructs the object by using composition, using the given digital input
     * object to control methods in this class.
     *
     * @param hallEffect actual underlying object used
     * @param counter the counter object it uses
     */
    public HallEffectModule(DigitalInput hallEffect, Counter counter) {
        super(hallEffect, counter);
    }
    private boolean enabled;

    /**
     * Disables the module. This prevents the class from returning values.
     * Additionally, it stops and resets the counter.
     *
     * @return if module was successfully disabled
     */
    public boolean disable() {
        stop();
        return !(enabled = false);
    }

    /**
     * Enables the module and starts counter. Allows the class to function
     * properly.
     *
     * @return if module was successfully enabled
     */
    public boolean enable() {
        start();
        return (enabled = true);
    }

    /**
     * Returns whether or not the module has been enabled yet. If it is not
     * enabled, The methods of this class will not function if disabled
     * properly. (will always return false)
     *
     * @return whether module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * If the module is enabled, returns the current polarity of the sensor.
     *
     * @return the current polarization of the sensor
     */
    public boolean isPolarized() {
        return isEnabled() ? super.isPolarized() : false;
    }

    /**
     * If the module is enabled, returns the current count from {@link Counter}.
     *
     * @return the current count from the Counter
     */
    public int getCount() {
        return isEnabled() ? super.getCount() : 0;
    }

    /**
     * If the module is enabled, returns the current rate of the counter.
     *
     * @return the rate of sensor
     */
    public double getRate() {
        return isEnabled() ? super.getRate() : 0;
    }
}

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards
 * {@link edu.wpi.first.wpilibj.DigitalInput} & {@link edu.wpi.first.wpilibj.Counter}.
 *
 * @author Denis Trailin
 */
class ForwardingHallEffectModule implements HallEffect {

    private final DigitalInput hallEffect;
    private final Counter counter;
    private long start = System.currentTimeMillis();

    /**
     * Constructs the object by using composition, using the given digital input
     * object to control methods in this class.
     *
     * @param hallEffect underlying hallEffect object used
     */
    public ForwardingHallEffectModule(DigitalInput hallEffect) {
        this.hallEffect = hallEffect;
        this.counter = new Counter(hallEffect);
    }

    /**
     * Constructs the object by using composition, using the given digital input
     * object to control methods in this class.
     *
     * @param counter Counter object being used
     * @param hallEffect underlying hallEffect object used
     */
    public ForwardingHallEffectModule(DigitalInput hallEffect, Counter counter) {
        this.hallEffect = hallEffect;
        this.counter = counter;
    }

    /**
     * Starts the the counter and sets the start variable.
     *
     */
    protected void start() {
        counter.start();
        start = System.currentTimeMillis();
    }

    /**
     *Stops and resets the Counter.
     *
     */
    protected void stop() {
        counter.stop();
        counter.reset();
    }
/**
 * Returns the current timer value.
 * @return the current counter value 
 */
    
    public int getCount() {
        return counter.get();
    }
    /**
     * Returns the counter rate.
     * 
     * @return the current counter rate
     */

    public double getRate() {
        return getCount() / (System.currentTimeMillis() - start);
    }
    /**
     * Returns boolean polarity of the Hall Effect sensor.
     *
     * @return the Hall Effect sensor polarity
     */
    public boolean isPolarized() {
        return hallEffect.get();
    }
}