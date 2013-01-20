package edu.ATA.auto;

import edu.ATA.command.CommandGroup;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The basic framework for different autonomous modes. This allows the drive
 * team to select which mode to run before each match, as opposed to being stuck
 * with what code is written. There is no limit to how long or short the
 * autonomous mode can run. That means that <i>autonomous modes can run into
 * teleoperated mode</i>. It is very important to regulate this when writing
 * code. It is absolutely possible that it is a good idea to continue the
 * autonomous mode until it is finished, rather than when teleoperated mode
 * starts - which is why there is no regulation put on autonomous modes.
 *
 * <p> To use this class, you need to subclass it. Without extending this class,
 * the {@link AutonomousMode} object created (using
 * {@link AutonomousMode#AutonomousMode(java.lang.String) AutonomousMode(String)})
 * will do nothing. To add functionality to your subclass, add all commands in
 * the constructor of the autonomous mode (or an non-static block). The
 * {@link AutonomousMode#runCommand() runCommand()} method will automatically
 * run those commands given to it previously, in the order they were given.
 *
 * <p> To add commands to an autonomous mode, use the same method as
 * {@link CommandGroup}.
 * {@link AutonomousMode#addSequential(edu.ATA.command.Command) addSequential(Command)}
 * and
 * {@link AutonomousMode#addConcurrent(edu.ATA.command.Command) addConcurrent(Command)}
 * behave in the same way as the {@link CommandGroup}. (in fact,
 * {@link AutonomousMode} is simply a subclass of {@link CommandGroup})
 *
 * <p> An example of an autonomous mode would look like this:
 * <pre>
 * public class CustomAutoMode extends AutonomousMode {
 *     public CustomAutoMode() {
 *         super("Custom auto mode");
 *         addSequential(new ShootBall());
 *         addSequential(new ShootBall());
 *         addConcurrent(new RetractShooter());
 *         addConcurrent(new DriveBackwards(1400));
 *         addSequential(new Wait(5000));
 *     }
 * }
 * </pre>
 *
 * <p> Autonomous modes have 'names' to identify what they do. This is typically
 * used for the user to select a mode to run, and should be regarded as
 * something specifically meant for the user to understand. There is no required
 * format for names of autonomous modes. (name is given in constructor and is
 * immutable)
 *
 * <p> Autonomous modes should be (and are, unless changed by their subclass)
 * immutable in practice. After calling it's constructor and non-static blocks,
 * an {@link AutonomousMode} should not be able to change. This is not a strict
 * requirement, and is certainly not enforced by the code.
 *
 * <p> The {@link AutonomousMode} class has a second "utility" function. It's
 * static methods are used to store the 'current' autonomous mode, meaning the
 * mode that is currently selected by the user to be run. It stores all modes
 * given to it in
 * {@link AutonomousMode#addAutonomousMode(edu.ATA.auto.AutonomousMode) addAutonomousMode(AutonomousMode)}
 * With those modes, they can be selected by name with
 * {@link AutonomousMode#setAutonomousMode(java.lang.String) setAutonomousMode(String)}.
 * This function is used by {@link UserInfo} to select the appropriate
 * autonomous mode based on the user's input.
 *
 * <p> <i> Note: For implementation purposes, no two autonomous modes can have
 * the exact same name. </i>
 *
 * @see CommandGroup
 * @author Joel Gallant
 */
public class AutonomousMode extends CommandGroup {

    private static AutonomousMode currentMode;
    private static final Hashtable autoModes = new Hashtable();
    private final String name;

    /**
     * Part of the "utility" portion of this class outlined in the documentation
     * of {@link AutonomousMode}, this method sets the 'current' (also outlined)
     * mode to the object given in the argument. The argument should never be
     * null.
     *
     * <p> This method also adds the autonomous mode to the {@link Hashtable}
     * within this class, which makes it accessible to
     * {@link AutonomousMode#setAutonomousMode(java.lang.String) setAutomousMode(String)}.
     *
     * @param mode the autonomous mode to be given in
     * {@link AutonomousMode#getCurrentMode()}
     */
    public static void setAutonomousMode(AutonomousMode mode) {
        if (mode == null) {
            throw new NullPointerException();
        }
        addAutonomousMode(mode);
        currentMode = mode;
    }

    /**
     * Part of the "utility" portion of this class outlined in the documentation
     * of {@link AutonomousMode}, this method sets the 'current' (also outlined
     * in documentation) mode to the mode in the storage in this class with the
     * name equivalent to the argument. If there is no mode under that name, the
     * current mode stays as it was beforehand.
     *
     * @param name the name of the autonomous mode
     * ({@link AutonomousMode#getName()})
     */
    public static void setAutonomousMode(String name) {
        AutonomousMode a = (AutonomousMode) autoModes.get(name);
        if (a != null) {
            currentMode = a;
        }
    }

    /**
     * Part of the "utility" portion of this class outlined in the documentation
     * of {@link AutonomousMode}, this method adds an {@link AutonomousMode}
     * object to the {@link Hashtable} storage in this class. It is stored under
     * the name ({@link AutonomousMode#getName()}). Once the mode has been added
     * with this method, it can be accessed by
     * {@link AutonomousMode#setAutonomousMode(java.lang.String) setAutonomousMode(String)}.
     *
     * <p> <i> This method does not set the 'current' mode.</i>
     *
     * @param mode the autonomous mode to add to storage
     */
    public static void addAutonomousMode(AutonomousMode mode) {
        if (mode == null) {
            throw new NullPointerException();
        }
        autoModes.put(mode.getName(), mode);
    }

    /**
     * Part of the "utility" portion of this class outlined in the documentation
     * of {@link AutonomousMode}, this method creates and returns a string that
     * represents all autonomous modes in the {@link Hashtable} storage. The
     * format is <b>not to be depended on</b>, and is subject to change.
     *
     * <p> The current format is:
     *
     * <pre>
     * A.toString() + ", " + A1.toString() + ", " + ... + Afinal.toString()
     * </pre>
     *
     * @return string representing all modes
     */
    public static String getAutonomousModes() {
        String s = "";
        Enumeration e = autoModes.keys();
        while (e.hasMoreElements()) {
            s += e.nextElement().toString().concat(", ");
        }
        if (s.indexOf(", ") >= 0) {
            s = s.substring(0, s.length() - 2);
        }
        return s;
    }

    /**
     * Part of the "utility" portion of this class outlined in the documentation
     * of {@link AutonomousMode}, this method returns the currently 'selected'
     * autonomous mode. The selected mode can be changed anywhere with
     * {@link AutonomousMode#setAutonomousMode(edu.ATA.auto.AutonomousMode) setAutonomousMode(AutonomousMode)}
     * or
     * {@link AutonomousMode#setAutonomousMode(java.lang.String) setAutonomousMode(String)}.
     *
     * @return the last set autonomous mode
     */
    public static AutonomousMode getCurrentMode() {
        return currentMode;
    }

    /**
     * Constructs the autonomous mode with a specific name used to identify
     * itself to the user. Most of the constructing (adding commands, etc.)
     * should be done in the constructor.
     *
     * <p> There is no defined format for names. They should be unique (<b>need
     * to be if storing in {@link AutonomousMode}</b>), and easily identifiable
     * to the user.
     *
     * @param name the name of the autonomous mode
     */
    public AutonomousMode(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the mode. This field will never change, so it si safe
     * to assume that this method will always return the exact same value.
     *
     * @return the name of the autonomous mode
     */
    public final String getName() {
        return name;
    }

    /**
     * Returns the name of the mode. Is equivalent to
     * {@link AutonomousMode#getName()}.
     *
     * @return name of the autonomous mode
     */
    public String toString() {
        return getName();
    }
}
