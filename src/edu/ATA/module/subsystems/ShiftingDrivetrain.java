package edu.ATA.module.subsystems;

import edu.ATA.module.Module;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.actuator.SolenoidModule;
import edu.ATA.module.subsystem.Subsystem;

public class ShiftingDrivetrain extends Subsystem {

    private final RobotDriveModule driveModule;
    private final SolenoidModule firstGear, secondGear;

    public ShiftingDrivetrain(RobotDriveModule driveModule,
            SolenoidModule firstGear, SolenoidModule secondGear) {
        super(new Module[]{driveModule, firstGear, secondGear});
        this.driveModule = driveModule;
        this.firstGear = firstGear;
        this.secondGear = secondGear;
    }
}
