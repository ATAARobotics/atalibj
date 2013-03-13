package edu.ata.commands;

import edu.ata.subsystems.GearShifters;

/**
 * Command class for gear shifter used for switching gears on two speed
 * gearboxes.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class GearShiftCommand extends ThreadableCommand {

    public static final ShiftType SHIFT = new ShiftType(ShiftType.SHIFT);
    public static final ShiftType FIRST = new ShiftType(ShiftType.FIRST);
    public static final ShiftType SECOND = new ShiftType(ShiftType.SECOND);
    private final GearShifters gearShifters;
    private final ShiftType type;

    /**
     * Constructs the command with the gear shifting system.
     *
     * @param gearShifters shifters to switch
     * @param type the kind of gear shift to do
     * @param newThread if the command should be run in a new thread
     */
    public GearShiftCommand(GearShifters gearShifters, ShiftType type, boolean newThread) {
        super(newThread);
        this.gearShifters = gearShifters;
        this.type = type;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (type.type == SHIFT.type) {
                    gearShifters.shift();
                } else if (type.type == FIRST.type) {
                    gearShifters.setFirstGear();
                } else if (type.type == SECOND.type) {
                    gearShifters.setSecondGear();
                }
            }
        };
    }

    /**
     * Class representing the type of shift to be done.
     */
    public static final class ShiftType {

        private static final int SHIFT = 1, FIRST = 2, SECOND = 3;
        private final int type;

        private ShiftType(int type) {
            this.type = type;
        }
    }
}
