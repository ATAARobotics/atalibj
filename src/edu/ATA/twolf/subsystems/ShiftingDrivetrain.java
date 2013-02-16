package edu.ATA.twolf.subsystems;

import edu.ATA.commands.GearShift;
import edu.first.module.Module;
import edu.first.module.actuator.SolenoidModule;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.subsystem.Subsystem;

/**
 *
 * @author Team 4334
 */
public class ShiftingDrivetrain extends Subsystem {

    private final RobotDriveModule driveModule;
    private final SolenoidModule firstGear, secondGear;

    public ShiftingDrivetrain(RobotDriveModule driveModule, SolenoidModule firstGear, SolenoidModule secondGear) {
        super(new Module[]{driveModule, firstGear, secondGear});
        this.driveModule = driveModule;
        this.firstGear = firstGear;
        this.secondGear = secondGear;
    }

    /**
     *
     * @param speed
     * @param turn
     */
    public void arcadeDrive(double speed, double turn) {
        driveModule.arcadeDrive(speed, turn);
    }

    /**
     *
     * @param left
     * @param right
     */
    public void tankDrive(double left, double right) {
        driveModule.tankDrive(left, right);
    }

    /**
     *
     */
    public void stop() {
        driveModule.stopMotors();
    }

    /**
     *
     */
    public void shiftGears() {
        new GearShift(firstGear, secondGear).run();
    }
}
