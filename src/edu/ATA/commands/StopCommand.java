package edu.ATA.commands;

import edu.first.commands.SpeedControllerCommand;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.speedcontroller.SpeedControllerModule;

/**
 * Command meant to stop a speed controller. Is virtually the same as {@link SpeedControllerCommand#SpeedControllerCommand(edu.ATA.module.speedcontroller.SpeedControllerModule, double)
 * SpeedControllerCommand(speedController, 0)}.
 *
 * @author joel
 */
public final class StopCommand {

    private final RobotDriveModule drive;
    private final SpeedControllerModule speedController;

    /**
     * Constructs the command with the speed controller to stop.
     *
     * @param speedControllerModule speed controller to stop
     */
    public StopCommand(SpeedControllerModule speedControllerModule) {
        this.speedController = speedControllerModule;
        this.drive = null;
    }

    /**
     * Constructs the command with a robot drive module that will be stopped.
     *
     * @param drive driving object to stop
     */
    public StopCommand(RobotDriveModule drive) {
        this.speedController = null;
        this.drive = drive;
    }

    public void run() {
        if(speedController != null) {
            speedController.set(0);
        }
        if(drive != null) {
            drive.stopMotors();
        }
    }
}
