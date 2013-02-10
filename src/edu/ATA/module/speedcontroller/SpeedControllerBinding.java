package edu.ATA.module.speedcontroller;

import edu.ATA.bindings.AxisBind;

/**
 * The class for binding the speed controller value. 
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

    public void set(double axisValue) {
        speedController.set(axisValue);
    }
}
