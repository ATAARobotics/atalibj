package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.driving.RobotDriveModule;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.sensor.GyroModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.PIDModule;
import edu.wpi.first.wpilibj.PIDController;

public final class MovementSystem extends Subsystem {

    private static final long delay = 20L;
    private static final double dP = 0.001, dI = 0, dD = 0.001;
    private static final double tP = 0.007, tI = 0, tD = 0.001;
    private static final double turnMin = 0.3, turnMax = 0.4;
    private static final double forwardsMin = 0, forwardsMax = 0.7;
    private final PIDModule forwards;
    private final PIDModule turning;
    private final Object movementChanges = new Object();
    private boolean forward;
    private boolean turn;
    private double setpoint;

    public MovementSystem(RobotDriveModule driveModule, EncoderModule encoder, GyroModule gyroModule) {
        this(new PIDModule(new PIDController(dP, dI, dD, encoder, driveModule.getForwards())),
                new PIDModule(new PIDController(tP, tI, tD, gyroModule, driveModule.getTurning())));
    }

    public MovementSystem(PIDModule forwards, PIDModule turning) {
        super(new Module[]{forwards, turning});
        this.forwards = forwards;
        this.turning = turning;

        forwards.setOutputRange(forwardsMin, forwardsMax);
        turning.setOutputRange(turnMin, turnMax);
    }

    public void start() {
        startAtFixedDelay(delay);
    }

    public void run() {
        try {
            synchronized (movementChanges) {
                movementChanges.wait();
            }
            if (forward) {
                forwards.setSetpoint(setpoint);
                turning.disable();
                forwards.enable();
            } else if (turn) {
                turning.setSetpoint(setpoint);
                forwards.disable();
                turning.enable();
            } else {
                forwards.disable();
                turning.disable();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void update() {
        synchronized (movementChanges) {
            movementChanges.notify();
        }
    }

    public void driveToDistance(double distance) {
        forward = true;
        turn = false;
        
        setpoint = distance;
        update();
    }

    public void turnToAngle(double angle) {
        forward = false;
        turn = true;
        
        setpoint = angle;
        update();
    }

    public void stopMovement() {
        forward = false;
        turn = false;
        update();
    }
}