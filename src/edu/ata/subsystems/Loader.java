package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.DualActionSolenoidModule;
import edu.first.module.sensor.PotentiometerModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.BangBangModule;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.Timer;

public final class Loader extends Subsystem {

    private static final boolean coastShots = true;
    private static final long delay = 20L;
    private final DualActionSolenoidModule solenoid;
    private final BangBangModule shooterWheel;
    private final PotentiometerModule potentiometer;
    private final Object loaderChanges = new Object();
    private boolean fired;
    private boolean in, out;

    public Loader(DualActionSolenoidModule solenoid, BangBangModule shooterWheel,
            PotentiometerModule potentiometer) {
        super(new Module[]{solenoid, shooterWheel, potentiometer});
        this.solenoid = solenoid;
        this.shooterWheel = shooterWheel;
        this.potentiometer = potentiometer;
    }

    public void start() {
        startAtFixedDelay(delay);
    }

    public void run() {
        try {
            synchronized (loaderChanges) {
                loaderChanges.wait();
            }
            if (in) {
                solenoid.setIn();

                in = false;
            } else if (out) {
                solenoid.setOut();

                out = false;
            } else if (fired) {
                if (!solenoid.get()) {
                    solenoid.setOut();
                    Timer.delay(0.25);
                }
                if (coastShots) {
                    shooterWheel.setCoast(true);
                }

                Logger.log(Logger.Urgency.LOG, "Pot Before Shot - " + potentiometer.getPosition());
                Logger.log(Logger.Urgency.LOG, "Speed Before Shot - " + shooterWheel.getInput());
                double lowestSpeed = Double.POSITIVE_INFINITY;

                solenoid.setIn();
                Timer t = new Timer();
                t.start();
                while (t.get() <= 0.5) {
                    double speed = shooterWheel.getInput();
                    if (speed < lowestSpeed) {
                        lowestSpeed = speed;
                    }
                }
                t.stop();
                if (coastShots) {
                    shooterWheel.setCoast(false);
                }
                solenoid.setOut();

                Logger.log(Logger.Urgency.LOG, "Lowest Speed During Shot - " + lowestSpeed);
                Logger.log(Logger.Urgency.LOG, "Pot After Shot - " + potentiometer.getPosition());

                fired = false;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void update() {
        synchronized (loaderChanges) {
            loaderChanges.notify();
        }
    }

    public void fire() {
        fired = true;
        update();
    }

    public void setIn() {
        in = true;
        update();
    }

    public void setOut() {
        out = true;
        update();
    }

    public boolean isOut() {
        return solenoid.isOut();
    }
}