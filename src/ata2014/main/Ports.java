package ata2014.main;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Ports {
    
    /*
    
    Speed Controllers:
        Front Left Drive - 1
        Front Right Drive - 2
        Back Left Drive - 3
        Back Right Drive - 4
        Left Loader - 5
        Right Loader - 6
        Winch Motor - 7
    
    Solenoids:
        Left Loader (in) - 1
        Left Loader (out) - 2
        Right Loader (in) - 3
        Right Loader (out) - 4
        Left Shifter (in) - 5
        Left Shifter (out) - 6
        Right Shifter (in) - 7
        Right Shifter (out) - 8
    
    */

    // Drivetrain
    int FRONT_LEFT_DRIVE = 1, FRONT_RIGHT_DRIVE = 2,
            BACK_LEFT_DRIVE = 3, BACK_RIGHT_DRIVE = 4;
    int LEFT_SHIFTER_IN = 5, LEFT_SHIFTER_OUT = 6, 
            RIGHT_SHIFTER_IN = 7, RIGHT_SHIFTER_OUT = 8;
    
    // Loader
    int LEFT_LOADER_MOTOR = 5, RIGHT_LOADER_MOTOR = 6;
    int LEFT_LOADER_PISTON_IN = 1,  LEFT_LOADER_PISTON_OUT = 2, 
            RIGHT_LOADER_PISTON_IN = 3, RIGHT_LOADER_PISTON_OUT = 4;
    
    // Winch
    int WINCH_MOTOR = 7;
    int WINCH_RELEASE = 3;
}
