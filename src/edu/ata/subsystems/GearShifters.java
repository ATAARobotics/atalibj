package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.DualActionSolenoid;
import edu.first.module.actuator.Solenoid;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

public final class GearShifters extends Subsystem {

    private final Solenoid solenoid;

    public GearShifters(SolenoidModule second) {
        super(new Module[]{second});
        this.solenoid = second;
    }

    public GearShifters(DualActionSolenoid solenoid) {
        super(new Module[]{solenoid});
        this.solenoid = solenoid;
    }

    public void start() {
        // No thread needed
    }

    public void run() {
        // No thread needed
    }

    public void switchGears() {
        solenoid.set(!solenoid.get());
    }

    public void firstGear() {
        solenoid.set(true);
    }

    public void secondGear() {
        solenoid.set(false);
    }
    
    public int gear() {
        return isFirstGear() ? 1 : 2;
    }

    public boolean isFirstGear() {
        return solenoid.get();
    }

    public boolean isSecondGear() {
        return !solenoid.get();
    }
}