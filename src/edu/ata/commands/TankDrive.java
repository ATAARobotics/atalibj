package edu.ata.commands;

import edu.ata.subsystems.Drivetrain;
import edu.first.identifiers.ReturnableNumber;

public final class TankDrive extends ThreadableCommand {

    private final Drivetrain drivetrain;
    private final ReturnableNumber left, right;

    public TankDrive(Drivetrain drivetrain, double left, double right, boolean newThread) {
        this(drivetrain, new ReturnableNumber.Number(left), new ReturnableNumber.Number(right), newThread);
    }

    public TankDrive(Drivetrain drivetrain, ReturnableNumber left, ReturnableNumber right, boolean newThread) {
        super(newThread);
        this.drivetrain = drivetrain;
        this.left = left;
        this.right = right;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                drivetrain.tankDrive(left.get(), right.get());
            }
        };
    }
}