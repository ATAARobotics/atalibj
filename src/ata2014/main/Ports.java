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
    //   Right Drive - 5, 6, 7
    //   Left Loader - 4
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
    //   Compressor PSI: 1
    //   Left Encoder: 2, 3
    //   Right Encoder: 4, 5
    //   Left Loader Switch: 6
    //   Right Loader Switch: 7
//     Analog Input:
    //   Shooter Potentiometer: 1
//     Relays:
    //   Compressor Relay: 1
//
//
//
    // Drivetrain
    int LEFT_DRIVE_1 = Preferences.getInstance().getInt("LEFT_DRIVE_1", 5),
            LEFT_DRIVE_2 = Preferences.getInstance().getInt("LEFT_DRIVE_2", 6),
            LEFT_DRIVE_3 = Preferences.getInstance().getInt("LEFT_DRIVE_3", 7),
            RIGHT_DRIVE_1 = Preferences.getInstance().getInt("RIGHT_DRIVE_1", 1),
            RIGHT_DRIVE_2 = Preferences.getInstance().getInt("RIGHT_DRIVE_2", 2),
            RIGHT_DRIVE_3 = Preferences.getInstance().getInt("RIGHT_DRIVE_3", 3);
    int SHIFTER_IN = Preferences.getInstance().getInt("SHIFTER_IN", 5),
            SHIFTER_OUT = Preferences.getInstance().getInt("SHIFTER_OUT", 6);
    int LEFT_ENCODER_A = Preferences.getInstance().getInt("LEFT_ENCODER_A", 2),
            LEFT_ENCODER_B = Preferences.getInstance().getInt("LEFT_ENCODER_B", 3),
            RIGHT_ENCODER_A = Preferences.getInstance().getInt("RIGHT_ENCODER_A", 4),
            RIGHT_ENCODER_B = Preferences.getInstance().getInt("RIGHT_ENCODER_B", 5);

    // Loader
    int LEFT_LOADER_SENSOR = Preferences.getInstance().getInt("LEFT_LOADER_SENSOR", 6),
            RIGHT_LOADER_SENSOR = Preferences.getInstance().getInt("RIGHT_LOADER_SENSOR", 7);
    int LEFT_LOADER_MOTOR = Preferences.getInstance().getInt("LEFT_LOADER_MOTOR", 4),
            RIGHT_LOADER_MOTOR = Preferences.getInstance().getInt("RIGHT_LOADER_MOTOR", 8);
    int LEFT_LOADER_PISTON_IN = Preferences.getInstance().getInt("LEFT_LOADER_PISTON_IN", 1),
            LEFT_LOADER_PISTON_OUT = Preferences.getInstance().getInt("LEFT_LOADER_PISTON_OUT", 2),
            RIGHT_LOADER_PISTON_IN = Preferences.getInstance().getInt("RIGHT_LOADER_PISTON_IN", 3),
            RIGHT_LOADER_PISTON_OUT = Preferences.getInstance().getInt("RIGHT_LOADER_PISTON_OUT", 4);

    // Winch
    int SHOOTER_POSITION = Preferences.getInstance().getInt("SHOOTER_POSITION", 1);
    int WINCH_MOTOR = Preferences.getInstance().getInt("WINCH_MOTOR", 9);
    int WINCH_RELEASE = Preferences.getInstance().getInt("WINCH_RELEASE", 7);
    
    // Compressor
    int COMPRESSOR_PSI = Preferences.getInstance().getInt("COMPRESSOR_PSI", 1);
    int COMPRESSOR_RELAY = Preferences.getInstance().getInt("COMPRESSOR_RELAY", 1);
}
