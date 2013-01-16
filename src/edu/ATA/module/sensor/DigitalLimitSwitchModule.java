package edu.ATA.module.sensor;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Modules representing digital limit switch sensors. To check whether the
 * switch is pushed, use {@link DigitalLimitSwitchModule#isPushed()}. When it is
 * not enabled, that method will always return false.
 *
 * @author Team 4334
 */
public final class DigitalLimitSwitchModule extends ForwardingDigitalLimitSwitch implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given digital input
     * object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    public DigitalLimitSwitchModule(DigitalInput button) {
        super(button);
    }

    /**
     * Disables the module. This prevents the switch from returning true in
     * {@link DigitalLimitSwitchModule#isPushed()}
     *
     * @return if module was successfully disabled
     */
    public boolean disable() {
        return !(enabled = false);
    }

    /**
     * Enables the module. This enables
     * {@link DigitalLimitSwitchModule#isPushed()} to function properly.
     *
     * @return if module was successfully enabled
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether or not the module has been enabled yet. If it is not
     * enabled, {@link DigitalLimitSwitchModule#isPushed()} will not function
     * properly. (will always return false)
     *
     * @return whether module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * If the module is enabled, returns whether or not the switch connected to
     * the port is currently sending a signal. (not necessarily on) If the
     * module is not enabled, this method will always return false.
     *
     * @return if the switch is sending a signal
     */
    public boolean isPushed() {
        return isEnabled() ? super.isPushed() : false;
    }
}

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.DigitalInput}.
 *
 * @author Joel Gallant
 */
class ForwardingDigitalLimitSwitch implements DigitalLimitSwitch {

    private final DigitalInput button;

    /**
     * Constructs the object by using composition, using the given digital input
     * object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    ForwardingDigitalLimitSwitch(DigitalInput button) {
        this.button = button;
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.DigitalInput}.
     *
     * @return composition object under this one
     */
    protected DigitalInput getSwitch() {
        return button;
    }

    /**
     * Returns whether or not the switch connected to the port is currently
     * sending a signal. (not necessarily on)
     *
     * @return if switch is sending signal
     */
    public boolean isPushed() {
        return button.get();
    }
}
