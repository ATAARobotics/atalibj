package edu.first.module.actuators;

/**
 * General interface that signifies that the class controls the speed of a motor
 * / servo. This interface is meant to replace
 * {@link edu.wpi.first.wpilibj.SpeedController} as the de facto speed
 * controller interface, because that interface does not properly give something
 * the actual functions of a speed controller.
 *
 * <p> The only thing a speed controller can do is set the speed. All other
 * functionality cannot be assumed.
 *
 * @since May 28 13
 * @author Joel Gallant
 */
public interface SpeedController {

    /**
     * Sets the speed between {@code -1} and {@code +1}. {@code +1} should be
     * the maximum speed, and {@code -1} should be the maximum speed in the
     * opposite direction. {@code 0} should be a stopped position.
     *
     * <p> The effect of this function is not necessarily linear, but it is
     * useful to do so in implementation.
     *
     * @param speed the speed to change to
     */
    public void setSpeed(double speed);

    /**
     * Sets the speed between {@code 0} and {@code 255}. {@code 255} should be
     * the maximum speed, and {@code 0} should be the maximum speed in the
     * opposite direction. {@code 127} should be a stopped position.
     *
     * <p> The effect of this function is not necessarily linear, but it is
     * useful to do so in implementation.
     *
     * @param speed the speed to change to
     */
    public void setRawSpeed(int speed);

    /**
     * Returns the speed currently set on the speed controller. {@code +1}
     * should be the maximum speed, and {@code -1} should be the maximum speed
     * in the opposite direction. {@code 0} should be a stopped position.
     *
     * @return current speed of the controller
     */
    public double getSpeed();

    /**
     * Returns the speed currently set on the speed controller. {@code 255}
     * should be the maximum speed, and {@code 0} should be the maximum speed in
     * the opposite direction. {@code 127} should be a stopped position.
     *
     * @return current speed of the controller
     */
    public int getRawSpeed();

    /**
     * Ensures that the controller is set to the speed. If there is a watchdog
     * or something like that attached to the controller, this method will
     * update that controller so that the speed can continue.
     */
    public void update();
}
