package edu.ata.auto.modes;

import edu.ata.auto.AutonomousMode;
import edu.ata.commands.ScriptCommand;
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

    /**
     * Creates the autonomous mode with the full script. The script is
     * 'unchangeable', meaning that it cannot change values at any time.
     *
     * @param script string representation of script
     */
    public ScriptAutonomousMode(String script) {
        super("Script Autonomous Mode");
        addSequential(new ScriptCommand(script));
    }
}