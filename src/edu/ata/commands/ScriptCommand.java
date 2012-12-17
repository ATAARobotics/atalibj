package edu.ata.commands;

import edu.ata.command.Command;
import edu.ata.gordian.Script;

/**
 * Command to run scripts. Two Script commands should never run simultaneously
 * (variable storage problems). See {@link Script}.
 *
 * @author Joel Gallant
 */
public final class ScriptCommand extends Command {

    private final String script;

    /**
     * Creates the command with a string representing the script.
     *
     * @param script full script to run
     */
    public ScriptCommand(String script) {
        this.script = script;
    }

    public void run() {
        Script.run(script);
    }
}