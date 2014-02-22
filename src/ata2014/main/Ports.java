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
    //   Left Loader - 9
    //   Right Loader - 4
    //   Winch Motor - 10
//     Solenoids:
    //   Loader (in) - 1
    //   Loader (out) - 2
    //   Shifter (in) - 3
    //   Shifter (out) - 4
    //   Back load (in) - 5
    //   Back load (out) - 6
    //   Winch Release - 7
//     Digital Input:
    //   Compressor PSI: 1
    //   Left Encoder: 2, 3
    //   Right Encoder: 4, 5
    //   Left Loader Switch: 6
    //   Right Loader Switch: 7
    //   Winch limit Switch: 14
//     Analog Input:
    //   Shooter Potentiometer: 1
    //   Left Intake Potentiometer: 7
    //   Right Intake Potentiometer: 2
//     Relays:
    //   Compressor Relay: 1
//
//
//
    // Drivetrain
    int LEFT_DRIVE_1 = Preferences.getInstance().getInt("LEFTDRIVE1", 5),
            LEFT_DRIVE_2 = Preferences.getInstance().getInt("LEFTDRIVE2", 6),
            LEFT_DRIVE_3 = Preferences.getInstance().getInt("LEFTDRIVE3", 7),
            RIGHT_DRIVE_1 = Preferences.getInstance().getInt("RIGHTDRIVE1", 1),
            RIGHT_DRIVE_2 = Preferences.getInstance().getInt("RIGHTDRIVE2", 2),
            RIGHT_DRIVE_3 = Preferences.getInstance().getInt("RIGHTDRIVE3", 3);
    int SHIFTER_IN = Preferences.getInstance().getInt("SHIFTERIN", 3),
            SHIFTER_OUT = Preferences.getInstance().getInt("SHIFTEROUT", 4);
    int LEFT_ENCODER_A = Preferences.getInstance().getInt("LEFTENCODERA", 2),
            LEFT_ENCODER_B = Preferences.getInstance().getInt("LEFTENCODERB", 3),
            RIGHT_ENCODER_A = Preferences.getInstance().getInt("RIGHTENCODERA", 4),
            RIGHT_ENCODER_B = Preferences.getInstance().getInt("RIGHTENCODERB", 5);

    // Loader
    int LEFT_LOADER_SWITCH = Preferences.getInstance().getInt("LEFTLOADERSWITCH", 6),
            RIGHT_LOADER_SWITCH = Preferences.getInstance().getInt("RIGHTLOADERSWITCH", 7);
    int LEFT_LOADER_POT = Preferences.getInstance().getInt("LEFTLOADERPOT", 7),
            RIGHT_LOADER_POT = Preferences.getInstance().getInt("RIGHTLOADERPOT", 2);
    int LEFT_LOADER_MOTOR = Preferences.getInstance().getInt("LEFTLOADERMOTOR", 9),
            RIGHT_LOADER_MOTOR = Preferences.getInstance().getInt("RIGHTLOADERMOTOR", 4);
    int LOADER_PISTON_IN = Preferences.getInstance().getInt("LOADERPISTONIN", 1),
            LOADER_PISTON_OUT = Preferences.getInstance().getInt("LOADERPISTONOUT", 2);

    // Back loader
    int BACK_LOADER_IN = Preferences.getInstance().getInt("BACKLOADERIN", 5);
    int BACK_LOADER_OUT = Preferences.getInstance().getInt("BACKLOADEROUT", 6);

    // Winch
    int SHOOTER_POSITION = Preferences.getInstance().getInt("SHOOTERPOSITION", 1);
    int WINCH_MOTOR = Preferences.getInstance().getInt("WINCHMOTOR", 10);
    int WINCH_RELEASE_OUT = Preferences.getInstance().getInt("WINCHRELEASEOUT", 7),
            WINCH_RELEASE_IN = Preferences.getInstance().getInt("WINCHRELEASEIN", 8);
    int WINCH_LIMIT = Preferences.getInstance().getInt("WINCHLIMIT", 14);

    // Compressor
    int COMPRESSOR_PSI = Preferences.getInstance().getInt("COMPRESSORPSI", 1);
    int COMPRESSOR_RELAY = Preferences.getInstance().getInt("COMPRESSORRELAY", 1);
}
