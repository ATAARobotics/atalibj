package edu.ata.automation.autonomous;

import edu.ata.automation.dispatch.AutonomousMode;
import edu.ata.automation.dispatch.GameMode;

/**
 * Basic interface for autonomous modes. Initializes, does things, then closes.
 *
 * <p> This is not a {@link GameMode}. It is a way to structure autonomous in
 * three separate parts. It is not included in, for example
 * {@link AutonomousMode} because other autonomous modes could exist.
 *
 * <p> The reason to have three methods within {@link AutonomousStructure} is to
 * encourage proper organization of autonomous modes, and especially to use the
 * {@code close()} method. It provides a way to close up everything done within
 * the autonomous mode when the mode is told to finish, instead of right after
 * it is done. This could be useful for things like if you wanted the robot to
 * do something right before teleoperated started instead of after you
 * autonomous finishes.
 *
 * <p><b> Please make all autonomous modes use this basic interface, unless
 * there is a good reason not to do so.</b>
 *
 * @author Joel Gallant
 */
public interface AutonomousStructure {

    /**
     * Initializes the mode. This should be used for things that explicitely
     * need to be done before the mode starts. Even though it is certainly
     * possible to do them at the start of {@code main()}, this gives better
     * clarity of the reason to do something. (Initialization code)
     */
    public abstract void init();

    /**
     * Runs the mode. This is a very open ended method that is basically open to
     * whatever you need it to do. If you want your autonomous to be able to be
     * interrupted however, you should take care of that on your own.
     */
    public abstract void main();

    /**
     * Closes the mode. Should do any finalizing code. In implementation, should
     * usually be called when autonomous is told that it is done, rather than
     * after the robot is done autonomous. (Reasons are given in class java doc.
     */
    public abstract void close();
}