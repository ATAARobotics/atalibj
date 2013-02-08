package edu.ATA.main;

/**
 * Interface that carries all of the port numbers of the various components on
 * the robot.
 *
 * @author Team 4334
 */
public interface PortMap {

    // Joysticks
    int JOYSTICK_1 = 1;
    int JOYSTICK_2 = 2;
    
    // Digital sidecar output
    int DRIVE[] = {5, 4, 2, 3};
    int SHOOTER_PORT = 1;
    int SHOOTER_ALIGNMENT_PORT = 6;
    
    // Digital sidecar input
    int HALLEFFECT_PORT = 1;
    int ENCODER[] = {2, 3};
    
    // Analog sidecar
    int SHOOTER_POSITION = 1;
    
    // Pneumatics sidecar
    int GEAR_1 = 1;
    int GEAR_2 = 2;
    int LOADER_PORT = 3;
    int RELOADER_PORT = 4;
    int SHORT_ALIGN_PORT = 5;
    int LONG_ALIGN_PORT = 6;
    int STATIC_ALIGN_PORT = 7;
    
    // Relays
    int COMPRESSOR = 1;
}
