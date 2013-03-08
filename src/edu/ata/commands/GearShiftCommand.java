package edu.ata.commands;

import edu.ata.subsystems.GearShifters;

/**
 * Command class for gear shifter used for switching gears on two speed
 * gearboxes.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class GearShiftCommand extends ThreadableCommand {

    private final GearShifters gearShifters;

    /**
     * Constructs the command with the gear shifting system.
     *
     * @param gearShifters shifters to switch
     * @param newThread if the command should be run in a new thread
     */
    public GearShiftCommand(GearShifters gearShifters, boolean newThread) {
        super(newThread);
        this.gearShifters = gearShifters;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                gearShifters.shift();
            }
        };
    }
}
