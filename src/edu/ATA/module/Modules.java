package edu.ATA.module;

import edu.ATA.main.PortMap;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.SolenoidModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.speedcontroller.SpikeRelayModule;
import edu.ATA.module.subsystem.Drivetrain;
import edu.ATA.module.subsystem.GearedDrivetrain;
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
    SolenoidModule left1 = new SolenoidModule(new Solenoid(SOLENOID[0]));
    SolenoidModule left2 = new SolenoidModule(new Solenoid(SOLENOID[1]));
    SolenoidModule right1 = new SolenoidModule(new Solenoid(SOLENOID[2]));
    SolenoidModule right2 = new SolenoidModule(new Solenoid(SOLENOID[3]));
    SpikeRelayModule compressor = new SpikeRelayModule(new Relay(RELAY));
    SpeedControllerModule shooter = new SpeedControllerModule(new Victor(SHOOTER));
    // Subsystems
    Drivetrain drivetrain = new Drivetrain(robotDrive, controller, true);
    GearedDrivetrain gearedDrivetrain = new GearedDrivetrain(left1, left2, right1, right2, robotDrive, controller, true);
}
