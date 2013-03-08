package edu.ata.murdock;

/**
 * Port mapping of all components of the robot.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface PortMap {

    int JOYSTICK_1 = 1;
    int JOYSTICK_2 = 2;
    // Digital sidecar output
    int SHOOTER_PORT = 1;
    int SHOOTER_ALIGNMENT_PORT = 2;
    int[] DRIVE = {5, 6, 3, 4};
    // Digital sidecar input
    int HALLEFFECT_PORT = 1;
    int[] ENCODER = {2, 3};
    int PSI_SWITCH = 5;
    // Analog sidecar
    int SHOOTER_POSITION = 1;
    int GYRO = 2;
    // Pneumatics sidecar
    int GEAR_UP = 3;
    int GEAR_DOWN = 4;
    int BACK_IN = 1;
    int BACK_OUT = 2;
    int LOAD_IN = 7;
    int LOAD_OUT = 8;
    int BITCH_BAR_IN_PORT = 5;
    int BITCH_BAR_OUT_PORT = 6;
    // Relays
    int COMPRESSOR = 1;
}