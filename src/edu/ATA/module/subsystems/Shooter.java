package edu.ATA.module.subsystems;

import edu.ATA.command.Command;
import edu.ATA.commands.SpeedControllerCommand;
import edu.ATA.module.Module;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.SolenoidModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.speedcontroller.SpikeRelay;
import edu.ATA.module.speedcontroller.SpikeRelayModule;
import edu.ATA.module.subsystem.Subsystem;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends Subsystem {

    private final XboxController controller;
    private final SpikeRelayModule compressor;
    private final SpeedControllerModule shooter;
    private final SolenoidModule loader;
    private final SolenoidModule reloader;

    public Shooter(XboxController controller,
            SpikeRelayModule compressor, SpeedControllerModule shooter,
            SolenoidModule loader, SolenoidModule reloader) {
        super(new Module[]{controller, compressor, shooter, loader, reloader});
        this.controller = controller;
        this.compressor = compressor;
        this.shooter = shooter;
        this.loader = loader;
        this.reloader = reloader;
        init();
    }

    private void init() {
        controller.bindWhenPressed(XboxController.RIGHT_BUMPER, new Command() {
            public void run() {
                loader.set(true);
                reloader.set(false);
                Timer.delay(0.8);
                loader.set(false);
                reloader.set(true);
            }
        });
        controller.bindWhilePressed(XboxController.RIGHT_STICK, new SpeedControllerCommand(shooter, 1));
        controller.bindWhilePressed(XboxController.A, new SpeedControllerCommand(shooter, 0.9));
        controller.bindWhilePressed(XboxController.B, new SpeedControllerCommand(shooter, 0.8));
        controller.bindWhilePressed(XboxController.Y, new SpeedControllerCommand(shooter, 0.7));
        controller.bindWhilePressed(XboxController.X, new SpeedControllerCommand(shooter, 0.6));
        controller.bindWhilePressed(XboxController.LEFT_BUMPER, new SpeedControllerCommand(shooter, 0.5));
        controller.bindWhenPressed(XboxController.LEFT_STICK, new SpeedControllerCommand(shooter, 0));
    }

    public void teleop() {
        compressor.set(SpikeRelay.FORWARD);
        controller.doBinds();
    }
}
