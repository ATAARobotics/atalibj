package edu.first.module.target;

import edu.first.module.Module;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.sensor.GyroModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.utils.DriverstationInfo;
import edu.wpi.first.wpilibj.Timer;

/**
 * Subsystem to control two PID systems that move a drivetrain. It allows the
 * drivetrain to move forwards and/or turn.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class MovingModule extends Subsystem {

    private final double distanceTolerance;
    private final double turningTolerance;
    private final edu.wpi.first.wpilibj.PIDController forwards;
    private final edu.wpi.first.wpilibj.PIDController turning;
    private final EncoderModule encoder;
    private final GyroModule gyro;

    /**
     * Constructs the subsystem using the appropriate sensors and output device.
     *
     * @param forwards encoder to measure distance (ensure it is in distance
     * mode)
     * @param turning gyro to measure angle
     * @param moving drivetrain to move
     * @param fP forward P coefficient
     * @param fI forward I coefficient
     * @param fD forward D coefficient
     * @param tP turning P coefficient
     * @param tI turning I coefficient
     * @param tD turning D coefficient
     * @param distanceTolerance maximum acceptable distance error
     * @param turningTolerance maximum acceptable angle error
     * @param sMaxSpeed maximum speed
     * @param sMinSpeed minimum speed
     * @param tMaxSpeed maximum turn
     * @param tMinSpeed minimum turn
     */
    public MovingModule(EncoderModule forwards, GyroModule turning, RobotDriveModule moving,
            double fP, double fI, double fD, double tP, double tI, double tD,
            double distanceTolerance, double turningTolerance,
            double sMaxSpeed, double sMinSpeed, double tMaxSpeed, double tMinSpeed) {
        super(new Module[]{forwards, turning, moving});
        this.distanceTolerance = distanceTolerance;
        this.turningTolerance = turningTolerance;
        this.encoder = forwards;
        this.gyro = turning;
        this.forwards = new edu.wpi.first.wpilibj.PIDController(fP, fI, fD, forwards, 
                moving.pidForward(sMinSpeed, sMaxSpeed));
        this.turning = new edu.wpi.first.wpilibj.PIDController(tP, tI, tD, turning, 
                moving.pidTurn(tMinSpeed, tMaxSpeed));
    }

    /**
     * Moves the drivetrain forwards to an encoder value.
     *
     * @param distance encoder value to set as the setpoint
     */
    public void moveForwards(double distance) {
        encoder.reset();
        forwards.setSetpoint(distance);

        forwards.enable();

        String period = DriverstationInfo.getGamePeriod();
        while (Math.abs(encoder.getDistance() - distance) > distanceTolerance
                && period.equals(DriverstationInfo.getGamePeriod())) {
            Timer.delay(0.02);
        }

        forwards.disable();
    }

    /**
     * Turns the drivetrain to an angle from the current position.
     *
     * @param angle gyro value to turn to
     */
    public void turn(double angle) {
        gyro.reset();
        turning.setSetpoint(angle);

        turning.enable();

        while (true) {
            if (Math.abs(gyro.getAngle() - angle) < turningTolerance) {
                // Has to be at setpoint for > 0.3 seconds
                Timer.delay(0.3);
                if (Math.abs(gyro.getAngle() - angle) < turningTolerance) {
                    break;
                }
            }
            Timer.delay(0.02);
        }

        turning.disable();
    }
}