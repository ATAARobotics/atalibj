package edu.first.module.speedcontroller;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Speed controller object that controls multiple speed controllers, doing the
 * same thing to all of them.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GroupSpeedController implements SpeedController {

    private final SpeedController[] speedControllers;

    /**
     * Constructs the group speed controller with an array of all the speed
     * controllers to use. There must be at least one speed controller in the
     * array. ({@link IllegalStateException})
     *
     * @param speedControllers speed controllers to control
     */
    public GroupSpeedController(SpeedController[] speedControllers) {
        if (speedControllers == null || speedControllers.length == 0) {
            throw new IllegalStateException("GroupSpeedController with no speed controllers");
        }
        this.speedControllers = speedControllers;
    }

    /**
     * Returns the mean speed on all the speed controllers. This <b>should</b>
     * return the speed of all the controllers, but in reality that is not the
     * case.
     *
     * @return average speed of controllers
     */
    public double get() {
        double s = 0;
        for (int x = 0; x < speedControllers.length; x++) {
            s += speedControllers[x].get();
        }
        return s / speedControllers.length;
    }

    /**
     * Sets the speed of all the speed controllers.
     *
     * @param speed the speed to set - value should be between -1.0 and 1.0
     * @param syncGroup the update group to add this set() to, pending
     * updateSyncGroup() - if 0, update immediately
     */
    public void set(double speed, byte syncGroup) {
        for (int x = 0; x < speedControllers.length; x++) {
            speedControllers[x].set(speed, syncGroup);
        }
    }

    /**
     * Sets the speed of all the speed controllers.
     *
     * @param speed the speed to set - value should be between -1.0 and 1.0
     */
    public void set(double speed) {
        for (int x = 0; x < speedControllers.length; x++) {
            speedControllers[x].set(speed);
        }
    }

    /**
     * Disables all speed controllers.
     */
    public void disable() {
        for (int x = 0; x < speedControllers.length; x++) {
            speedControllers[x].disable();
        }
    }

    /**
     * Writes the pid value to all speed controllers.
     *
     * @param output pid output value
     */
    public void pidWrite(double output) {
        for (int x = 0; x < speedControllers.length; x++) {
            speedControllers[x].pidWrite(output);
        }
    }
}