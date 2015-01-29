package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.util.log.Logger;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class WaitCommand implements Command {

    private final long period;

    public WaitCommand(long period) {
        this.period = period;
    }

    public WaitCommand(double seconds) {
        this.period = (long) (seconds * 1000);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(period);
        } catch (InterruptedException ex) {
            Logger.getLogger(this).error("Waiting interrupted", ex);
        }
    }
}
