package ata2014.main;

import edu.first.identifiers.Function;
import edu.first.util.MathUtils;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Settings extends Files {

    SettingsFile settings = new SettingsFile(settingsFile);

    String CONTROL_STYLE = settings.getString("ControlStyle", "arcade");

    boolean DRIVING_PID = settings.getBoolean("DrivingPID", false);
    boolean WINCH_CONTROL = settings.getBoolean("WinchControl", false);
    boolean MAC_MODE = settings.getBoolean("MacMode", false);

    double JOYSTICK_DEADBAND = settings.getDouble("JoystickDeadband", 0.20);
    double DRIVING_P = settings.getDouble("DrivingP", 1),
            DRIVING_I = settings.getDouble("DrivingI", 0),
            DRIVING_D = settings.getDouble("DrivingD", 0);
    double DRIVING_STRAIGHT_P = settings.getDouble("DrivingStraightP", 1),
            DRIVING_STRAIGHT_I = settings.getDouble("DrivingStraightI", 0),
            DRIVING_STRAIGHT_D = settings.getDouble("DrivingStraightD", 0);
    double DRIVING_CORRECTION = settings.getDouble("DrivingCorrection", 0);
    double DRIVING_MAX_SPEED = settings.getDouble("DrivingMaxSpeed", 20000);
    double DRIVING_SENSITIVITY = settings.getDouble("DrivingSensitivity", 0.5);
    double LOADER_P = settings.getDouble("LoaderP", -1),
            LOADER_I = settings.getDouble("LoaderI", 0),
            LOADER_D = settings.getDouble("LoaderD", 0);
    double LOADER_MAX_SPEED = settings.getDouble("LoaderMaxSpeed", 0.5);
    double LOADER_LOAD_SETPOINT = settings.getDouble("LoaderLoadSetpoint", 2);
    double LOADER_STORE_SETPOINT = settings.getDouble("LoaderStoreSetpoint", 3.5);
    double LOADER_TOLERANCE = settings.getDouble("LoaderTolerance", 0.1);

    Function drivingAlgorithm = new Function() {
        public double F(double in) {
            return DRIVING_SENSITIVITY * (MathUtils.pow(in, 3)) + (1 - DRIVING_SENSITIVITY) * in;
        }
    };
}
