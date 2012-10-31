package edu.ata.automation.autonomous;

import edu.ata.automation.dispatch.AutonomousMode;
import java.util.Hashtable;

/**
 * Class used to set an autonomous mode, to enable dynamic switching between
 * modes. Contains a stash of ready autonomous modes that are callable by name
 * if wanted.
 *
 * @author Joel Gallant
 */
public class AutonomousModeChooser {

    private AutonomousMode autonomousMode;
    private final Hashtable modes;

    /**
     * Creates the chooser with an array of modes used. These modes are usable
     * with {@link AutonomousModeChooser#setAutonomousMode(java.lang.String)},
     * but it is completely possible to set the autonomous mode outside of the
     * modes array with
     * {@link AutonomousModeChooser#setAutonomousMode(edu.ata.automation.dispatch.AutonomousMode)}.
     *
     * <p> <i>Warning: If two autonomous modes have the same name, the furthest
     * one in the array will be taken, and the first one ignored.</i>
     *
     * @param modes
     */
    public AutonomousModeChooser(AutonomousMode[] modes) {
        this.modes = new Hashtable(modes.length);
        for (int x = 0; x < modes.length; x++) {
            this.modes.put(modes[x].getName(), modes[x]);
        }
    }

    /**
     * Creates chooser with nothing pre-defined.
     * {@link AutonomousModeChooser#setAutonomousMode(java.lang.String)} is
     * rendered functionless if this constructor is called.
     */
    public AutonomousModeChooser() {
        this.modes = new Hashtable();
    }

    /**
     * Sets the current autonomous mode. Is able to set it to anything.
     *
     * @param autonomousMode new autonomous mode
     */
    public void setAutonomousMode(AutonomousMode autonomousMode) {
        this.autonomousMode = autonomousMode;
    }

    /**
     * Sets the current autonomous mode based off of the array of modes given in
     * the constructor. Is useless if
     * {@link AutonomousModeChooser#AutonomousModeChooser()} was used.
     *
     * @param name name of the autonomous mode in the array
     */
    public void setAutonomousMode(String name) {
        setAutonomousMode((AutonomousMode) modes.get(name));
    }

    /**
     * Returns the current selection for autonomous.
     *
     * @return the last set autonomous mode
     */
    public AutonomousMode getAutonomousMode() {
        return autonomousMode;
    }
}