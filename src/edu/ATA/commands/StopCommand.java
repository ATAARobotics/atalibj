package edu.ATA.commands;

import edu.first.module.driving.RobotDriveModule;

public final class StopCommand extends ThreadableCommand {

    private final RobotDriveModule drive;

    public StopCommand(RobotDriveModule drive, boolean newThread) {
        super(newThread);
        this.drive = drive;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                drive.stopMotors();
            }
        };
    }
}
