package edu.ata.automation.autonomous;

import edu.ata.automation.dispatch.AutonomousMode;
import java.util.Hashtable;

/**
 * Class used to set an autonomous mode, to enable dynamic switching between
 * modes.
 *
 * <p> There are two implementation possibilities for
 * {@link AutonomousModeChooser}.
 *
 * <pre> 1. Using a pre-made stash of {@link AutonomousMode Autonomous modes}
 * and picking them by name </pre>
 * <pre> 2. Using this as a way to determine which {@link AutonomousMode} to
 * run, using
 * {@link AutonomousModeChooser#setAutonomousMode(edu.ata.automation.dispatch.AutonomousMode)}
 * and {@link AutonomousModeChooser#getAutonomousMode()}. </pre>
 * <pre> 3. Using a combination of #1 and #2, and having a stash, and being able to set it manually.
 * </pre>
 *
 * <p> #3 is the most flexible option, but allows for the most incorrect and
 * buggy code. Please make sure to use this class correctly.
 *
 *
 * @author Joel Gallant
 */
public class AutonomousModeChooser {

    private AutonomousMode autonomousMode;
    private final Hashtable modes;

    /**
     * Creates the chooser with an array of
     * {@link AutonomousMode Autonomous modes} available. These modes are usable
     * with {@link AutonomousModeChooser#setAutonomousMode(java.lang.String)},
     * but it is completely possible to set the autonomous mode outside of the
     * modes array with
     * {@link AutonomousModeChooser#setAutonomousMode(edu.ata.automation.dispatch.AutonomousMode)}.
     *
     * <p> <i>Warning: If two autonomous modes have the same name, the furthest
     * one in the array will be taken, and the first one ignored.</i>
     *
     * @param modes array of modes possible
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
     *
     * <p> It is still perfectly valid to use
     * {@link AutonomousModeChooser#setAutonomousMode(edu.ata.automation.dispatch.AutonomousMode)}
     * and {@link AutonomousModeChooser#getAutonomousMode()} will still work as
     * expected.
     */
    public AutonomousModeChooser() {
        this.modes = new Hashtable();
    }

    /**
     * Sets the current autonomous mode. Is able to set it to any
     * {@link AutonomousMode}. Is not limited by choices given in the
     * constructor.
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