package edu.ATA.commands;

import edu.ATA.twolf.subsystems.Shooter;
import edu.first.command.Command;

/**
 * Command class to align the shooter to a setpoint.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class ShooterAlignCommand implements Command {

    private final Shooter shooter;
    private final double setpoint;
    private final boolean newThread;
    private final Runnable run = new Runnable() {
        public void run() {
            shooter.alignTo(setpoint);
        }
    };

    public ShooterAlignCommand(Shooter shooter, double setpoint, boolean newThread) {
        this.shooter = shooter;
        this.setpoint = setpoint;
        this.newThread = newThread;
    }

    public void run() {
        if(newThread) {
            new Thread(run).start();
        } else {
            run.run();
        }
    }
}
