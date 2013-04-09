package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.sensor.VexMotorEncoderModule;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.PIDModule;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;

public final class WindshieldWiper extends Subsystem {

    private static final long delay = 20L;
    private static final double minSpeed = 0.3, maxSpeed = 0.6;
    private static final double P = 1, I = 0, D = 0;
    private static final double tolerance = 0.05;
    private static final double IN = 1, OUT = 0;
    private static final double TIMEOUT = 5;
    private final SpeedControllerModule windshieldWiper;
    private final VexMotorEncoderModule windshildWiperEncoder;
    private final PIDModule PID;
    private final Timer timer = new Timer();
    private final Object wiperChanges = new Object();
    private double speed;
    private boolean wipe;
    private boolean auto;
    private double setpoint;

    public WindshieldWiper(SpeedControllerModule windshieldWiper, VexMotorEncoderModule windshildWiperEncoder) {
        super(new Module[]{windshieldWiper, windshildWiperEncoder});
        this.windshieldWiper = windshieldWiper;
        this.windshildWiperEncoder = windshildWiperEncoder;
        this.PID = new PIDModule(new PIDController(P, I, D, windshildWiperEncoder, windshieldWiper));
        PID.setTolerance(tolerance);
        PID.setOutputRange(minSpeed, maxSpeed);
    }

    protected boolean enableSubsystem() {
        // Stay where it started
        PID.setSetpoint(windshildWiperEncoder.pidGet());

        return PID.enable();
    }

    protected boolean disableSubsystem() {
        return PID.disable();
    }

    public void start() {
        startAtFixedDelay(delay);
    }

    public void run() {
        try {
            synchronized (wiperChanges) {
                wiperChanges.wait();
            }
            if (wipe) {
                // Wiping back and forth
                PID.enable();
                doWipe();
                wipe = false;
            } else if (auto) {
                // Going somewhere
                PID.enable();
                doAuto();
                auto = false;
            } else {
                // Manual
                PID.disable();
                windshieldWiper.set(speed);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void update() {
        synchronized (wiperChanges) {
            wiperChanges.notify();
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        update();
    }

    public void setPlacement(double setpoint) {
        auto = true;
        this.setpoint = setpoint;
        update();
    }

    public void wipe() {
        auto = false;
        wipe = true;
        update();
    }

    public double getPosition() {
        return windshildWiperEncoder.pidGet();
    }

    private void doWipe() {
        setpoint = IN;
        doAuto();
        
        setpoint = OUT;
        doAuto();
    }

    private void doAuto() {
        PID.setSetpoint(setpoint);

        timer.reset();
        timer.start();

        while (!PID.onTarget() && timer.get() < TIMEOUT) {
            Timer.delay(0.01);
        }

        // Stay for 0.25 seconds
        Timer.delay(0.25);
    }
}