package edu.ATA.module.driving;

import edu.ATA.bindings.AxisBind;

/**
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class SideBinding implements AxisBind {

    public static final Side RIGHT = new Side(Side.RIGHT), LEFT = new Side(Side.LEFT);
    private final RobotDriveModule driveModule;
    private final Side side;

    public static class Side {

        private static final int RIGHT = 1, LEFT = 2;
        private final int side;

        private Side(int side) {
            this.side = side;
        }
    }

    public SideBinding(RobotDriveModule driveModule, Side side) {
        this.driveModule = driveModule;
        this.side = side;
    }

    public void set(double axisValue) {
        if (side.side == Side.RIGHT) {
            driveModule.setRightMotorOutput(axisValue);
        } else {
            driveModule.setLeftMotorOutput(axisValue);
        }
    }
}
