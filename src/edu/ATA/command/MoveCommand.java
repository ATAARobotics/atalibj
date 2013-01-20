package edu.ATA.command;

import edu.ATA.module.driving.RobotDriveModule;

/**
 * Basic command to move a robot drive module. Moves straight.
 *
 * @author joel
 */
public abstract class MoveCommand extends LoopingCommand {

    private final RobotDriveModule driveModule;
    private final double speed;

    /**
     * Constructs command using the robot drive and speed. Assumes that the
     * drive module is enabled.
     *
     * @param driveModule drivetrain to move with
     * @param speed speed to move at
     */
    public MoveCommand(RobotDriveModule driveModule, double speed) {
        this.driveModule = driveModule;
        this.speed = speed;
    }

    /**
     * Moves the robot straight at the given speed in the constructor.
     */
    public void loop() {
        driveModule.setLeftRightMotorOutputs(speed, speed);
    }
}
