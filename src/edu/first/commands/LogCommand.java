package edu.first.commands;

import edu.first.command.Command;
import edu.first.utils.Logger;

/**
 * Logs a message as a {@link Logger.Urgency USERMESSAGE}.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class LogCommand implements Command {

    private final String log;

    /**
     * Constructs the command with the message to be displayed when run.
     *
     * @param log message to display
     */
    public LogCommand(String log) {
        this.log = log;
    }

    /**
     * Logs the message using the default logger.
     */
    public void run() {
        Logger.log(Logger.Urgency.USERMESSAGE, log);
    }
}
