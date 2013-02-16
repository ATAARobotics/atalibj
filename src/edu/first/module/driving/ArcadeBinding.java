package edu.first.module.driving;

import edu.first.bindings.AxisBind;

/**
 * An axis bind that binds the axis to a value of arcade drive. This means it
 * uses {@link RobotDriveModule#setForwardValue(double)} and
 * {@link RobotDriveModule#setRotateValue(double)}.
 *
 * @author Joel Gallant
 */
public class ArcadeBinding implements AxisBind {

    public static final Value FORWARD = new Value(Value.FORWARD), ROTATE = new Value(Value.ROTATE);
    private final RobotDriveModule robotDrive;
    private final Value value;

    /**
     * Object representing forwards or rotate value.
     */
    public static class Value {

        private static int FORWARD = 1, ROTATE = 2;
        private final int value;

        private Value(int value) {
            this.value = value;
        }
    }

    /**
     * Constructs bind using the robot drive to use and which value to set.
     *
     * @param robotDrive driving object to move robot with
     * @param value which value to set with the bind
     */
    public ArcadeBinding(RobotDriveModule robotDrive, Value value) {
        this.robotDrive = robotDrive;
        this.value = value;
    }

    /**
     * Sets the value to the axis value given.
     *
     * @param axisValue value from joystick
     */
    public void set(double axisValue) {
        if (value.value == ROTATE.value) {
            robotDrive.setRotateValue(axisValue);
        } else {
            robotDrive.setForwardValue(axisValue);
        }
    }
}
