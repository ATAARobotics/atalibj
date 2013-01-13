package edu.ATA.main;

import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;
import edu.ATA.module.pid.PIDModule;
import edu.ATA.module.sensor.EncoderModule;
import edu.ATA.module.sensor.GyroModule;
import edu.ATA.module.speedcontroller.SpeedControllerModule;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
import java.util.Hashtable;

public final class UnitTests {

    final static SpeedControllerModule m1 = new SpeedControllerModule(new Jaguar(1));
    final static SpeedControllerModule m2 = new SpeedControllerModule(new Jaguar(2));
    final static XboxController xboxController = new XboxController(new Joystick(1));
    final static RobotDriveModule robotDrive = new RobotDriveModule(new RobotDrive(m1, m2));
    final static Solenoid solenoid = new Solenoid(1);
    final static EncoderModule encoder = new EncoderModule(new Encoder(1, 2));
    final static PIDModule pid = new PIDModule(new PIDController(1, 0, 0, encoder, m1));
    final static GyroModule gyro = new GyroModule(new Gyro(1));
    final static AnalogChannel potentiometer = new AnalogChannel(2);

    static {
    }

    private UnitTests() {
    }
    public static final Robot basicDrive = new DefaultRobot() {
        public String name() {
            return "Basic Drive";
        }

        public void robotInit() throws Error {
            robotDrive.enable();
            xboxController.enable();
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopPeriodic() throws Error {
            robotDrive.arcadeDrive(xboxController.getLeftDistanceFromMiddle(), xboxController.getRightX());
        }
    };
    public static final Robot shooter = new DefaultRobot() {
        Timer timer = new Timer();
        
        public String name() {
            return "Shooter";
        }

        public void robotInit() throws Error {
            xboxController.enable();
            m1.enable();
            m2.enable();
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopInit() {
            timer.reset();
            timer.start();
        }

        public void teleopPeriodic() throws Error {
            m1.set(xboxController.getRightDistanceFromMiddle());
            m2.set(xboxController.getRightDistanceFromMiddle());
            SmartDashboard.putNumber("Speed", xboxController.getRightDistanceFromMiddle());
            solenoid.set(xboxController.getRightBumper());
        }
    };
    public static final Robot encoderTest = new DefaultRobot() {
        public String name() {
            return "Encoder";
        }

        public void robotInit() throws Error {
            encoder.enable();
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopPeriodic() throws Error {
            SmartDashboard.putNumber("Encoder", encoder.getRate());
        }
    };
    public static final Robot gyroTest = new DefaultRobot() {
        public String name() {
            return "Gyro";
        }

        public void robotInit() throws Error {
            gyro.enable();
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopPeriodic() throws Error {
            SmartDashboard.putNumber("Gyro", gyro.getAngle());
        }
    };
    public static final Robot potTest = new DefaultRobot() {
        public String name() {
            return "Pot";
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopPeriodic() throws Error {
            SmartDashboard.putNumber("Pot", potentiometer.getVoltage());
        }
    };
    public static final Robot pidTest = new DefaultRobot() {
        public String name() {
            return "PID";
        }

        public void robotInit() throws Error {
            pid.enable();
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopInit() {
            try {
                pid.setSetpoint(SmartDashboard.getNumber("PID"));
            } catch (TableKeyNotDefinedException ex) {
                SmartDashboard.putNumber("PID", 0);
                pid.setSetpoint(0);
            }
        }

        public void teleopPeriodic() throws Error {
        }
    };
    public static final Robot bangBangTest = new DefaultRobot() {
        public String name() {
            return "Bang-Bang";
        }

        public void robotInit() throws Error {
            encoder.enable();
            m1.enable();
            m2.enable();
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopPeriodic() throws Error {
            double desiredSpeed = SmartDashboard.getNumber("Speed");
            if (encoder.getRate() < desiredSpeed) {
                m1.set(1);
                m2.set(1);
            } else {
                m1.set(0);
                m2.set(0);
            }
        }
    };
    public static final Robot controllerTest = new DefaultRobot() {
        public String name() {
            return "Controller";
        }

        public void robotInit() throws Error {
            xboxController.enable();
        }

        public void autonomousPeriodic() throws Error {
        }

        public void teleopPeriodic() throws Error {
            SmartDashboard.putNumber("RightX", xboxController.getRightX());
            SmartDashboard.putNumber("RightY", xboxController.getRightY());
            SmartDashboard.putNumber("LeftX", xboxController.getLeftX());
            SmartDashboard.putNumber("LeftY", xboxController.getLeftY());
            SmartDashboard.putNumber("RightDistance", xboxController.getRightDistanceFromMiddle());
            SmartDashboard.putNumber("LeftDistance", xboxController.getLeftDistanceFromMiddle());
        }
    };
    public static final Hashtable robots = new Hashtable();

    static {
        robots.put(basicDrive.name(), basicDrive);
        robots.put(encoderTest.name(), encoderTest);
        robots.put(gyroTest.name(), gyroTest);
        robots.put(potTest.name(), potTest);
        robots.put(pidTest.name(), pidTest);
        robots.put(bangBangTest.name(), bangBangTest);
        robots.put(controllerTest.name(), controllerTest);
        robots.put(shooter.name(), shooter);
    }
}
