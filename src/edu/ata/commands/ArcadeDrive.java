package edu.ata.commands;

import edu.ata.subsystems.Drivetrain;
import edu.first.identifiers.ReturnableNumber;

public final class ArcadeDrive extends ThreadableCommand {

    private final Drivetrain drivetrain;
    private final ReturnableNumber speed, turn;

    public ArcadeDrive(Drivetrain drivetrain, double speed, double turn, boolean newThread) {
        this(drivetrain, new ReturnableNumber.Number(speed), new ReturnableNumber.Number(turn), newThread);
    }

    public ArcadeDrive(Drivetrain drivetrain, ReturnableNumber speed, ReturnableNumber turn, boolean newThread) {
        super(newThread);
        this.drivetrain = drivetrain;
        this.speed = speed;
        this.turn = turn;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                drivetrain.arcadeDrive(speed.get(), turn.get());
            }
        };
    }
}