package edu.first.module.driving;

import edu.first.bindings.AxisBind;

/**
 * An axis bind that binds the axis to a side of the robot. This means it uses
 * {@link RobotDriveModule#setRightMotorOutput(double)} and
 * {@link RobotDriveModule#setLeftMotorOutput(double)}.
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public final class SideBinding implements AxisBind {

    /**
     *
     */
    public static final Side RIGHT = new Side(Side.RIGHT),
    /**
     *
     */
    LEFT = new Side(Side.LEFT);
    private final RobotDriveModule driveModule;
    private final Side side;

    /**
     * Object representing left or right side.
     */
    public static class Side {

        private static final int RIGHT = 1, LEFT = 2;
        private final int side;

        private Side(int side) {
            this.side = side;
        }
    }

    /**
     * Constructs the bind using the robot drive module and which side. Make
     * sure the drive module is enabled before using.
     *
     * @param driveModule driving object
     * @param side side to drive
     */
    public SideBinding(RobotDriveModule driveModule, Side side) {
        this.driveModule = driveModule;
        this.side = side;
    }

    /**
     * Sets the side to the value given.
     *
     * @param axisValue value from joystick
     */
    public void set(double axisValue) {
        if (side.side == Side.RIGHT) {
            driveModule.setRightMotorOutput(axisValue);
        } else {
            driveModule.setLeftMotorOutput(axisValue);
        }
    }
}
