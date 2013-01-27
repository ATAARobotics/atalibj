package edu.ATA.commands;

import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;

/**
 * Command meant to stop a speed controller. Is virtually the same as {@link SpeedControllerCommand#SpeedControllerCommand(edu.ATA.module.speedcontroller.SpeedControllerModule, double)
 * SpeedControllerCommand(speedController, 0)}.
 *
 * @author joel
 */
public final class StopCommand extends SpeedControllerCommand {

    private RobotDriveModule drive;

    /**
     * Constructs the command with the speed controller to stop.
     *
     * @param speedControllerModule speed controller to stop
     */
    public StopCommand(SpeedControllerModule speedControllerModule) {
        super(speedControllerModule, 0);
    }

    /**
     * Constructs the command with a robot drive module that will be stopped.
     *
     * @param drive driving object to stop
     */
    public StopCommand(RobotDriveModule drive) {
        super(null, 0);
        this.drive = drive;
    }

    public void run() {
        if (drive == null) {
            super.run();
        } else {
            drive.stopMotors();
        }
    }
}
