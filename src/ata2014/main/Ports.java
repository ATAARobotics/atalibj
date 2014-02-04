package ata2014.main;

import org.gordian.value.GordianNumber;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Ports {

    // Drivetrain
    int FRONT_LEFT_DRIVE = ((GordianNumber) Preferences.preferences.get("FRONT_LEFT_DRIVE")).getInt(),
            FRONT_RIGHT_DRIVE = ((GordianNumber) Preferences.preferences.get("FRONT_RIGHT_DRIVE")).getInt(),
            BACK_LEFT_DRIVE = ((GordianNumber) Preferences.preferences.get("BACK_LEFT_DRIVE")).getInt(),
            BACK_RIGHT_DRIVE = ((GordianNumber) Preferences.preferences.get("BACK_RIGHT_DRIVE")).getInt();
    int SHIFTER_IN = ((GordianNumber) Preferences.preferences.get("SHIFTER_IN")).getInt(),
            SHIFTER_OUT = ((GordianNumber) Preferences.preferences.get("SHIFTER_OUT")).getInt();
    int LEFT_ENCODER_A = ((GordianNumber) Preferences.preferences.get("LEFT_ENCODER_A")).getInt(),
            LEFT_ENCODER_B = ((GordianNumber) Preferences.preferences.get("LEFT_ENCODER_B")).getInt(),
            RIGHT_ENCODER_A = ((GordianNumber) Preferences.preferences.get("RIGHT_ENCODER_A")).getInt(),
            RIGHT_ENCODER_B = ((GordianNumber) Preferences.preferences.get("RIGHT_ENCODER_B")).getInt();

    // Loader
    int LEFT_LOADER_SENSOR = ((GordianNumber) Preferences.preferences.get("LEFT_LOADER_SENSOR")).getInt(),
            RIGHT_LOADER_SENSOR = ((GordianNumber) Preferences.preferences.get("RIGHT_LOADER_SENSOR")).getInt();
    int LEFT_LOADER_MOTOR = ((GordianNumber) Preferences.preferences.get("LEFT_LOADER_MOTOR")).getInt(),
            RIGHT_LOADER_MOTOR = ((GordianNumber) Preferences.preferences.get("RIGHT_LOADER_MOTOR")).getInt();
    int LEFT_LOADER_PISTON_IN = ((GordianNumber) Preferences.preferences.get("LEFT_LOADER_PISTON_IN")).getInt(),
            LEFT_LOADER_PISTON_OUT = ((GordianNumber) Preferences.preferences.get("LEFT_LOADER_PISTON_OUT")).getInt(),
            RIGHT_LOADER_PISTON_IN = ((GordianNumber) Preferences.preferences.get("RIGHT_LOADER_PISTON_IN")).getInt(),
            RIGHT_LOADER_PISTON_OUT = ((GordianNumber) Preferences.preferences.get("RIGHT_LOADER_PISTON_OUT")).getInt();

    // Winch
    int SHOOTER_POSITION = ((GordianNumber) Preferences.preferences.get("SHOOTER_POSITION")).getInt();
    int WINCH_MOTOR = ((GordianNumber) Preferences.preferences.get("WINCH_MOTOR")).getInt();
    int WINCH_RELEASE = ((GordianNumber) Preferences.preferences.get("WINCH_RELEASE")).getInt();
}
