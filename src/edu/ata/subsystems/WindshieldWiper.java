package edu.ata.subsystems;

import edu.first.module.Module;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.first.module.subsystem.Subsystem;

public final class WindshieldWiper extends Subsystem {

    private static final long delay = 20L;
    private final SpeedControllerModule windshieldWiper;
    private final Object wiperChanges = new Object();
    private double speed;

    public WindshieldWiper(SpeedControllerModule windshieldWiper) {
        super(new Module[]{windshieldWiper});
        this.windshieldWiper = windshieldWiper;
    }

    public void start() {
        startAtFixedDelay(delay);
    }

    public void run() {
        try {
            synchronized (wiperChanges) {
                wiperChanges.wait();
            }
            // Manual
            windshieldWiper.set(speed);
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
}