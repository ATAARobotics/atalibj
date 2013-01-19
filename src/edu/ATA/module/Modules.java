package edu.ATA.module;

import edu.ATA.main.PortMap;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.SolenoidModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.speedcontroller.SpikeRelayModule;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 * Interface to store all instances of modules. Modules are not enabled by
 * default, and need to be in order to be used.
 *
 * @author Team 4334
 */
public interface Modules extends PortMap {

    XboxController controller = new XboxController(new Joystick(JOYSTICK));
    RobotDriveModule robotDrive = new RobotDriveModule(new RobotDrive(DRIVE[0], DRIVE[1], DRIVE[2], DRIVE[3]));
    SpikeRelayModule spikeRelay = new SpikeRelayModule(new Relay(RELAY));
    SolenoidModule gearShifter = new SolenoidModule(new Solenoid(SOLENOID));
    Victor shooterMotor = new Victor(SHOOTER);
    SpeedControllerModule shooter = new SpeedControllerModule(shooterMotor);

}
