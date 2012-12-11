package edu.ata.auto.modes;

import edu.ata.auto.AutonomousMode;
import edu.ata.commands.Command;
import edu.ata.gordian.Script;

/**
 * Autonomous mode specifically meant to run a script using Gordian. Uses
 * {@link Script#run(java.lang.String)} so that all variables are reset each
 * time. This means that at no point should <i>two</i> scripts be run at the
 * same time.
 *
 * @see Script
 * @author Joel Gallant
 */
public class ScriptAutonomousMode extends AutonomousMode {

    private final String script;

    /**
     * Creates the autonomous mode with the full script. The script is
     * 'unchangeable', meaning that it cannot change values at any time.
     *
     * @param script string representation of script
     */
    public ScriptAutonomousMode(final String script) {
        super("Script Autonomous Mode");
        this.script = script;
        addSequential(new ScriptCommand());
    }

    private class ScriptCommand extends Command {

        private ScriptCommand() {
        }

        public void run() {
            Script.run(script);
        }
    }
}