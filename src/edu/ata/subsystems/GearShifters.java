package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

/**
 * System to between two gears. Is basically a {@link ReversingSolenoids}, but
 * uses {@code shift()} and {@code gear()} to be clear.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class GearShifters extends Subsystem {

    private final ReversingSolenoids reversingSolenoids;

    /**
     * Constructs the shifter with a {@link ReversingSolenoids} object.
     *
     * @param reversingSolenoids duel solenoid system to switch gears
     */
    public GearShifters(SolenoidModule gearOne, SolenoidModule gearTwo) {
        this(new ReversingSolenoids(gearOne, gearTwo));
    }

    private GearShifters(ReversingSolenoids reversingSolenoids) {
        super(new Module[]{reversingSolenoids});
        this.reversingSolenoids = reversingSolenoids;
    }

    /**
     * Shifts gears.
     */
    public void shift() {
        reversingSolenoids.switchPosition();
    }

    /**
     * Sets the gear to the first gear.
     */
    public void setFirstGear() {
        reversingSolenoids.setIn();
    }

    /**
     * Sets the gear to the second gear.
     */
    public void setSecondGear() {
        reversingSolenoids.setOut();
    }

    /**
     * Returns the gear number currently selected.
     *
     * @return 1 or 2
     */
    public int gear() {
        return reversingSolenoids.isIn() ? 1 : 2;
    }
}