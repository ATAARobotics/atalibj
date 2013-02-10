package edu.ATA.commands;

import edu.ATA.bindings.CommandBind;
import edu.ATA.module.subsystems.Shooter;

/**
 * Command class to align the shooter to a setpoint.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ShooterAlignCommand implements CommandBind {

    private final Shooter shooter;
    private final double setpoint;

    /**
     * Sets the shooter alignment to a setpoint.
     *
     * @param shooter the shooter object used.
     * @param setpoint the setpoint for the alignment.
     */
    public ShooterAlignCommand(Shooter shooter, double setpoint) {
        this.shooter = shooter;
        this.setpoint = setpoint;
    }

    public void run() {
        shooter.alignTo(setpoint);
    }
}
