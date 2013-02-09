package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.actuator.SolenoidModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GearShift implements CommandBind {

    private final SolenoidModule gearUp, gearDown;

    public GearShift(SolenoidModule gearUp, SolenoidModule gearDown) {
        this.gearUp = gearUp;
        this.gearDown = gearDown;
    }

    public void run() {
        gearUp.set(!gearUp.get());
        gearDown.set(!gearDown.get());
    }
}
