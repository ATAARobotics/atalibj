package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.DualActionSolenoidModule;
import edu.first.module.actuator.Solenoid;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.subsystem.Subsystem;

public final class BitchBar extends Subsystem {

    private final Solenoid solenoid;

    public BitchBar(SolenoidModule solenoid) {
        super(new Module[]{solenoid});
        this.solenoid = solenoid;
    }

    public BitchBar(DualActionSolenoidModule solenoid) {
        super(new Module[]{solenoid});
        this.solenoid = solenoid;
    }

    public void start() {
        // No thread needed
    }

    public void run() {
        // No thread needed
    }

    protected boolean disableSubsystem() {
        setIn();
        return true;
    }

    public void switchPosition() {
        solenoid.set(!solenoid.get());
    }

    public void setOut() {
        solenoid.set(true);
    }

    public void setIn() {
        solenoid.set(false);
    }

    public boolean isOut() {
        return solenoid.get();
    }
}