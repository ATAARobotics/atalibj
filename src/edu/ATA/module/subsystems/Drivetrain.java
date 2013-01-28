package edu.ATA.module.subsystems;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.Module;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.driving.SideBinding;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.SolenoidModule;
import edu.ATA.module.speedcontroller.SpikeRelay;
import edu.ATA.module.speedcontroller.SpikeRelayModule;
import edu.ATA.module.subsystem.Subsystem;

public class Drivetrain extends Subsystem {

    private final RobotDriveModule driveModule;
    private final XboxController controller;
    private final SpikeRelayModule compressor;
    private final SolenoidModule left1, left2, right1, right2;

    public Drivetrain(RobotDriveModule driveModule, XboxController controller,
            SpikeRelayModule compressor, SolenoidModule left1, SolenoidModule left2,
            SolenoidModule right1, SolenoidModule right2) {
        super(new Module[]{driveModule, controller, compressor, left1, left2, right1, right2});
        this.driveModule = driveModule;
        this.controller = controller;
        this.compressor = compressor;
        this.left1 = left1;
        this.left2 = left2;
        this.right1 = right1;
        this.right2 = right2;
    }

    public boolean enable() {
        init();
        return super.enable();
    }

    private void init() {
        controller.removeAllBinds();
        controller.bindAxis(XboxController.RIGHT_FROM_MIDDLE, new SideBinding(driveModule, SideBinding.RIGHT));
        controller.bindAxis(XboxController.LEFT_FROM_MIDDLE, new SideBinding(driveModule, SideBinding.LEFT));
        controller.bindWhenPressed(XboxController.RIGHT_BUMPER, new CommandBind() {
            public void run() {
                if (left1.get() && right1.get()) {
                    left1.set(false);
                    left2.set(true);
                    right1.set(false);
                    right2.set(true);
                } else {
                    left1.set(true);
                    left2.set(false);
                    right1.set(true);
                    right2.set(false);
                }
            }
        });
    }

    public void teleop() {
        compressor.set(SpikeRelay.FORWARD);
        controller.doBinds();
    }
}
