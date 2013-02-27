package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;

/**
 * This is the command class for gear shifter. Used for setting gear shifter
 * gears.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class GearShift extends ThreadableCommand {

    private final ShiftingDrivetrain drivetrain;

    public GearShift(ShiftingDrivetrain drivetrain, boolean newThread) {
        super(newThread);
        this.drivetrain = drivetrain;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                drivetrain.shiftGears();
            }
        };
    }
}
