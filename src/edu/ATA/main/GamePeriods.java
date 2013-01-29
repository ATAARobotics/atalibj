/*
 * Team 4334 - Alberta Tech Alliance - FRC Robot Code
 * 2012-2013 Season
 *
 * This code is intended for use for Team 4334, and should not be distributed to
 * other FRC teams without full consent from the proper team authorities. To learn
 * more about the programming team, rules, conventions or any other information about
 * our code, please contact Joel (joelgallant236@gmail.com).
 *
 * This code is hosted on github on https://github.com/Team4334/robot-code
 *
 * Before making changes, adding files or doing anything on git, please ensure that
 * you are authorized to do so, and know what you are doing.
 *
 * If you would like to use a piece of code in this project, please ask us first,
 * through chief delphi (joelg236) or email (joelgallant236@gmail.com).
 *
 * "It should be noted that no ethically-trained software engineer would ever
 * consent to write a DestroyBaghdad procedure. Basic professional ethics would
 * instead require him to write a DestroyCity procedure, to which Baghdad could
 * be given as a parameter." - Nathaniel Borenstein
 *
 * Please write code properly, and have fun doing so!
 *
 * The license for the code is available in /license.txt
 */

/*----------------------------------------------------------------------------*/
/* Copyright for WPI libraries:                                               */
/*                                                                            */
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.ATA.main;

import edu.ATA.test.RobotSelector;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * This class is the 'main' class of the robot code. This is where everything is
 * started by the DriverStation and WPIlibj code.
 *
 * <p> <b> The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory. (<i>/resources/META-INF/MANIFEST.MF</i> under "MIDlet-1")</b>
 */
public final class GamePeriods extends IterativeRobot {

    private static Robot robot;

    public static void setRobot(Robot robot) {
        GamePeriods.robot = robot;
    }

    {
        try {
            robot = new RobotSelector();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Run once when robot starts.
     */
    public void robotInit() {
        try {
            robot.robotInit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs once before disabled mode.
     */
    public void disabledInit() {
        try {
            robot.disabledInit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during disabled mode.
     */
    public void disabledPeriodic() {
        try {
            robot.disabledPeriodic();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs once before autonomous mode.
     */
    public void autonomousInit() {
        try {
            robot.autonomousInit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during autonomous mode.
     */
    public void autonomousPeriodic() {
        try {
            robot.autonomousPeriodic();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs once before teleoperated mode.
     */
    public void teleopInit() {
        try {
            robot.teleopInit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during teleoperated mode.
     */
    public void teleopPeriodic() {
        try {
            robot.teleopPeriodic();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs once before test mode.
     */
    public void testInit() {
        try {
            robot.testInit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Runs periodically (20ms) during test mode.
     */
    public void testPeriodic() {
        try {
            robot.testPeriodic();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
