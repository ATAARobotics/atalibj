package edu.ATA.module.subsystems;

import edu.ATA.module.Module;
import edu.ATA.module.driving.ArcadeBinding;
import edu.ATA.module.driving.GearShift;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.actuator.SolenoidModule;
import edu.ATA.module.subsystem.Subsystem;

public class ShiftingDrivetrain extends Subsystem {

    private final RobotDriveModule driveModule;
    private final XboxController controller;
    private final SolenoidModule firstGear, secondGear;

    public ShiftingDrivetrain(RobotDriveModule driveModule, XboxController controller,
            SolenoidModule firstGear, SolenoidModule secondGear) {
        super(new Module[]{driveModule, controller, firstGear, secondGear});
        this.driveModule = driveModule;
        this.controller = controller;
        this.firstGear = firstGear;
        this.secondGear = secondGear;
    }

    public void bindAll() {
        // Drivetrain
        controller.bindAxis(XboxController.LEFT_Y, new ArcadeBinding(driveModule, ArcadeBinding.FORWARD));
        controller.bindAxis(XboxController.RIGHT_X, new ArcadeBinding(driveModule, ArcadeBinding.ROTATE));
        // Gear shifting
        controller.bindWhenPressed(XboxController.LEFT_BUMPER, new GearShift(secondGear, firstGear));
    }

    public void removeBinds() {
        controller.removeAllBinds();
    }
}
