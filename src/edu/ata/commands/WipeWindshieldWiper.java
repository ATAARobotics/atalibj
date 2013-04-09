package edu.ata.commands;

import edu.ata.subsystems.WindshieldWiper;

public final class WipeWindshieldWiper extends ThreadableCommand {

    private final WindshieldWiper windshieldWiper;

    public WipeWindshieldWiper(WindshieldWiper windshieldWiper, boolean newThread) {
        super(newThread);
        this.windshieldWiper = windshieldWiper;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                windshieldWiper.wipe();
            }
        };
    }
}