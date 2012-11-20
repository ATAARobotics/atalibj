package edu.ata.commands;

import edu.wpi.first.wpilibj.Timer;
import java.util.Vector;

/**
 * Basic {@link Command} that contains multiple commands within itself. Is
 * capable of running them concurrently or sequentially.
 *
 * <p> In practice, it is a command that has commands within itself. It is important
 * not to recursively add {@link CommandGroup Command groups} to each other,
 * since that would cause a stack overflow.
 *
 * @author Joel Gallant
 */
public class CommandGroup extends Command {

    private static final int SEQUENTIAL = 0, CONCURRENT = 1;
    private Vector autoBuffer = new Vector();
    private int[] typesBuffer = new int[0];

    /**
     * Adds a command to be run, and stop the next command from running until it
     * is complete. All previous commands will be run beforehand, and all the
     * next commands will be run afterwards.
     *
     * @param command command to add
     */
    protected void addSequential(Command command) {
        addCommand(SEQUENTIAL, command);
    }

    /**
     * Adds a command to be run with all other concurrent commands around
     * itself. All previous sequential commands will be run beforehand, and all
     * the next sequential commands will be run afterwards. All concurrent
     * commands beforehand and afterwards are run at the same time.
     *
     * @param command command to add
     */
    protected void addConcurrent(Command command) {
        addCommand(CONCURRENT, command);
    }

    private void addCommand(final int type, Command command) {
        int[] tmp = typesBuffer;
        typesBuffer = new int[typesBuffer.length + 1];
        System.arraycopy(tmp, 0, typesBuffer, 0, tmp.length);
        typesBuffer[typesBuffer.length - 1] = type;
        autoBuffer.addElement(command);
    }

    /**
     * Adds a waiting period between two commands. Is a sequential command.
     *
     * @see Pause
     * @param time time to wait
     */
    protected void addPause(final double time) {
        addSequential(new CommandGroup.Pause(time));
    }

    /**
     * Runs the group, and all of the commands added to it.
     */
    public void run() {
        Command[] concurrentBuffer = null;
        for (int x = 0; x < typesBuffer.length; ++x) {
            if (typesBuffer[x] == SEQUENTIAL) {
                if (concurrentBuffer != null) {
                    Scheduler.runConcurrently(concurrentBuffer);
                    concurrentBuffer = null;
                }
                Scheduler.run((Command) autoBuffer.elementAt(x));
            } else if (typesBuffer[x] == CONCURRENT) {
                if (concurrentBuffer != null) {
                    Command[] tmp = concurrentBuffer;
                    concurrentBuffer = new Command[concurrentBuffer.length + 1];
                    System.arraycopy(tmp, 0, concurrentBuffer, 0, tmp.length);
                    concurrentBuffer[concurrentBuffer.length - 1] =
                            (Command) autoBuffer.elementAt(x);
                } else {
                    concurrentBuffer = new Command[1];
                    concurrentBuffer[0] = (Command) autoBuffer.elementAt(x);
                }
            }
        }
        // Runs remaining if they exist
        if (concurrentBuffer != null) {
            Scheduler.runConcurrently(concurrentBuffer);
        }
    }

    /**
     * Basic command that waits for a certain amount of time. Is accessible to
     * anywhere in the code.
     */
    public static class Pause extends Command {

        private final double pause;

        /**
         * Constructs command with timeout.
         *
         * @param seconds seconds to wait
         */
        public Pause(double seconds) {
            super("Pause");
            this.pause = seconds;
        }

        public void run() {
            Timer.delay(pause);
        }
    }
}
