package edu.ATA.commands;

import edu.ATA.twolf.subsystems.Shooter;
import edu.first.command.Command;
import edu.first.module.target.BangBangModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class AutoShoot implements Command {

    private final Shooter shooter;
    private final BangBangModule bangBang;
    private final boolean newThread;
    private final Runnable run = new Runnable() {
        public void run() {
            if (bangBang.pastSetpoint()) {
                shooter.shoot();
            }
        }
    };

    public AutoShoot(Shooter shooter, BangBangModule bangBang, boolean newThread) {
        this.shooter = shooter;
        this.bangBang = bangBang;
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
