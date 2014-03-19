package ata2014.controllers;

import edu.first.identifiers.AverageInputGroup;
import edu.first.identifiers.Input;
import edu.first.identifiers.Output;
import edu.first.module.Module;
import edu.first.module.actuators.Drivetrain;
import edu.first.module.controllers.PIDController;
import edu.first.module.joysticks.BindingJoystick;
import edu.first.module.joysticks.Joystick.Axis;
import edu.first.module.sensors.Encoder;

/**
 * PID model from the drivetrain, to accurately portray speed based off of
 * encoders.
 *
 * Take note that the internal PID will only affect the drivetrain <b>when bind
 * is run.</b>
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class DrivingPID extends Module.StandardModule {

    private double maxSpeed = 1;
    private final Drivetrain drivetrain;
    private final Input speed;
    private final PIDController pid;
    private final SpeedBuffer speedBuffer = new SpeedBuffer();

    public DrivingPID(Drivetrain drivetrain, Encoder encoder, double P, double I, double D, double maxSpeed) {
        this.drivetrain = drivetrain;
        this.speed = encoder;
        this.pid = new PIDController(speed, speedBuffer, P, I, D);
        this.maxSpeed = maxSpeed;
    }

    public DrivingPID(Drivetrain drivetrain, Encoder leftEncoder, Encoder rightEncoder, double P, double I, double D, double maxSpeed) {
        this.drivetrain = drivetrain;
        this.speed = new AverageInputGroup(new Input[]{leftEncoder, rightEncoder});
        this.pid = new PIDController(speed, speedBuffer, P, I, D);
        this.maxSpeed = maxSpeed;
    }

    public PIDController getPID() {
        return pid;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BindingJoystick.DualAxisBind getArcade(Axis a1, Axis a2) {
        return new ArcadeBind(a1, a2);
    }

    protected void enableModule() {
        pid.enable();
    }

    protected void disableModule() {
        speedBuffer.set(0);
        pid.disable();
    }

    public void init() {
        pid.init();
    }

    private final class ArcadeBind extends BindingJoystick.DualAxisBind {

        public ArcadeBind(Axis a1, Axis a2) {
            super(a1, a2);
        }

        public void doBind(double axis1, double axis2) {
            // give pid value to calculate (axis1 should be -1 to +1 so it can be used as a percentage of max speed)
            pid.set(axis1 * maxSpeed);
            // take speed from buffer (set by pid) and use pure turn
            drivetrain.arcadeDrive(speedBuffer.value, axis2);
        }
    }

    private final class SpeedBuffer implements Output {

        private double value;

        public void set(double value) {
            this.value = value;
        }
    }
}
