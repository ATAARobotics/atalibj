package edu.ATA.twolf;

public interface PortMap {

    int JOYSTICK_1 = 1;
    int JOYSTICK_2 = 2;
    // Digital sidecar output
    int SHOOTER_PORT = 1;
    int SHOOTER_ALIGNMENT_PORT = 2;
    int[] DRIVE = {5, 6, 3, 4};
    // Digital sidecar input
    int SHOOTER_LIMIT_SWITCH = 1;
    int HALLEFFECT_PORT = 2;
    int[] ENCODER = {3, 4};
    int PSI_SWITCH = 5;
    // Analog sidecar
    int SHOOTER_POSITION = 1;
    int GYRO = 2;
    // Pneumatics sidecar
    int GEAR_UP = 1;
    int GEAR_DOWN = 2;
    int SHORT_ALIGN_OUT_PORT = 3;
    int SHORT_ALIGN_IN_PORT = 4;
    int LONG_ALIGN_OUT_PORT = 5;
    int LONG_ALIGN_IN_PORT = 6;
    int STATIC_ALIGN_IN_PORT = 7;
    int STATIC_ALIGN_OUT_PORT = 8;
    // Relays
    int LOADER_PORT = 1;
    int COMPRESSOR = 2;
}
