/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;
import edu.first.command.Command;
import edu.first.module.driving.RobotDriveModule;

/**
 *
 * @author denis
 */
public class TankDriveCommand implements Command {

    private final ShiftingDrivetrain drive;
    private final double left;
    private final double right;

    public TankDriveCommand(ShiftingDrivetrain drive, double left, double right) {
        this.drive = drive;
        this.left = left;
        this.right = right;
    }
    

    public void run() {
        drive.arcadeDrive(left, right);
    }
}
