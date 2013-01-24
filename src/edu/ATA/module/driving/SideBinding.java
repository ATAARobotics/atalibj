package edu.ATA.module.driving;

import edu.ATA.module.joystick.BindableJoystick;

/**
 *
 * @author Joel Gallant <joel.gallant236@gmail.com>
 */
public class SideBinding implements BindableJoystick.BindedAxis {

    public static final int RIGHT = 1, LEFT = 2;
    private final RobotDriveModule driveModule;
    private final int side;

    public SideBinding(RobotDriveModule driveModule, int side) {
        this.driveModule = driveModule;
        this.side = side;
    }

    public void set(double axisValue) {
        if (side == RIGHT) {
            driveModule.setRightMotorOutput(axisValue);
        } else {
            driveModule.setLeftMotorOutput(axisValue);
        }
    }
}
