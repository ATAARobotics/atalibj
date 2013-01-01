package edu.ATA.module.joystick;

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
        this.joystick = joystick;
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.Joystick}.
     *
     * @return composition object under this one
     */
    protected edu.wpi.first.wpilibj.Joystick getJoystick() {
        return joystick;
    }

    /**
     * Returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawAxis(int)}.
     *
     * @param axis axis number defined in windows
     * @return value of where the joystick is (usually -1 to +1)
     */
    public double getAxis(int axis) {
        return joystick.getRawAxis(axis);
    }

    /**
     * Returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawButton(int)}.
     *
     * @param button button number defined in windows
     * @return whether the button is pressed
     */
    public boolean getButton(int button) {
        return joystick.getRawButton(button);
    }
}