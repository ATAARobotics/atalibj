package edu.ata.commands;

import edu.ata.subsystems.Shooter;

/**
 * Command to align the shooter to a setpoint.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class ShooterAlignCommand extends ThreadableCommand {

    private final Shooter shooter;
    private final double setpoint;

    /**
     * Constructs the command using the shooter to align and where to align it
     * to.
     *
     * @param shooter shooter to align
     * @param setpoint setpoint to align the shooter to
     * @param newThread if command should run in a new thread
     */
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
