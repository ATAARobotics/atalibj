package edu.ata.user;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * Utility class used to send data to the SmartDashboard. Has three different
 * parts used to describe the type of data being transmitted. Info, Auto and
 * Tele are used to describe data about the robot, autonomous mode and
 * teleoperated mode respectively.
 *
 * @author Joel Gallant
 */
public class Dashboard {

    /**
     * The delay between sending data in the Dashboard thread. Default is three
     * seconds.
     */
    public static long delay = 3000;
    private static boolean threadOn = true,
            firstRun = true;
    // Lock for dashboard thread
    private static final Object lock = new Object();

    /**
     * Sends data about the robot to the dashboard.
     */
    public static void sendInfo() {
    }

    /**
     * Sends data about autonomous mode to the dashboard.
     */
    public static void sendAuto() {
    }

    /**
     * Sends data about teleoperated mode to the dashboard.
     */
    public static void sendTele() {
    }

    /**
     * Sends all data to the dashboard depending on the settings.
     */
    public static void sendAll() {
        // Check existance of all settings and set to true by default
        if (firstRun) {
            try {
                SmartDashboard.getBoolean("InfoData");
            } catch (TableKeyNotDefinedException ex) {
                // Default to true
                SmartDashboard.putBoolean("InfoData", true);
            }

            try {
                SmartDashboard.getBoolean("AutoData");
            } catch (TableKeyNotDefinedException ex) {
                // Default to true
                SmartDashboard.putBoolean("AutoData", true);
            }

            try {
                SmartDashboard.getBoolean("TeleData");
            } catch (TableKeyNotDefinedException ex) {
                // Default to true
                SmartDashboard.putBoolean("TeleData", true);
            }
            
            firstRun = false;
        }

        if (SmartDashboard.getBoolean("InfoData")) {
            sendInfo();
        }
        if (SmartDashboard.getBoolean("AutoData")) {
            sendAuto();
        }
        if (SmartDashboard.getBoolean("TeleData")) {
            sendTele();
        }
    }

    /**
     * Starts the dashboard thread that updates the data periodically.
     */
    public static void start() {
        threadOn = true;
        new Thread(new Runnable() {
            public void run() {
                boolean t = threadOn;
                while (t) {
                    sendAll();
                    Timer.delay(delay);
                    synchronized (lock) {
                        t = threadOn;
                    }
                }
            }
        }, "Dashboard Thread").start();
    }

    /**
     * Stops the dashboard thread that updates the data.
     */
    public static void stop() {
        synchronized (lock) {
            threadOn = false;
        }
    }
}
