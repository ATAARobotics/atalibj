package edu.ata.automation.driving.robot;

import edu.ata.automation.dispatch.GameMode;
import edu.ata.automation.driving.modules.Module;
import edu.ata.main.GamePeriods;
import edu.ata.user.Logger;

/**
 * {@link Robot} object used as the default for code. It abstracts the
 * relationship between {@link GamePeriods}, its associated methods and
 * {@link GameMode Game Modes}.
 *
 * @author Joel Gallant
 */
public abstract class DefaultRobot extends Robot {

    private final GameMode autonomous, teleop, disabled;

    /**
     * Creates the robot with its fields. Should <i><b>never<b></i> provide null
     * game modes. Will throw {@link NullPointerException}.
     *
     * <p> To learn more about how the game modes interact within this class,
     * look at the source. <b> The important thing to know is that it relies on
     * {@link GameMode#stop()} to stop the mode, and wait until it is finished.
     * </b>
     *
     * @param modules array of the modules
     * @param name identifier of robot
     * @param autonomous autonomous mode
     * @param teleop teleop mode
     * @param disabled disabled mode
     */
    public DefaultRobot(Module[] modules, String name, GameMode autonomous,
            GameMode teleop, GameMode disabled) throws NullPointerException {
        super(modules, name);
        if (autonomous == null || teleop == null || disabled == null) {
            throw new NullPointerException("Gave null game mode in robot - " + name);
        }
        this.autonomous = autonomous;
        this.teleop = teleop;
        this.disabled = disabled;
    }

    public final void disabled() {
        autonomous.stop();
        teleop.stop();
        Logger.log(Logger.Urgency.STATUSREPORT, "Starting disabled mode  - " + disabled);
        disabled.start();
    }

    public final void teleop() {
        disabled.stop();
        autonomous.stop();
        Logger.log(Logger.Urgency.STATUSREPORT, "Starting teleop mode - " + teleop);
        teleop.start();
    }

    public final void autonomous() {
        disabled.stop();
        teleop.stop();
        Logger.log(Logger.Urgency.STATUSREPORT, "Starting autonomous mode - " + autonomous);
        autonomous.start();
    }
}