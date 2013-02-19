package edu.ATA.commands;

import edu.ATA.twolf.subsystems.Shooter;

/**
 * Command class to align the shooter to a setpoint.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class ShooterAlignCommand extends ThreadableCommand {

    private final Shooter shooter;
    private final double setpoint;

    public ShooterAlignCommand(Shooter shooter, double setpoint, boolean newThread) {
        super(newThread);
        this.shooter = shooter;
        this.setpoint = setpoint;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                shooter.alignTo(setpoint);
            }
        };
    }
}
