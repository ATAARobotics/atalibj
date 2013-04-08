package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.DualActionSolenoid;
import edu.first.module.actuator.Solenoid;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

public final class BitchBar extends Subsystem {

    private final Solenoid solenoid;

    public BitchBar(SolenoidModule solenoid) {
        super(new Module[]{solenoid});
        this.solenoid = solenoid;
    }

    public BitchBar(DualActionSolenoid solenoid) {
        super(new Module[]{solenoid});
        this.solenoid = solenoid;
    }

    public void start() {
        // No thread needed
    }

    public void run() {
        // No thread needed
    }
}