package edu.ata.commands;

import edu.ata.subsystems.Shooter;
import edu.first.module.target.BangBangModule;

/**
 * Command to fire the shooter depending on whether the it is at the correct
 * RPM.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AutoShoot extends ThreadableCommand {

    private final Shooter shooter;
    private final BangBangModule bangBang;

    /**
     * Constructs the command using the shooter and the bang bang controller the
     * wheel.
     *
     * @param shooter shooting system
     * @param bangBang shooter control
     * @param newThread if command should run in a new thread
     */
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
