package ata2014.main;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Ports {

//
// *****     DEFAULTS     *****
//
//     Speed Controllers:
    //   Left Drive - 1, 2, 3
    //   Right Drive - 4, 5, 6
    //   Left Loader - 7
    //   Right Loader - 8
    //   Winch Motor - 9
//     Solenoids:
    //   Left Loader (in) - 1
    //   Left Loader (out) - 2
    //   Right Loader (in) - 3
    //   Right Loader (out) - 4
    //   Shifter (in) - 5
    //   Shifter (out) - 6
    //   Winch Release - 7
//     Digital Input:
    //   Left Encoder: 1, 2
    //   Right Encoder: 3, 4
    //   Left Loader Switch: 5
    //   Right Loader Switch: 6
//     Analog Input:
    //   Shooter Potentiometer: 1
//
//
//
    // Drivetrain
    int LEFT_DRIVE_1 = Preferences.getInstance().getInt("LEFT_DRIVE_1", 1),
            LEFT_DRIVE_2 = Preferences.getInstance().getInt("LEFT_DRIVE_2", 2),
            LEFT_DRIVE_3 = Preferences.getInstance().getInt("LEFT_DRIVE_3", 3),
            RIGHT_DRIVE_1 = Preferences.getInstance().getInt("RIGHT_DRIVE_1", 4),
            RIGHT_DRIVE_2 = Preferences.getInstance().getInt("RIGHT_DRIVE_2", 5),
            RIGHT_DRIVE_3 = Preferences.getInstance().getInt("RIGHT_DRIVE_3", 6);
    int SHIFTER_IN = Preferences.getInstance().getInt("SHIFTER_IN", 5),
            SHIFTER_OUT = Preferences.getInstance().getInt("SHIFTER_OUT", 6);
    int LEFT_ENCODER_A = Preferences.getInstance().getInt("LEFT_ENCODER_A", 1),
            LEFT_ENCODER_B = Preferences.getInstance().getInt("LEFT_ENCODER_B", 2),
            RIGHT_ENCODER_A = Preferences.getInstance().getInt("RIGHT_ENCODER_A", 3),
            RIGHT_ENCODER_B = Preferences.getInstance().getInt("RIGHT_ENCODER_B", 4);

    // Loader
    int LEFT_LOADER_SENSOR = Preferences.getInstance().getInt("LEFT_LOADER_SENSOR", 5),
            RIGHT_LOADER_SENSOR = Preferences.getInstance().getInt("RIGHT_LOADER_SENSOR", 6);
    int LEFT_LOADER_MOTOR = Preferences.getInstance().getInt("LEFT_LOADER_MOTOR", 5),
            RIGHT_LOADER_MOTOR = Preferences.getInstance().getInt("RIGHT_LOADER_MOTOR", 6);
    int LEFT_LOADER_PISTON_IN = Preferences.getInstance().getInt("LEFT_LOADER_PISTON_IN", 1),
            LEFT_LOADER_PISTON_OUT = Preferences.getInstance().getInt("LEFT_LOADER_PISTON_OUT", 2),
            RIGHT_LOADER_PISTON_IN = Preferences.getInstance().getInt("RIGHT_LOADER_PISTON_IN", 3),
            RIGHT_LOADER_PISTON_OUT = Preferences.getInstance().getInt("RIGHT_LOADER_PISTON_OUT", 4);

    // Winch
    int SHOOTER_POSITION = Preferences.getInstance().getInt("SHOOTER_POSITION", 1);
    int WINCH_MOTOR = Preferences.getInstance().getInt("WINCH_MOTOR", 7);
    int WINCH_RELEASE = Preferences.getInstance().getInt("WINCH_RELEASE", 7);
}
