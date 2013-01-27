package edu.ATA.module.speedcontroller;

import edu.ATA.bindings.AxisBind;

/**
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class SpeedControllerBinding implements AxisBind {

    private final SpeedControllerModule speedController;

    public SpeedControllerBinding(SpeedControllerModule speedController) {
        this.speedController = speedController;
    }

    public void set(double axisValue) {
        speedController.set(axisValue);
    }
}
