package edu.ata.commands;

import edu.ata.subsystems.MovementSystem;
import edu.first.identifiers.ReturnableNumber;

public final class DriveDistance extends ThreadableCommand {

    private final MovementSystem movementSystem;
    private final ReturnableNumber number;

    public DriveDistance(MovementSystem movementSystem, double number, boolean newThread) {
        this(movementSystem, new ReturnableNumber.Number(number), newThread);
    }

    public DriveDistance(MovementSystem movementSystem, ReturnableNumber number, boolean newThread) {
        super(newThread);
        this.movementSystem = movementSystem;
        this.number = number;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                movementSystem.driveToDistance(number.get());
            }
        };
    }
}