package edu.ata.commands;

import edu.ata.subsystems.Shooter;

/**
 * Command to shoot.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class ShootCommand extends ThreadableCommand {

    private final Shooter shooter;

    /**
     * Constructs the command using the shooter to shoot with.
     *
     * @param shooter shooting mechanism
     * @param newThread if command should run in a new thread
     */
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
