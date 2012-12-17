package edu.ata.user;

import edu.ata.auto.AutonomousSelector;
import edu.ata.auto.modes.ScriptAutonomousMode;
import edu.ata.driving.modules.Module;
import edu.ata.driving.robot.Robot;
import edu.ata.main.DriverstationInfo;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Singleton design pattern class that deals with the smart dashboard and
 * preferences.
 *
 * @author Joel Gallant
 */
public class UserInfo {

    private static UserInfo instance;

    /**
     * Returns the singleton instance of itself. Uses synchronized check to
     * ensure no tomfoolery.
     *
     * @return singleton instance
     */
    public static UserInfo getInstance() {
        synchronized (UserInfo.class) {
            if (instance == null) {
                instance = new UserInfo();
            }
        }
        return instance;
    }
    private final Preferences preferences = Preferences.getInstance();
    private final TransferRateCalc transferRate = new TransferRateCalc();

    private UserInfo() {
    }

    /**
     * Updates all data - input and output.
     */
    public void updateAll() {
        updateInfo();
        updateAutonomous();
    }

    /**
     * Receives all info about robot.
     */
    public void updateInfo() {
        SmartDashboard.putNumber("Packets Per Second", transferRate.packetsPerSecond());
        SmartDashboard.putString("Modules", Module.MODULES.toString());
        SmartDashboard.putString("Robot", Robot.getRobot().getName());
    }

    /**
     * Sends and then receives all data pertaining to autonomous mode.
     */
    public void updateAutonomous() {
        sendAutonomous();
        getAutonomous();
    }

    private void sendAutonomous() {
        SmartDashboard.putString("autoChoices", AutonomousSelector.getInstance().toString());
    }

    private void getAutonomous() {
        if (scriptOn()) {
            // Make autonomous mode the script
            AutonomousSelector.getInstance().set(new ScriptAutonomousMode(userScript()));
        } else {
            // Make autonomous mode the current selected mode
            AutonomousSelector.getInstance().set(currentAutonomousMode());
        }
    }

    private String currentAutonomousMode() {
        if (!preferences.containsKey("currentAuto")) {
            preferences.putString("currentAuto", "DEFAULT");
        }
        return preferences.getString("currentAuto", null);
    }

    private boolean scriptOn() {
        if (!preferences.containsKey("scriptOn")) {
            preferences.putBoolean("scriptOn", true);
        }
        return preferences.getBoolean("scriptOn", userScript() != null);
    }

    private String userScript() {
        if (!preferences.containsKey("userScript")) {
            preferences.putString("userScript", "#INSERT SCRIPT HERE");
        }
        return preferences.getString("userScript", null);
    }

    private class TransferRateCalc {

        private long lastPPSCheck = System.currentTimeMillis();
        private int lastPacketsCheck = DriverstationInfo.getPacketCount();

        public double packetsPerSecond() {
            return packetsSinceLastCheck() / secondsSinceLastCheck();
        }
        
        public void reset() {
            lastPPSCheck = System.currentTimeMillis();
            lastPacketsCheck = 0;
        }

        private long secondsSinceLastCheck() {
            long s = (lastPPSCheck - System.currentTimeMillis());
            lastPPSCheck = System.currentTimeMillis();
            return s / 1000;
        }

        private int packetsSinceLastCheck() {
            int s = DriverstationInfo.getPacketCount() - lastPacketsCheck;
            lastPacketsCheck = DriverstationInfo.getPacketCount();
            return s;
        }
    }
}