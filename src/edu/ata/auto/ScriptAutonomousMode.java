package edu.ata.auto;

import edu.ata.commands.Command;
import edu.ata.script.Script;

/**
 * @author Joel Gallant
 */
public class ScriptAutonomousMode extends AutonomousMode {

    public ScriptAutonomousMode(final String script) {
        super("Script Autonomous Mode");

        addSequential(new Command("Script") {
            public void run() {
                Script.run(script);
            }
        });
    }
}