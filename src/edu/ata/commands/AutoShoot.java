package edu.ata.commands;

import edu.ata.subsystems.Loader;
import edu.ata.subsystems.ShooterWheel;

public final class AutoShoot extends ThreadableCommand {

    private final ShooterWheel shooterWheel;
    private final Loader loader;

    public AutoShoot(ShooterWheel shooterWheel, Loader loader, boolean newThread) {
        super(newThread);
        this.shooterWheel = shooterWheel;
        this.loader = loader;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                if(shooterWheel.isPastSetpoint()) {
                    loader.fire();
                }
            }
        };
    }
}