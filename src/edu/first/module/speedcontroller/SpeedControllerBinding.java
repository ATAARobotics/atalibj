package edu.first.module.speedcontroller;

import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.bindings.AxisBind;

/**
 * The class for binding a speed controller value.
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class SpeedControllerBinding implements AxisBind {

    private final SpeedControllerModule speedController;

    /**
     * Makes the speed controller bindable to an axis.
     *
     * @param speedController the speed controller module used
     */
    public SpeedControllerBinding(SpeedControllerModule speedController) {
        this.speedController = speedController;
    }

    /**
     * Sets the speed controller's speed.
     *
     * @param axisValue speed from joystick
     */
    public void set(double axisValue) {
        speedController.set(axisValue);
    }
}
