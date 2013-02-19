/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ATA.commands;

import edu.first.command.Command;
import edu.first.module.driving.RobotDriveModule;

/**
 *
 * @author denis
 */
public class ArcadeDriveCommand implements Command {

    private final RobotDriveModule drive;
    private final double forward;
    private final double turn;

    public ArcadeDriveCommand(RobotDriveModule drive, double forward, double turn) {
        this.drive = drive;
        this.forward = forward;
        this.turn = turn;
    }

    public void run() {
        drive.arcadeDrive(forward, turn);
    }
}
