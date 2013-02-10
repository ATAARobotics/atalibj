package edu.ATA.module.joystick;

import edu.ATA.bindings.Bindable;
import edu.ATA.module.Module;

/**
 * Class meant to describe joysticks that are capable of "binding", as described
 * in {@link Bindable}. Acts in the same way that a {@link JoystickModule} does,
 * but implements features that allow buttons and axises.
 *
 * @author Joel Gallant
 */
public class BindableJoystick extends Bindable implements Joystick, Module.DisableableModule {

    /**
     *The composition object used to get input.
     */
    protected final JoystickModule joystick;

    /**
     * Constructs the joystick using composition to operate the class using the
     * wpi joystick object.
     *
     * @param joystick object used to get input
     */
    public BindableJoystick(edu.wpi.first.wpilibj.Joystick joystick) {
        this.joystick = new JoystickModule(joystick);
    }

    /**
     * Turns off the joystick module, making all input return 0.
     *
     * @return whether module successfully disabled
     */
    public final boolean disable() {
        return joystick.disable();
    }

    /**
     * Enables the module so that it can give input from the joystick.
     *
     * @return whether module is enabled correctly
     */
    public final boolean enable() {
        return joystick.enable();
    }

    /**
     * Returns whether or not the joystick will return valid results.
     *
     * @return whether joystick is enabled
     */
    public final boolean isEnabled() {
        return joystick.isEnabled();
    }

    /**
     * If the module is enabled, returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawAxis(int)}. If it is not,
     * returns 0.
     *
     * @param port the port of the axis
     * @return value of where the joystick is (usually -1 to +1)
     */
    public double getAxis(int port) {
        return joystick.getAxis(port);
    }

    /**
     * If the module is enabled, returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawButton(int)}. If it is not,
     * returns false.
     *
     * @param button button number defined in windows
     * @return whether the button is pressed
     */
    public boolean getButton(int button) {
        return joystick.getButton(button);
    }

    /**
     * Returns {@link BindableJoystick#getButton(int)}.
     *
     * @return whether the button is pressed
     */
    public final boolean getPressed(final int port) {
        return this.getButton(port);
    }

    /**
     * Returns {@link BindableJoystick#getAxis(int)}.
     *
     * @return value of where the joystick is (usually -1 to +1)
     */
    public final double getAxisValue(final int port) {
        return getAxis(port);
    }
}
