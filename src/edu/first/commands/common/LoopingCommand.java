package edu.first.commands.common;

import edu.first.command.Command;

/**
 * Command that runs in a loop until a condition returns false.
 *
 * @since June 17 13
 * @author Joel Gallant
 */
public abstract class LoopingCommand implements Command {
    private boolean first = true;

    public LoopingCommand() {
    }

    /**
     * Runs {@link #runLoop()} until {@link #continueLoop()} returns false.
     */
    @Override
    public final void run() {
        while(this.continueLoop()) {
            if (this.first) {
                this.first = false;

                try {
                    if (this.getClass().getMethod("firstLoop").getDeclaringClass().getTypeName().endsWith("LoopingCommand")) {
                        this.runLoop();
                    } else {
                        this.firstLoop();
                    }
                } catch (SecurityException | NoSuchMethodException var2) {
                    throw new RuntimeException(var2);
                }
            } else {
                this.runLoop();
            }
        }

        this.end();
    }

    public void firstLoop() {
    }

    public void end() {
    }

    public abstract boolean continueLoop();

    public abstract void runLoop();
}
