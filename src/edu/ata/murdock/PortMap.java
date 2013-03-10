package edu.ata.murdock;

/**
 * Port mapping of all components of the robot.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface PortMap {

    int JOYSTICK_1 = PortMapFile.getPort("Joystick1", 1);
    int JOYSTICK_2 = PortMapFile.getPort("Joystick2", 2);
    // Digital sidecar output
    int SHOOTER_PORT = PortMapFile.getPort("Shooter", 1);
    int SHOOTER_ALIGNMENT_PORT = PortMapFile.getPort("ShooterAlignment", 2);
    int[] DRIVE = {PortMapFile.getPort("LeftBack", 5), PortMapFile.getPort("LeftFront", 6),
        PortMapFile.getPort("RightBack", 3), PortMapFile.getPort("JRightFront", 4)};
    // Digital sidecar input
    int HALLEFFECT_PORT = PortMapFile.getPort("HallEffect", 1);
    int[] ENCODER = {PortMapFile.getPort("EncoderA", 2), PortMapFile.getPort("EncoderB", 3)};
    int PSI_SWITCH = PortMapFile.getPort("PSISwitch", 5);
    // Analog sidecar
    int SHOOTER_POSITION = PortMapFile.getPort("Pot", 1);
    int GYRO = PortMapFile.getPort("Gyro", 2);
    // Pneumatics sidecar
    int GEAR_UP = PortMapFile.getPort("GearUp", 3);
    int GEAR_DOWN = PortMapFile.getPort("GearDown", 4);
    int BACK_IN = PortMapFile.getPort("BackIn", 1);
    int BACK_OUT = PortMapFile.getPort("BackOut", 2);
    int LOAD_OUT = PortMapFile.getPort("LoadOut", 7);
    int BITCH_BAR_IN_PORT = PortMapFile.getPort("BitchBarIn", 5);
    int BITCH_BAR_OUT_PORT = PortMapFile.getPort("BitchBarOut", 6);
    // Relays
    int COMPRESSOR = PortMapFile.getPort("Compressor", 1);
}