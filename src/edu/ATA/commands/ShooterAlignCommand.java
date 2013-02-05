package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.subsystems.Shooter;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ShooterAlignCommand implements CommandBind {

    private final Shooter shooter;
    private final double setpoint;

    public ShooterAlignCommand(Shooter shooter, double setpoint) {
        this.shooter = shooter;
        this.setpoint = setpoint;
    }

    public void run() {
        shooter.alignTo(setpoint);
    }
}
