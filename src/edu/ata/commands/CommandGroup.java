package edu.ata.commands;

import edu.wpi.first.wpilibj.Timer;
import java.util.Vector;

/**
 * Basic {@link Command} that contains multiple commands within itself. Is
 * capable of running them concurrently or sequentially.
 *
 * @author Joel Gallant
 */
public class CommandGroup extends Command {

    static final int SEQUENTIAL = 0, CONCURRENT = 1;
    Vector autoBuffer = new Vector();
    int[] typesBuffer = new int[0];

    /**
     * Adds a command to be run, and stop the next command from running until it
     * is done.
     *
     * @param command command to add
     */
    protected void addSequential(Command command) {
        addCommand(SEQUENTIAL, command);
    }

    /**
     * Adds a command to be run with all other concurrent commands around
     * itself.
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
     * Adds a waiting period between two commands.
     *
     * @param time time to wait
     */
    protected void addPause(final double time) {
        addSequential(new Command() {
            public void run() {
                Timer.delay(time);
            }
        });
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
}
