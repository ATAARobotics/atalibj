package edu.ATA.commands;

import edu.ATA.twolf.subsystems.Shooter;
import edu.first.command.Command;

/**
 * The command class for the shooter shooting.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ShootCommand implements Command {

    private final Shooter shooter;
    private final boolean newThread;
    private final Runnable run = new Runnable() {
        public void run() {
            shooter.shoot();
        }
    };

    public ShootCommand(Shooter shooter, boolean newThread) {
        this.shooter = shooter;
        this.newThread = newThread;
    }

    public void run() {
        if (newThread) {
            new Thread(run).start();
        } else {
            run.run();
        }
    }
}
