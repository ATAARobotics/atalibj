package edu.ATA.module.subsystems;

import edu.ATA.bindings.CommandBind;
import edu.ATA.command.Commands;
import edu.ATA.module.Module;
import edu.ATA.module.sensor.PotentiometerModule;
import edu.ATA.module.actuator.SolenoidModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.subsystem.Subsystem;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends Subsystem {

    private final SolenoidModule loader;
    private final SolenoidModule reloader;
    private final PotentiometerModule pot;
    private final SpeedControllerModule alignment;

    public Shooter(SolenoidModule loader, SolenoidModule reloader,
            PotentiometerModule pot, SpeedControllerModule alignment) {
        super(new Module[]{loader, reloader, pot, alignment});
        this.loader = loader;
        this.reloader = reloader;
        this.pot = pot;
        this.alignment = alignment;
    }

    public void shoot() {
        Commands.runInNewThread(new ShootCommand());
    }

    public void alignTo(double setpoint) {
        Commands.runInNewThread(new AlignCommand(setpoint));
    }

    private class ShootCommand implements CommandBind {

        public void run() {
            loader.set(true);
            reloader.set(false);
            Timer.delay(0.75);
            loader.set(false);
            reloader.set(true);
        }
    }

    private class AlignCommand implements CommandBind {

        private final double setpoint;

        public AlignCommand(double setpoint) {
            this.setpoint = setpoint;
        }

        public void run() {
            while (pot.getPosition() > setpoint) {
                alignment.set(-1);
            }
            while (pot.getPosition() < setpoint) {
                alignment.set(1);
            }
            alignment.set(0);
        }
    }
}
