package edu.first.module.actuators;

import edu.first.identifiers.PositionalActuator;
import edu.first.identifiers.PositionalSensor;

/**
 * Basic representation of servo motors. Moves from position to position.
 *
 * @since June 06 13
 * @author Joel Gallant
 */
public interface Servo extends PositionalActuator, PositionalSensor {

    /**
     * Sets the current position of the servo. This value will be between 0 and
     * 1, and is linear to the position of the servo. It is not necessarily in
     * tune with the rotation of the servo.
     *
     * @param position placement to move servo to
     */
    @Override
    public void setPosition(double position);

    /**
     * Sets the position of the servo.
     *
     * @param position placement to move servo to
     * @see #setPosition(double)
     */
    @Override
    public void set(double position);

    /**
     * Returns the current position of the servo. This value will be between 0
     * and 1, and is linear to the position of the servo. It is not necessarily
     * in tune with the rotation of the servo.
     *
     * @return current position of the servo
     */
    @Override
    public double getPosition();

    /**
     * Returns the position of the servo.
     *
     * @return current position of the servo
     * @see #getPosition()
     */
    @Override
    public double get();
}
