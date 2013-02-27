package edu.ATA.commands;

import edu.ATA.twolf.subsystems.Shooter;
import edu.first.module.target.BangBangModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AutoShoot extends ThreadableCommand {

    private final Shooter shooter;
    private final BangBangModule bangBang;

    public AutoShoot(Shooter shooter, BangBangModule bangBang, boolean newThread) {
        super(newThread);
        this.shooter = shooter;
        this.bangBang = bangBang;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if (bangBang.pastSetpoint()) {
                    shooter.shoot();
                }
            }
        };
    }
}
