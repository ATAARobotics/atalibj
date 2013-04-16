package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.DualActionSolenoidModule;
import edu.first.module.subsystem.Subsystem;
import edu.first.module.target.BangBangModule;
import edu.wpi.first.wpilibj.Timer;

public final class Loader extends Subsystem {

    private static final boolean coastShots = true;
    private static final long delay = 20L;
    private final DualActionSolenoidModule solenoid;
    private final BangBangModule shooterWheel;
    private final Object loaderChanges = new Object();
    private boolean fired;
    private boolean in, out;

    public Loader(DualActionSolenoidModule solenoid, BangBangModule shooterWheel) {
        super(new Module[]{solenoid, shooterWheel});
        this.solenoid = solenoid;
        this.shooterWheel = shooterWheel;
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
                solenoid.setIn();
                if (coastShots) {
                    shooterWheel.setCoast(true);
                }
                Timer.delay(0.5);
                if (coastShots) {
                    shooterWheel.setCoast(false);
                }
                solenoid.setOut();

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