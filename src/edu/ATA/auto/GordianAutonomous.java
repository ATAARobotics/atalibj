package edu.ATA.auto;

import ATA.gordian.Data;
import ATA.gordian.Script;
import ATA.gordian.instructions.Method;
import ATA.gordian.storage.Methods;
import edu.ATA.command.Command;

/**
 * The {@link GordianAutonomous} class is an autonomous mode used to run Gordian
 * (Team 4334's scripting language) scripts. This mode will run one script once,
 * each time it is run. It is - for all intents and purposes - immutable, meaning
 * that it will do the exact same thing each time it is run, and can not be
 * changed. (assuming Gordian runs the same way - can be altered by adding
 * methods) It would be technically incorrect to say it is immutable,
 * considering it it mutable through the {@link Script} hierarchy.
 * ({@link Methods#METHODS_STORAGE} and {@link Data#RETURNING_METHODS})
 *
 * @author Joel Gallant
 */
public final class GordianAutonomous extends AutonomousMode {

    private final String script;

    /**
     * Constructs the autonomous mode using a name and the actual script (in
     * {@link String} form). The name should adhere to the standards in
     * {@link AutonomousMode#AutonomousMode(java.lang.String)}.
     *
     * <p> The {@code script} argument cannot be null.
     *
     * @param name the name of the autonomous mode
     * @param script representation of the full Gordian script
     */
    public GordianAutonomous(String name, String script) {
        super(name);
        if(script == null) {
            throw new NullPointerException();
        }
        this.script = script;
        addSequential(new ScriptCommand());
    }

    private final class ScriptCommand implements Command {

        public void runCommand() {
            Script.run(script);
        }
    }
}
