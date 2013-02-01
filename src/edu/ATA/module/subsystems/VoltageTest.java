package edu.ATA.module.subsystems;

import edu.ATA.bindings.CommandBind;
import edu.ATA.main.Logger;
import edu.ATA.module.Module;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.SolenoidModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.speedcontroller.SpikeRelay;
import edu.ATA.module.speedcontroller.SpikeRelayModule;
import edu.ATA.module.subsystem.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author joel
 */
public class VoltageTest extends Subsystem {

    private final XboxController controller;
    private final SpeedControllerModule victor;
    private final SpikeRelayModule spikeRelay;
    private final SolenoidModule loader;
    private final SolenoidModule reloader;
    private double speed = 0;

    public VoltageTest(XboxController controller, SpeedControllerModule victor, SpikeRelayModule spikeRelay, SolenoidModule loader, SolenoidModule reloader) {
        super(new Module[]{controller, victor, spikeRelay, loader, reloader});
        this.controller = controller;
        this.victor = victor;
        this.spikeRelay = spikeRelay;
        this.loader = loader;
        this.reloader = reloader;
    }

    public boolean enable() {
        init();
        return super.enable();
    }

    private void init() {
        controller.removeAllBinds();
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
        controller.bindWhenPressed(XboxController.Y, new CommandBind() {
            public void run() {
                victor.set(speed -= speed);
                SmartDashboard.putNumber("ShooterSpeed", victor.get());
            }
        });
        controller.bindWhenPressed(XboxController.RIGHT_BUMPER, new CommandBind() {
            public void run() {
                Logger.log(Logger.Urgency.STATUSREPORT, "Shooter fired.");
                loader.set(true);
                reloader.set(false);
                Timer.delay(0.8);
                loader.set(false);
                reloader.set(true);
            }
        });
    }

    public void teleop() {
        spikeRelay.set(SpikeRelay.FORWARD);
        controller.doBinds();
    }
}
