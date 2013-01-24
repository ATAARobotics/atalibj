package edu.ATA.module.speedcontroller;

import edu.ATA.module.joystick.BindableJoystick;

/**
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class SpeedControllerBinding implements BindableJoystick.BindedAxis {

    private final SpeedControllerModule speedController;

    public SpeedControllerBinding(SpeedControllerModule speedController) {
        this.speedController = speedController;
    }

    public void set(double axisValue) {
        speedController.set(axisValue);
    }
}
