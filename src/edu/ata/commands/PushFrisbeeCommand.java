package edu.ata.commands;

import edu.ata.subsystems.Shooter;

public class PushFrisbeeCommand extends ThreadableCommand {

    private final Shooter shooter;

    public PushFrisbeeCommand(Shooter shooter, boolean newThread) {
        super(newThread);
        this.shooter = shooter;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                shooter.pushFrisbees();
            }
        };
    }
}