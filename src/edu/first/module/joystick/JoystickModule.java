package edu.first.module.joystick;

import edu.first.module.Module;

/**
 * Module designed to get input from the user. Has all of the elements from
 * {@link ForwardingJoystick}, and is a {@link DisableableModule}. When it is
 * not enabled, all input will return 0.
 *
 * @author Joel Gallant
 */
public class JoystickModule extends ForwardingJoystick implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given joystick
     * object to control methods in this class.
     *
     * @param joystick actual underlying object used
     */
    public JoystickModule(edu.wpi.first.wpilibj.Joystick joystick) {
        super(joystick);
    }

    /**
     * Enables the module so that it can give input from the joystick.
     *
     * @return whether module is enabled correctly
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether or not the joystick will return valid results.
     *
     * @return whether joystick is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Turns off the joystick module, making all input return 0.
     *
     * @return whether module successfully disabled
     */
    public boolean disable() {
        return !(enabled = false);
    }

    /**
     * If the module is enabled, returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawAxis(int)}. If it is not,
     * returns 0.
     *
     * @param axis axis number defined in windows
     * @return value of where the joystick is (usually -1 to +1)
     */
    public final double getRawAxis(int axis) {
        return isEnabled() ? super.getRawAxis(axis) : 0;
    }

    /**
     * If the module is enabled, returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawButton(int)}. If it is not,
     * returns false.
     *
     * @param button button number defined in windows
     * @return whether the button is pressed
     */
    public final boolean getRawButton(int button) {
        return isEnabled() ? super.getRawButton(button) : false;
    }
}

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.Joystick}.
 *
 * @author Joel Gallant
 */
class ForwardingJoystick implements Joystick {

    private final edu.wpi.first.wpilibj.Joystick joystick;

    /**
     * Constructs the object by using composition, using the given joystick
     * object to control methods in this class.
     *
     * @param joystick actual underlying object used
     */
    ForwardingJoystick(edu.wpi.first.wpilibj.Joystick joystick) {
        if (joystick == null) {
            throw new NullPointerException();
        }
        this.joystick = joystick;
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.Joystick}.
     *
     * @return composition object under this one
     */
    protected final edu.wpi.first.wpilibj.Joystick getJoystick() {
        return joystick;
    }

    /**
     * Returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawAxis(int)}.
     *
     * @param axis axis number defined in windows
     * @return value of where the joystick is (usually -1 to +1)
     */
    public double getRawAxis(int axis) {
        return joystick.getRawAxis(axis);
    }

    /**
     * Returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawButton(int)}.
     *
     * @param button button number defined in windows
     * @return whether the button is pressed
     */
    public boolean getRawButton(int button) {
        return joystick.getRawButton(button);
    }
}