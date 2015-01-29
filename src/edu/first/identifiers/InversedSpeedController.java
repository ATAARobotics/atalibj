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

    @Override
    public void set(double value) {
        speedController.set(-value);
    }

    @Override
    public void setRate(double rate) {
        speedController.setRate(-rate);
    }

    @Override
    public void setRawSpeed(int speed) {
        speedController.setRawSpeed(255 - speed);
    }

    @Override
    public void setSpeed(double speed) {
        speedController.setSpeed(-speed);
    }

    @Override
    public double getSpeed() {
        return speedController.getSpeed();
    }

    @Override
    public int getRawSpeed() {
        return speedController.getRawSpeed();
    }

    @Override
    public void update() {
        speedController.update();
    }

    @Override
    public double getRate() {
        return speedController.getRate();
    }

    @Override
    public double get() {
        return speedController.get();
    }
}
