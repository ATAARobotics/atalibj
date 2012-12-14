package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Module object representing a PWM controlled speed controller. Only applies to
 * {@link SpeedController} objects.
 *
 * <p> Is not 'enablable' or 'disablable'. Will always work.
 *
 * @author Joel Gallant
 */
public abstract class SpeedControllerModule extends Module {

    private SpeedController sc;

    /**
     * Creates module with its name and {@link SpeedController} object.
     *
     * @param name name of the module
     * @param speedController speed controller
     */
    public SpeedControllerModule(String name, SpeedController speedController) {
        super(name);
        this.sc = speedController;
    }

    public void enable() {
    }

    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the original speed controller.
     *
     * @return speed controller object
     */
    public SpeedController getSpeedController() {
        return sc;
    }

    /**
     * Sets the speed of the motor.
     *
     * @param speed speed from -1 to +1
     */
    public void setSpeed(double speed) {
        sc.set(speed);
    }

    /**
     * Stops the motor.
     */
    public void stop() {
        sc.set(0);
    }

    /**
     * Returns the currently set speed.
     *
     * @return speed from -1 to +1
     */
    public double getSpeed() {
        return sc.get();
    }

    /**
     * Feed the MotorSafety timer. This method is called by the subclass motor
     * whenever it updates its speed, thereby reseting the timeout value. This
     * method is here to allow user to set the speed and just periodically feed
     * it rather than setting speed each time.
     */
    public abstract void feed();
}