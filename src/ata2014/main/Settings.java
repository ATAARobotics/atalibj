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

    double JOYSTICK_DEADBAND = settings.getDouble("JoystickDeadband", 0.20);
    double DRIVING_P = settings.getDouble("DrivingP", 1),
            DRIVING_I = settings.getDouble("DrivingI", 0),
            DRIVING_D = settings.getDouble("DrivingD", 0);
    double DRIVING_MAX_SPEED = settings.getDouble("DrivingMaxSpeed", 20000);
    double DRIVING_SENSITIVITY = settings.getDouble("DrivingSensitivity", 0.5);

    Function drivingAlgorithm = new Function() {
        public double F(double in) {
            return DRIVING_SENSITIVITY * (MathUtils.pow(in, 3)) + (1 - DRIVING_SENSITIVITY) * in;
        }
    };
}
