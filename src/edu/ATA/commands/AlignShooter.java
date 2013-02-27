package edu.ATA.commands;

import edu.ATA.twolf.subsystems.Shooter;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignShooter extends ThreadableCommand {

    private final Shooter shooter;
    private final double setpoint;

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
