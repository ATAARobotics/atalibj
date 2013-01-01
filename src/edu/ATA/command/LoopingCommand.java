package edu.ATA.command;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that runs in a loop. Runs every 10 milliseconds, looping as long as
 * {@link LoopingCommand#continueLoop() continueLoop()} returns true.
 *
 * @author Joel Gallant
 */
public abstract class LoopingCommand implements Command {

    /**
     * Runs {@link LoopingCommand#loop() loop()} in a loop as long as
     * {@link LoopingCommand#continueLoop()} returns true.
     */
    public final void runCommand() {
        while (continueLoop()) {
            loop();
            Timer.delay(0.01);
        }
    }

    /**
     * Tests whether or not to continue running the loop or not.
     *
     * @return if loop should continue running
     */
    public abstract boolean continueLoop();

    /**
     * The main loop to run every 10 milliseconds. Continues to run as long as
     * {@link LoopingCommand#continueLoop()} returns true. This method has no
     * requirements, similar to {@link Command#runCommand()}.
     */
    public abstract void loop();
}
