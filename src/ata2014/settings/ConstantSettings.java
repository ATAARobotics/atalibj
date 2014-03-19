package ata2014.settings;

import edu.first.identifiers.Function;
import edu.first.util.MathUtils;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface ConstantSettings extends Files {

    SettingFile constants = new SettingFile(constantsFile);

    String CONTROL_STYLE = constants.getString("ControlStyle", "arcade");
    
    boolean MAC_MODE = constants.getBoolean("MacMode", false);

    double DRIVING_SENSITIVITY = constants.getDouble("DrivingSensitivity", 0.5);
    double JOYSTICK_DEADBAND = constants.getDouble("JoystickDeadband", 0.20);

    Function drivingAlgorithm = new Function() {
        public double F(double in) {
            return DRIVING_SENSITIVITY * (MathUtils.pow(in, 3)) + (1 - DRIVING_SENSITIVITY) * in;
        }
    };
}
