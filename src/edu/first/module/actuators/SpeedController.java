package edu.first.module.actuators;

import edu.first.identifiers.RateActuator;
import edu.first.identifiers.RateSensor;

/**
 * General interface that signifies that the class controls the speed of a motor
 * / servo. This interface is meant to replace
 * {@link edu.wpi.first.wpilibj.SpeedController} as the de facto speed
 * controller interface, because that interface does not properly give something
 * the actual functions of a speed controller.
 *
 * <p>
 * The only thing a speed controller can do is set the speed. All other
 * functionality cannot be assumed.
 *
 * @since May 28 13
 * @author Joel Gallant
 */
public interface SpeedController extends RateActuator, RateSensor {

    /**
     * Sets the speed between {@code -1} and {@code +1}. {@code +1} should be
     * the maximum speed, and {@code -1} should be the maximum speed in the
     * opposite direction. {@code 0} should be a stopped position.
     *
     * <p>
     * The effect of this function is not necessarily linear, but it is useful
     * to do so in implementation.
     *
     * @param speed the speed to change to
     */
    public void setSpeed(double speed);

    /**
     * Sets the speed between {@code 0} and {@code 255}. {@code 255} should be
     * the maximum speed, and {@code 0} should be the maximum speed in the
     * opposite direction. {@code 127} should be a stopped position.
     *
     * <p>
     * The effect of this function is not necessarily linear, but it is useful
     * to do so in implementation.
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

    /**
     * Sets the speed to {@code value}.
     *
     * @see #setSpeed(double)
     * @param rate rate to set speed to
     */
    @Override
    public void setRate(double rate);

    /**
     * Sets the speed to {@code value}.
     *
     * @see #setSpeed(double)
     * @param value setting to apply
     */
    @Override
    public void set(double value);

    /**
     * Returns the speed of the controller.
     *
     * @return speed of controller
     * @see #getSpeed()
     */
    @Override
    public double getRate();

    /**
     * Returns the speed of the controller. Is not bound by obligation to any
     * value range.
     *
     * @return speed of controller
     */
    @Override
    public double get();

    /**
     * A WPILibJ compatible class for usage in WPI classes that require a
     * {@link edu.wpi.first.wpilibj.SpeedController}.
     */
    public static final class CompatibleSpeedController implements edu.wpi.first.wpilibj.SpeedController {

        private final SpeedController controller;

        /**
         * Constructs the controller with the atalibj controller underneath it.
         *
         * @param controller speed controller to use
         */
        public CompatibleSpeedController(SpeedController controller) {
            this.controller = controller;
        }

        /**
         * Returns the speed of the speed controller.
         *
         * @return speed
         * @see SpeedController#getSpeed()
         */
        @Override
        public double get() {
            return controller.getSpeed();
        }
        
        /**
         * Sets the speed of the speed controller.
         *
         * @param speed new speed
         * @see SpeedController#setSpeed(double)
         */
        @Override
        public void set(double speed) {
            controller.setSpeed(speed);
        }

        /**
         * Stops the speed controller.
         */
        @Override
        public void disable() {
            controller.setSpeed(0);
        }

        /**
         * Sets the speed of the speed controller.
         *
         * @param output new speed
         * @see SpeedController#setSpeed(double)
         */
        @Override
        public void pidWrite(double output) {
            controller.setSpeed(output);
        }

		@Override
		public void setInverted(boolean isInverted) {
			throw new UnsupportedOperationException(); //TODO add functionality		
		}

		@Override
		public boolean getInverted() {
			throw new UnsupportedOperationException(); //TODO add functionality	
		}

		@Override
		public void stopMotor() {
			throw new UnsupportedOperationException(); //TODO add functionality
		}
    }
}
