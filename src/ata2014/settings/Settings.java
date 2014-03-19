package ata2014.settings;

import edu.first.identifiers.Input;
import edu.first.identifiers.Position;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Settings extends Files {

    SettingFile settings = new SettingFile(settingsFile);

    Position DRIVING_PID = settings.getBooleanSetting("DrivingPID", false);
    Position WINCH_CONTROL = settings.getBooleanSetting("WinchControl", false);

    Input DRIVING_P = settings.getDoubleSetting("DrivingP", 1),
            DRIVING_I = settings.getDoubleSetting("DrivingI", 0),
            DRIVING_D = settings.getDoubleSetting("DrivingD", 0);
    Input DRIVING_STRAIGHT_P = settings.getDoubleSetting("DrivingStraightP", 1),
            DRIVING_STRAIGHT_I = settings.getDoubleSetting("DrivingStraightI", 0),
            DRIVING_STRAIGHT_D = settings.getDoubleSetting("DrivingStraightD", 0);
    Input DRIVING_MAX_SPEED = settings.getDoubleSetting("DrivingMaxSpeed", 20000);
    Input LOADER_P = settings.getDoubleSetting("LoaderP", -1),
            LOADER_I = settings.getDoubleSetting("LoaderI", 0),
            LOADER_D = settings.getDoubleSetting("LoaderD", 0);
    Input LOADER_MAX_SPEED = settings.getDoubleSetting("LoaderMaxSpeed", 0.5);
    Input LOADER_LOAD_SETPOINT = settings.getDoubleSetting("LoaderLoadSetpoint", 2);
    Input LOADER_STORE_SETPOINT = settings.getDoubleSetting("LoaderStoreSetpoint", 3.5);
    Input LOADER_TOLERANCE = settings.getDoubleSetting("LoaderTolerance", 0.1);
}
