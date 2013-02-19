package edu.ATA.commands;

import edu.ATA.twolf.subsystems.Shooter;

/**
 * The command class for the shooter shooting.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class ShootCommand extends ThreadableCommand {

    private final Shooter shooter;

    public ShootCommand(Shooter shooter, boolean newThread) {
        super(newThread);
        this.shooter = shooter;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                shooter.shoot();
            }
        };
    }
}
