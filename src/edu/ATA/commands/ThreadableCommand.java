package edu.ATA.commands;

import edu.first.command.Command;

public abstract class ThreadableCommand implements Command {

    private final boolean newThread;
    private boolean running;

    protected ThreadableCommand(boolean newThread) {
        this.newThread = newThread;
    }

    public abstract Runnable getRunnable();

    public final void run() {
        if (!running) {
            running = true;
            if (newThread) {
                new Thread(getRunnable()).start();
            } else {
                getRunnable().run();
            }
            running = false;
        }
    }
}
