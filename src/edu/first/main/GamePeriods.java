/*
 * This software was created by Alberta Tech Alliance, Team 4334, in 
 * Calgary, Alberta. This version is a fork by FRC Western Canada.
 */
package edu.first.main;

import edu.first.robot.IterativeRobotAdapter;
import edu.first.robot.RobotMode;
import edu.first.robot.SafeRobotMode;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * This class is called by the VM automatically for every game mode. It is meant
 * to start all necessary functions, and is the gateway from the Java VM into
 * your code. {@code GamePeriods} is final because the functionality it provides
 * is through {@link RobotMode Robot Modes}. Although the implementation and
 * specifics about how {@link RobotMode} is used is up to the programmer, the
 * usage of {@link RobotMode} is necessary. This does not detract from
 * {@code GamePeriods}'s usefulness because it contains all of the methods of
 * {@code GamePeriods} and more.
 *
 * Please change the static variable {@link #robotMode} to your robot. Do so
 * however you wish, but you may experience problems if set statically (happens
 * before some internal initialisations of WPILibJ).
 *
 * <b> If you change the name of this class or the package after creating this
 * project, you must also update the manifest file in the resource directory.
 * (<i>/resources/META-INF/MANIFEST.MF</i> under "MIDlet-1")</b>
 *
 * This class is not thread safe and should <b>never</b> be manually
 * constructed, but by mandate (VM) its constructor needs to be public.
 *
 * @since May 07 13
 * @author Joel Gallant
 */
public final class GamePeriods extends IterativeRobot {

    // Stored to "end" the game mode after it is finished
    private static GameMode previousGameMode = null;
    // The current game mode - change to yours
    private static RobotMode robotMode;

    /**
     * Please never use this!
     */
    public GamePeriods() {
        // Init robot mode here so that static initializing doesn't interfere with wpi code
        robotMode = new SafeRobotMode(new IterativeRobotAdapter("Null"));
    }

    /**
     * Initializes the robot. Is run once at the start of the robot's execution
     * cycle, but never again. Effectively should "start the robot", in whatever
     * context it happens to be in.
     */
    public void robotInit() {
        robotMode.init();
    }

    /**
     * Initializes the disabled mode of the robot. Usually, this method "turns
     * off" the functions of the robot, like background threads and motor
     * controllers.
     *
     * Every time disabled is run, the {@code RobotMode} is updated. This only
     * happens during the disabled period.
     */
    public void disabledInit() {
        finishAndNewMode(GameMode.DISABLED);
        robotMode.initDisabled();
    }

    /**
     * Runs periodically during the disabled period.
     *
     * In the context of {@link IterativeRobot}, "periodic" means 50 times per
     * second, or 50Hz. It is run every 0.02 seconds. This is not guaranteed,
     * and is only run once every time the DriverStation sends packets. It can
     * be roughly assumed that it runs at 50Hz, but should not be depended on.
     */
    public void disabledPeriodic() {
        robotMode.periodicDisabled();
    }

    /**
     * Initializes anything needed for the autonomous period of the robot.
     */
    public void autonomousInit() {
        finishAndNewMode(GameMode.AUTONOMOUS);
        robotMode.initAutonomous();
    }

    /**
     * Runs periodically during the autonomous period.
     *
     * In the context of {@link IterativeRobot}, "periodic" means 50 times per
     * second, or 50Hz. It is run every 0.02 seconds. This is not guaranteed,
     * and is only run once every time the DriverStation sends packets. It can
     * be roughly assumed that it runs at 50Hz, but should not be depended on.
     */
    public void autonomousPeriodic() {
        robotMode.periodicAutonomous();
    }

    /**
     * Initializes anything needed for the teleoperated period of the robot.
     */
    public void teleopInit() {
        finishAndNewMode(GameMode.TELEOPERATED);
        robotMode.initTeleoperated();
    }

    /**
     * Runs periodically during the teleoperated period.
     *
     * In the context of {@link IterativeRobot}, "periodic" means 50 times per
     * second, or 50Hz. It is run every 0.02 seconds. This is not guaranteed,
     * and is only run once every time the DriverStation sends packets. It can
     * be roughly assumed that it runs at 50Hz, but should not be depended on.
     */
    public void teleopPeriodic() {
        robotMode.periodicTeleoperated();
    }

    /**
     * Initializes anything needed for the test period of the robot.
     */
    public void testInit() {
        finishAndNewMode(GameMode.TEST);
        robotMode.initTest();
    }

    /**
     * Runs periodically during the test period.
     *
     * In the context of {@link IterativeRobot}, "periodic" means 50 times per
     * second, or 50Hz. It is run every 0.02 seconds. This is not guaranteed,
     * and is only run once every time the DriverStation sends packets. It can
     * be roughly assumed that it runs at 50Hz, but should not be depended on.
     */
    public void testPeriodic() {
        robotMode.periodicTest();
    }

    /**
     * Finishes the {@code GameMode} given the last time this method was run.
     *
     * @param newMode the mode that the user is headed into which will be ended
     * the next time this method is run
     */
    private void finishAndNewMode(GameMode newMode) {
        if (previousGameMode != null) {
            previousGameMode.end();
        }
        previousGameMode = newMode;
    }

    /**
     * Representation of the different game modes used to end them using the
     * methods in {@link RobotMode}.
     */
    private static abstract class GameMode {

        static final GameMode DISABLED = new GameMode() {
            void end() {
                robotMode.endDisabled();
            }
        };
        static final GameMode AUTONOMOUS = new GameMode() {
            void end() {
                robotMode.endAutonomous();
            }
        };
        static final GameMode TELEOPERATED = new GameMode() {
            void end() {
                robotMode.endTeleoperated();
            }
        };
        static final GameMode TEST = new GameMode() {
            void end() {
                robotMode.endTest();
            }
        };

        /**
         * Ends the mode selected.
         */
        abstract void end();
    }
}
