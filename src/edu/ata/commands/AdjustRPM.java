package edu.ata.commands;

import edu.ata.subsystems.ShooterWheel;
import edu.first.identifiers.ReturnableNumber;

public final class AdjustRPM extends ThreadableCommand {

    private final ShooterWheel shooterWheel;
    private final ReturnableNumber adjustment;

    public AdjustRPM(ShooterWheel shooterWheel, double adjustment, boolean newThread) {
        this(shooterWheel, new ReturnableNumber.Number(adjustment), newThread);
    }

    public AdjustRPM(ShooterWheel shooterWheel, ReturnableNumber adjustment, boolean newThread) {
        super(newThread);
        this.shooterWheel = shooterWheel;
        this.adjustment = adjustment;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                shooterWheel.setRPM(shooterWheel.getSetpointRPM() + adjustment.get());
            }
        };
    }
}
