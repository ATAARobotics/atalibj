/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;

/**
 *
 * @author denis
 */
public class StopCommmand extends ThreadableCommand {

    private final ShiftingDrivetrain drive;

    public StopCommmand(ShiftingDrivetrain drive, boolean newThread) {
        super(newThread);
        this.drive = drive;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                drive.stop();
            }
        };
    }
}
