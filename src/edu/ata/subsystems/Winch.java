package edu.ata.subsystems;

import edu.first.identifiers.SetteableNumber;
import edu.first.module.Module;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.PIDModule;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public final class Winch extends Subsystem implements SetteableNumber, PIDOutput, PIDSource {

    private static final double P = 0.17, I = 0, D = 0;
    private final SpeedControllerModule winchMotor;
    private final PotentiometerModule potentiometer;
    private final PIDModule PID = new PIDModule(new PIDController(P, I, D, this, this));

    public Winch(SpeedControllerModule winchMotor, PotentiometerModule potentiometer) {
        super(new Module[]{winchMotor, potentiometer});
        this.winchMotor = winchMotor;
        this.potentiometer = potentiometer;
    }

    protected boolean enableSubsystem() {
        return PID.enable();
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

    public void set(double value) {
        PID.enable();
        PID.setSetpoint(value);
    }

    public void move(double speed) {
        if (speed != 0) {
            PID.disable();
            winchMotor.set(speed);
        }
    }

    public double pidGet() {
        return potentiometer.getPosition();
    }

    public void pidWrite(double output) {
        winchMotor.set(output);
    }
}