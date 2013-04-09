package edu.ata.commands;

import edu.ata.subsystems.MovementSystem;
import edu.first.identifiers.ReturnableNumber;

public final class TurnToAngle extends ThreadableCommand {

    private final MovementSystem movementSystem;
    private final ReturnableNumber number;

    public TurnToAngle(MovementSystem movementSystem, double number, boolean newThread) {
        this(movementSystem, new ReturnableNumber.Number(number), newThread);
    }

    public TurnToAngle(MovementSystem movementSystem, ReturnableNumber number, boolean newThread) {
        super(newThread);
        this.movementSystem = movementSystem;
        this.number = number;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                movementSystem.turnToAngle(number.get());
            }
        };
    }
}