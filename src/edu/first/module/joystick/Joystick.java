package edu.first.module.joystick;

/**
 * Outline for objects that give input from the user. Acts as a stable form of
 * {@link edu.wpi.first.wpilibj.Joystick}, so that expectations of classes
 * implementing this interface do not change.
 *
 * @author Joel Gallant
 */
interface Joystick {

    /**
     * Returns the value of the axis mapped to the specific port/channel/axis.
     * This method usually takes the windows mapped port, and returns a value
     * from -1 to +1, but is not required to follow that format.
     *
     * @param axis number that identifies axis
     * @return value of where the joystick is on the axis
     */
    public double getRawAxis(int axis);

    /**
     * Returns the value of the button mapped to the specific
     * port/channel/button number. This method usually takes the windows mapped
     * port, but is not required to. Returns whether the button is currently
     * being pushed.
     *
     * @param button button to check
     * @return whether button is pushed
     */
    public boolean getRawButton(int button);
}
