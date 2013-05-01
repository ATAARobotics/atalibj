package edu.ata.subsystems;

import edu.first.identifiers.ReturnableNumber;
import edu.first.identifiers.SetteableNumber;
import edu.first.module.Module;
import edu.first.module.sensor.AccelerometerModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.PIDModule;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public final class Winch extends Subsystem implements SetteableNumber, PIDOutput, PIDSource {

    private static final double P = 8, I = 0, D = 0;
    private static final double range = 0.01;
    private final SpeedControllerModule winchMotor;
    private final ReturnableNumber sensor;
    private final PIDModule PID = new PIDModule(new PIDController(P, I, D, this, this));
    private double ZERO;
    
    {
        PID.setTolerance(range);
    }

    public Winch(SpeedControllerModule winchMotor, PotentiometerModule potentiometer) {
        super(new Module[]{winchMotor, potentiometer});
        this.winchMotor = winchMotor;
        this.sensor = potentiometer;
    }

    public Winch(SpeedControllerModule winchMotor, AccelerometerModule accelerometer) {
        super(new Module[]{winchMotor, accelerometer});
        this.winchMotor = winchMotor;
        this.sensor = accelerometer;
    }

    protected boolean disableSubsystem() {
        return PID.disable();
    }

    public void start() {
        // No thread needed
    }

    public void run() {
        // No thread needed
    }

    public void zero() {
        setZero(sensor.get());
    }

    public void setZero(double zero) {
        this.ZERO = zero;
        int ix = (int) (zero * 100.0); // scale it 
        double rounded = ((double) ix) / 100.0;
        Logger.log(Logger.Urgency.USERMESSAGE, "Winch zeroed @ " + rounded);
    }

    public void set(double value) {
        PID.enable();
        PID.setSetpoint(value);
    }

    public void stopMotor() {
        PID.disable();
        winchMotor.set(0);
    }

    public void move(double speed) {
        if (PID.isEnabled() && speed != 0) {
            PID.disable();
            winchMotor.set(speed);
        } else if (!PID.isEnabled()) {
            winchMotor.set(speed);
        }
    }

    public double getZero() {
        return ZERO;
    }

    public double getPosition() {
        return sensor.get() - ZERO;
    }

    public double getSensorPosition() {
        return sensor.get();
    }

    public double pidGet() {
        return getPosition();
    }

    public void pidWrite(double output) {
        if (Math.abs(output) > 0.2 && !PID.onTarget()) {
            winchMotor.set(-output);
        } else {
            winchMotor.set(0);
        }
    }
}