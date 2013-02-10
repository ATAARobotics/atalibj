package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.actuator.SolenoidModule;

/**
 * This is the command class for gear shifter. Used for setting gear shifter
 * gears.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GearShift implements CommandBind {

    private final SolenoidModule gearUp, gearDown;

    /**
     * Shifts the gears on the gear shifter.
     *
     * @param gearUp this puts the gear shifter in gear 2
     * @param gearDown this the gear shifter in gear 1
     */
    public GearShift(SolenoidModule gearUp, SolenoidModule gearDown) {
        this.gearUp = gearUp;
        this.gearDown = gearDown;
    }

    public void run() {
        gearUp.set(!gearUp.get());
        gearDown.set(!gearDown.get());
    }
}
