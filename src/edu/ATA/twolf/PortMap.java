package edu.ATA.twolf;

public interface PortMap {

    int JOYSTICK_1 = 1;
    int JOYSTICK_2 = 2;
    // Digital sidecar output
    int SHOOTER_PORT = 1;
    int SHOOTER_ALIGNMENT_PORT = 2;
    int[] DRIVE = {5, 6, 3, 4};
    // Digital sidecar input
    int SHOOTER_LIMIT_SWITCH = 4;
    int HALLEFFECT_PORT = 1;
    int[] ENCODER = {2, 3};
    int PSI_SWITCH = 5;
    // Analog sidecar
    int SHOOTER_POSITION = 1;
    int GYRO = 2;
    // Pneumatics sidecar
    int GEAR_UP = 5;
    int GEAR_DOWN = 6;
    int SHORT_ALIGN_OUT_PORT = 1;
    int SHORT_ALIGN_IN_PORT = 2;
    int LONG_ALIGN_OUT_PORT = 7;
    int LONG_ALIGN_IN_PORT = 8;
    int BITCH_BAR_IN_PORT = 3;
    int BITCH_BAR_OUT_PORT = 4;
    // Relays
    int LOADER_PORT = 1;
    int COMPRESSOR = 2;
}
