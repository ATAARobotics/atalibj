package edu.ATA.commands;

import edu.first.command.Command;
import edu.first.module.actuator.SolenoidModule;

/**
 * This is the command class for gear shifter. Used for setting gear shifter
 * gears.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GearShift implements Command {

    private final SolenoidModule gearUp, gearDown;
    private boolean track = false;

    public GearShift(SolenoidModule gearUp, SolenoidModule gearDown) {
        this.gearUp = gearUp;
        this.gearDown = gearDown;
    }

    public void run() {
        track = !track;
        gearUp.set(track);
        gearDown.set(!track);
    }
}
