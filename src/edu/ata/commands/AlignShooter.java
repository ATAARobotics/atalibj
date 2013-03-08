package edu.ata.commands;

import edu.ata.subsystems.Shooter;

/**
 * Command to align the shooter.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignShooter extends ThreadableCommand {

    private final Shooter shooter;
    private final double setpoint;

    /**
     * Constructs the command with the shooter and the position.
     *
     * @param shooter shooter to align
     * @param setpoint value to align to
     * @param newThread if command should run in a new thread
     */
    public AlignShooter(Shooter shooter, double setpoint, boolean newThread) {
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
