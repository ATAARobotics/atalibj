package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.actuator.DualActionSolenoid;
import edu.first.module.subsystem.Subsystem;
import edu.wpi.first.wpilibj.Timer;

public final class Loader extends Subsystem {

    private static final long delay = 20L;
    private final DualActionSolenoid solenoid;
    private final Object loaderChanges = new Object();
    private boolean fired;
    private boolean in, out;

    public Loader(DualActionSolenoid solenoid) {
        super(new Module[]{solenoid});
        this.solenoid = solenoid;
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
                Timer.delay(0.5);
                solenoid.setOut();

                fired = false;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void update() {
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
}