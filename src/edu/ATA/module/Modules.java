package edu.ATA.module;

import edu.ATA.main.PortMap;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.sensor.EncoderModule;
import edu.ATA.module.sensor.HallEffectModule;
import edu.ATA.module.sensor.SolenoidModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.ATA.module.speedcontroller.SpikeRelayModule;
import edu.ATA.module.subsystems.Drivetrain;
import edu.ATA.module.subsystems.Shooter;
import edu.ATA.module.subsystems.VoltageTest;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
    EncoderModule encoder = new EncoderModule(new Encoder(ENCODER[0], ENCODER[1]));
    SpeedControllerModule shooterMotor = new SpeedControllerModule(new Victor(SHOOTER));
    SolenoidModule loader = new SolenoidModule(new Solenoid(LOADER));
    SolenoidModule reloader = new SolenoidModule(new Solenoid(RELOADER));
    HallEffectModule hallEffect = new HallEffectModule(new DigitalInput(HALLEFFECT));
    // Subsystems
    Drivetrain drivetrain = new Drivetrain(robotDrive, controller, compressor, left1, left2, right1, right2);
    Shooter shooter = new Shooter(controller, compressor, shooterMotor, loader, reloader);
    VoltageTest test = new VoltageTest(controller, shooterMotor, compressor, loader, reloader);
}
