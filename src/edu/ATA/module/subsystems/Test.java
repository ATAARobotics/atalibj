package edu.ATA.module.subsystems;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.Module;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.subsystem.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author joel
 */
public class Test extends Subsystem {

    private final XboxController controller;
    private final SpeedControllerModule victor;
    private double speed = 0;

    public Test(XboxController controller, final SpeedControllerModule victor) {
        super(new Module[]{controller, victor});
        this.controller = controller;
        this.victor = victor;
    }

    public boolean enable() {
        init();
        return super.enable();
    }

    private void init() {
        controller.bindWhenReleased(XboxController.A, new CommandBind() {
            public void run() {
                victor.set(speed += 0.01);
                SmartDashboard.putNumber("ShooterSpeed", victor.get());
            }
        });
        controller.bindWhenPressed(XboxController.B, new CommandBind() {
            public void run() {
                victor.set(speed -= 0.01);
                SmartDashboard.putNumber("ShooterSpeed", victor.get());
            }
        });
    }

    public void teleop() {
        controller.doBinds();
    }
}
