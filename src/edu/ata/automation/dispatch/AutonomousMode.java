package edu.ata.automation.dispatch;

import edu.ata.automation.autonomous.AutonomousStructure;

/**
 * {@link GameMode} object designed for autonomous modes. Is interruptible, but
 * will not just 'kill' the process. The interruptible behavior must be inserted
 * into the actual running code. The mode will not run if it has been
 * interrupted before running.
 *
 * @author Joel Gallant
 */
public abstract class AutonomousMode extends InterruptibleGameMode implements AutonomousStructure {

    /**
     * Creates the autonomous mode with a name. The thread will run on priority
     * 7 (medium-high).
     *
     * @param name name of the autonomous mode
     */
    public AutonomousMode(String name) {
        super(name, 7);
    }

    /**
     * Returns whether the mode is finished running. Will always return the same
     * as {@code !obj.isAlive()}. This method is just to make it clearer if the
     * mode is 'finished'.
     *
     * @return whether it is finished.
     */
    public final boolean isFinished() {
        return !isAlive();
    }

    /**
     * Method to be run when autonomous mode is run. Handles it's inner methods.
     * Is the symbolic opposite to {@link InterruptibleGameMode#close()}.
     * {@code open()} is called in {@link GameMode#run()}, while {@code close()}
     * is called in {@link GameMode#stop()}.
     */
    public final void open() {
        if (!isInterrupted()) {
            init();
            main();
        }
    }

    /**
     * Method used to be run after {@link GameMode#run()} is run.
     * {@link InterruptibleGameMode} guarantees that this method is run after,
     * and only after {@link AutonomousMode#run()} is finished.
     *
     * <p> There is a 10 second timeout when {@code run()} can tell
     * {@code stop()} that it can run, and after 10 seconds, regardless of
     * whether {@code run()} is finished, {@code stop()} will be run.
     *
     * <p> {@link AutonomousMode#close()} is run inside of
     * {@link InterruptibleGameMode#stop()}.
     */
    public abstract void close();
}