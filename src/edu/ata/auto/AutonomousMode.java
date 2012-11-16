package edu.ata.auto;

import edu.ata.commands.Command;
import edu.ata.commands.Scheduler;
import edu.wpi.first.wpilibj.Timer;
import java.util.Vector;

/**
 *
 * @author Joel Gallant
 */
public class AutonomousMode {

    static final int SEQUENTIAL = 0, CONCURRENT = 1;
    Vector autoBuffer = new Vector();
    int[] typesBuffer = new int[0];

    protected void addSequencial(Command command) {
        addCommand(SEQUENTIAL, command);
    }

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

    protected void addPause(final double time) {
        addSequencial(new Command() {
            public void run() {
                Timer.delay(time);
            }
        });
    }

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
        if(concurrentBuffer != null) {
            Scheduler.runConcurrently(concurrentBuffer);
        }
    }
}
