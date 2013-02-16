/*
 * This software has been created by Alberta Tech Alliance, Team 4334, in 
 * Calgary, Alberta. All credit goes to them and any use of this code should be 
 * accredited to them.
 */
package edu.first.main;

import edu.ATA.twolf.TheWolf;
import edu.first.robot.Robot;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * This class is the 'main' class of the robot code. This is where everything is
 * started by the the wpilibj. To set a default function of your code, set the
 * {@link GamePeriods#robot} object inside of this class, or use
 * {@link GamePeriods#setRobot(edu.first.main.Robot)}.
 *
 * <p> <b> The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. (<i>/resources/META-INF/MANIFEST.MF</i> under "MIDlet-1")</b>
 * 
 * @author Joel Gallant
 */
public final class GamePeriods extends IterativeRobot {

    private static Robot robot;
    
    {
        robot = TheWolf.fetchTheHound();
    }

    /**
     * Sets the robot to run by all instances of {@link GamePeriods}. All of the
     * methods in {@link IterativeRobot} are included in {@link Robot}, and
     * {@link GamePeriods} will run those methods. Assuming this class is used
     * for FRC purposes (wpilibj), this method can be considered the only useful
     * method in {@link GamePeriods}. To change the functionality of the robot
     * completely in a moments notice, this method can be used. It is
     * thread-safe to use this method, so any part of the code can be used to
     * change the robot. To have a "Default" robot, edit this class and
     * instantiate {@link GamePeriods#robot} as the default robot.
     *
     * <p> If the robot does not have a default, or this method is not called,
     * running the robot will result in a {@link NullPointerException}.
     *
     * @param robot robot object for game period methods
     */
    public static void setRobot(final Robot robot) {
        synchronized (GamePeriods.class) {
            GamePeriods.robot = robot;
        }
    }

    /**
     * Run once when robot starts. Anything thrown inside of
     * {@link Robot#robotInit()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void robotInit() {
        try {
            robot.robotInit();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs once before disabled mode. Anything thrown inside of
     * {@link Robot#disabledInit() } are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void disabledInit() {
        try {
            robot.disabledInit();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during disabled mode. Anything thrown inside of
     * {@link Robot#disabledPeriodic()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void disabledPeriodic() {
        try {
            robot.disabledPeriodic();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs once before autonomous mode. Anything thrown inside of
     * {@link Robot#autonomousInit()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void autonomousInit() {
        try {
            robot.autonomousInit();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during autonomous mode. Anything thrown inside
     * of {@link Robot#autonomousPeriodic()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void autonomousPeriodic() {
        try {
            robot.autonomousPeriodic();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs once before teleoperated mode. Anything thrown inside of
     * {@link Robot#teleopInit()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void teleopInit() {
        try {
            robot.teleopInit();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during teleoperated mode. Anything thrown inside
     * of {@link Robot#teleopPeriodic()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void teleopPeriodic() {
        try {
            robot.teleopPeriodic();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs once before test mode. Anything thrown inside of
     * {@link Robot#testInit()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void testInit() {
        try {
            robot.testInit();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during test mode. Anything thrown inside of
     * {@link Robot#testPeriodic()} are caught and logged as
     * {@link Logger.Urgency#URGENT}.
     */
    public void testPeriodic() {
        try {
            robot.testPeriodic();
        } catch (Throwable t) {
            Logger.log(Logger.Urgency.URGENT, "ERROR - " + t.getClass().getName() + " - " + t.getMessage());
            t.printStackTrace();
        }
    }
}
