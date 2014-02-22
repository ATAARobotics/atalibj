package edu.first.identifiers;

import edu.first.module.actuators.SpeedController;

/**
 * Inverses all output to a speed controller.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class InversedSpeedController implements SpeedController {

    private final SpeedController speedController;

    /**
     * Creates the inverted controller.
     *
     * @param speedController internal controller to send signals to and from
     */
    public InversedSpeedController(SpeedController speedController) {
        this.speedController = speedController;
    }

    public void set(double value) {
        speedController.set(-value);
    }

    public void setRate(double rate) {
        speedController.setRate(-rate);
    }

    public void setRawSpeed(int speed) {
        speedController.setRawSpeed(255 - speed);
    }

    public void setSpeed(double speed) {
        speedController.setSpeed(-speed);
    }

    public double getSpeed() {
        return speedController.getSpeed();
    }

    public int getRawSpeed() {
        return speedController.getRawSpeed();
    }

    public void update() {
        speedController.update();
    }

    public double getRate() {
        return speedController.getRate();
    }

    public double get() {
        return speedController.get();
    }
}
