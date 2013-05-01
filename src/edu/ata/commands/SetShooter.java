package edu.ata.commands;

import edu.ata.subsystems.ShooterWheel;
import edu.first.identifiers.ReturnableNumber;

public final class SetShooter extends ThreadableCommand {

    private final ShooterWheel shooterWheel;
    private final ReturnableNumber rpm;

    public SetShooter(ShooterWheel shooterWheel, double rpm, boolean newThread) {
        this(shooterWheel, new ReturnableNumber.Number(rpm), newThread);
    }

    public SetShooter(ShooterWheel shooterWheel, ReturnableNumber rpm, boolean newThread) {
        super(newThread);
        this.shooterWheel = shooterWheel;
        this.rpm = rpm;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                shooterWheel.setRPM(rpm.get());
            }
        };
    }
}