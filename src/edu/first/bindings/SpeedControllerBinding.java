package edu.first.bindings;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * The class for binding a speed controller value.
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class SpeedControllerBinding implements AxisBind {

    private final SpeedController speedController;
    private final boolean reversed;

    /**
     * Makes the speed controller bindable to an axis.
     *
     * @param speedController the speed controller module used
     */
    public SpeedControllerBinding(SpeedController speedController) {
        this(speedController, false);
    }

    /**
     * Makes the speed controller bindable to an axis.
     *
     * @param speedController the speed controller module used
     * @param reversed if output should be reversed
     */
    public SpeedControllerBinding(SpeedController speedController, boolean reversed) {
        this.speedController = speedController;
        this.reversed = reversed;
    }

    /**
     * Sets the speed controller's speed.
     *
     * @param axisValue speed from joystick
     */
    public void set(double axisValue) {
        speedController.set(reversed ? -axisValue : axisValue);
    }
}
