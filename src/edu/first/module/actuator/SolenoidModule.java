package edu.first.module.actuator;

import edu.first.module.Module;

/**
 * Module representing solenoids. Useful for pneumatic systems. When disabled,
 * it cannot set the solenoid on or off.
 *
 * @author Team 4334
 */
public class SolenoidModule extends ForwardingSolenoid implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given solenoid
     * object to control methods in this class.
     *
     * @param solenoid actual underlying object used
     */
    public SolenoidModule(edu.wpi.first.wpilibj.Solenoid solenoid) {
        super(solenoid);
    }

    /**
     * Disables the module. This prevents the solenoid from being turned on.
     *
     * @return if module has been enabled successfully
     */
    public boolean disable() {
        return !(enabled = false);
    }

    /**
     * Enables the module. This allows the solenoid to be turned on.
     *
     * @return if module has been disabled successfully
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether the module is currently enabled. If it is enabled, it can
     * set the solenoid on and off.
     *
     * @return whether module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Changes the state of the solenoid to on or off, depending on {@code on}.
     * If the module is disabled, this method will not turn pneumatics on or
     * off.
     *
     * @param on if solenoid should be on
     */
    public final void set(boolean on) {
        if (isEnabled()) {
            super.set(on);
        }
    }
}

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.Solenoid}.
 *
 * @author Joel Gallant
 */
class ForwardingSolenoid implements Solenoid {

    private final edu.wpi.first.wpilibj.Solenoid solenoid;

    /**
     * Constructs the object by using composition, using the given solenoid
     * object to control methods in this class.
     *
     * @param solenoid actual underlying object used
     */
    ForwardingSolenoid(edu.wpi.first.wpilibj.Solenoid solenoid) {
        if(solenoid == null) {
            throw new NullPointerException();
        }
        this.solenoid = solenoid;
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.Solenoid}.
     *
     * @return composition object under this one
     */
    protected edu.wpi.first.wpilibj.Solenoid getSolenoid() {
        return solenoid;
    }

    /**
     * Turns the solenoid on. Solenoid will be on until turned off.
     *
     * @param on whether solenoid is on
     */
    public void set(boolean on) {
        solenoid.set(on);
    }

    /**
     * Returns whether the solenoid has been turned on.
     *
     * @return if solenoid is on
     */
    public final boolean get() {
        return solenoid.get();
    }
}
