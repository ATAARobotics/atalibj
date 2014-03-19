package ata2014;

import api.gordian.Object;
import api.gordian.Signature;
import ata2014.subsystems.Drive;
import ata2014.subsystems.Loader;
import ata2014.subsystems.Winch;
import edu.first.module.Module;
import edu.first.module.actuators.Drivetrain;
import edu.first.module.actuators.DualActionSolenoid;
import edu.first.module.actuators.DualActionSolenoidModule;
import edu.first.module.actuators.SpeedController;
import edu.first.module.actuators.TalonModule;
import edu.first.module.controllers.PIDController;
import edu.first.module.sensors.DigitalInput;
import edu.first.module.sensors.EncoderModule;
import edu.first.util.DriverstationInfo;
import org.gordian.method.GordianMethod;
import org.gordian.scope.GordianScope;
import org.gordian.value.GordianBoolean;
import org.gordian.value.GordianNumber;
import org.gordian.value.GordianString;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class Autonomous extends GordianScope {

    private static final GordianDrivetrain DRIVETRAIN = new GordianDrivetrain(Drive.drivetrain);
    private static final GordianEncoder LEFT_DRIVE_ENCODER = new GordianEncoder(Drive.leftDriveEncoder);
    private static final GordianEncoder RIGHT_DRIVE_ENCODER = new GordianEncoder(Drive.rightDriveEncoder);
    private static final GordianLimitSwitch WINCH_LIMIT = new GordianLimitSwitch(Winch.winchLimit);
    private static final GordianTalon WINCH_MOTOR = new GordianTalon(Winch.winchMotor);
    private static final GordianDualActionSolenoid WINCH_RELEASE = new GordianDualActionSolenoid(Winch.winchRelease);
    private static final GordianSpeedController LOADER_MOTORS = new GordianSpeedController(Loader.loaderMotors);
    private static final GordianDualActionSolenoid LOADER_PISTON = new GordianDualActionSolenoid(Loader.loaderPiston);
    private static final GordianPIDController LOADER_CONTROLLER = new GordianPIDController(Loader.loaderController);

    public Autonomous() {
        methods().put("isAutonomous", new GordianMethod(new Signature()) {
            public Object run(Object[] args) {
                return new GordianBoolean(DriverstationInfo.isAutonomous() && DriverstationInfo.isEnabled());
            }
        });
        variables().put("drivetrain", DRIVETRAIN);
        variables().put("leftDriveEncoder", LEFT_DRIVE_ENCODER);
        variables().put("rightDriveEncoder", RIGHT_DRIVE_ENCODER);
        variables().put("winchLimit", WINCH_LIMIT);
        variables().put("winchMotor", WINCH_MOTOR);
        variables().put("winchRelease", WINCH_RELEASE);
        variables().put("loaderMotors", LOADER_MOTORS);
        variables().put("loaderPiston", LOADER_PISTON);
        variables().put("loaderController", LOADER_CONTROLLER);
    }

    private static class GordianModule extends GordianScope {

        public GordianModule(final Module module) {
            methods().put("enable", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    module.enable();
                    return null;
                }
            });
            methods().put("disable", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    module.disable();
                    return null;
                }
            });
            methods().put("init", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    module.init();
                    return null;
                }
            });
            methods().put("isEnabled", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianBoolean(module.isEnabled());
                }
            });
        }
    }

    private static class GordianDrivetrain extends GordianModule {

        public GordianDrivetrain(final Drivetrain drivetrain) {
            super(drivetrain);
            methods().put("arcade", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS, GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.arcadeDrive(((GordianNumber) args[0]).getValue(), ((GordianNumber) args[1]).getValue());
                    return null;
                }
            });
            methods().put("tank", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS, GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.tankDrive(((GordianNumber) args[0]).getValue(), ((GordianNumber) args[1]).getValue());
                    return null;
                }
            });
            methods().put("drive", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS, GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.drive(((GordianNumber) args[0]).getValue(), ((GordianNumber) args[1]).getValue());
                    return null;
                }
            });
            methods().put("setMaxSpeed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.setMaxSpeed(((GordianNumber) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setReversed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianBoolean.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.setReversed(((GordianBoolean) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setReversedTurn", new GordianMethod(new Signature(new api.gordian.Class[]{GordianBoolean.CLASS})) {
                public Object run(Object[] args) {
                    drivetrain.setReversedTurn(((GordianBoolean) args[0]).getValue());
                    return null;
                }
            });
            methods().put("getLeftSpeed", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(drivetrain.getLeftSpeed());
                }
            });
            methods().put("getRightSpeed", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(drivetrain.getRightSpeed());
                }
            });
        }
    }

    private static class GordianEncoder extends GordianModule {

        public GordianEncoder(final EncoderModule encoder) {
            super(encoder);
            methods().put("getRate", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(encoder.getRate());
                }
            });
            methods().put("getDistance", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(encoder.getDistance());
                }
            });
            methods().put("setDistancePerPulse", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    encoder.setDistancePerPulse(((GordianNumber) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setMinRate", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    encoder.setMinRate(((GordianNumber) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setReverseDirection", new GordianMethod(new Signature(new api.gordian.Class[]{GordianBoolean.CLASS})) {
                public Object run(Object[] args) {
                    encoder.setReverseDirection(((GordianBoolean) args[0]).getValue());
                    return null;
                }
            });
            methods().put("isStopped", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianBoolean(encoder.isStopped());
                }
            });
            methods().put("pause", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    encoder.pause();
                    return null;
                }
            });
            methods().put("unpause", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    encoder.unpause();
                    return null;
                }
            });
            methods().put("reset", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    encoder.reset();
                    return null;
                }
            });
        }
    }

    private static class GordianLimitSwitch extends GordianModule {

        public GordianLimitSwitch(final DigitalInput input) {
            super(input);
            methods().put("getPosition", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianBoolean(input.getPosition());
                }
            });
        }
    }

    private static class GordianTalon extends GordianModule {

        public GordianTalon(final TalonModule controller) {
            super(controller);
            methods().put("getSpeed", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(controller.getSpeed());
                }
            });
            methods().put("getRawSpeed", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(controller.getRawSpeed());
                }
            });
            methods().put("setSpeed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    controller.setSpeed(((GordianNumber) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setRawSpeed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    controller.setRawSpeed(((GordianNumber) args[0]).getInt());
                    return null;
                }
            });
        }
    }

    private static class GordianSpeedController extends GordianScope {

        public GordianSpeedController(final SpeedController controller) {
            methods().put("getSpeed", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(controller.getSpeed());
                }
            });
            methods().put("getRawSpeed", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(controller.getRawSpeed());
                }
            });
            methods().put("setSpeed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    controller.setSpeed(((GordianNumber) args[0]).getValue());
                    return null;
                }
            });
            methods().put("setRawSpeed", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    controller.setRawSpeed(((GordianNumber) args[0]).getInt());
                    return null;
                }
            });
        }
    }

    private static class GordianDualActionSolenoid extends GordianModule {

        public GordianDualActionSolenoid(final DualActionSolenoidModule solenoid) {
            super(solenoid);
            methods().put("set", new GordianMethod(new Signature(new api.gordian.Class[]{GordianString.CLASS})) {
                public Object run(Object[] args) {
                    solenoid.set(direction(((GordianString) args[0]).getValue()));
                    return null;
                }
            });
            methods().put("get", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianString(solenoid.get().toString());
                }
            });
            methods().put("reverse", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    solenoid.reverse();
                    return null;
                }
            });
            methods().put("turnOff", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    solenoid.turnOff();
                    return null;
                }
            });
        }

        private DualActionSolenoid.Direction direction(String key) {
            if (key.equalsIgnoreCase("left")) {
                return DualActionSolenoid.Direction.LEFT;
            } else if (key.equalsIgnoreCase("right")) {
                return DualActionSolenoid.Direction.RIGHT;
            } else {
                return DualActionSolenoid.Direction.OFF;
            }
        }
    }

    private static class GordianPIDController extends GordianModule {

        public GordianPIDController(final PIDController controller) {
            super(controller);
            methods().put("setSetpoint", new GordianMethod(new Signature(new api.gordian.Class[]{GordianNumber.CLASS})) {
                public Object run(Object[] args) {
                    controller.setSetpoint(((GordianNumber) args[0]).getValue());
                    return null;
                }
            });
            methods().put("getSetpoint", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianNumber(controller.getSetpoint());
                }
            });
            methods().put("atSetpoint", new GordianMethod(new Signature()) {
                public Object run(Object[] args) {
                    return new GordianBoolean(controller.onTarget());
                }
            });
        }
    }
}
