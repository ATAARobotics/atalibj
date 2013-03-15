package edu.ata.modules;

import edu.first.module.Module;
import edu.first.module.speedcontroller.SpeedControllerModule;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class AlignmentMotor implements SpeedController, Module {

    private final SpeedControllerModule speedController;
    private boolean locked = false;

    public AlignmentMotor(SpeedControllerModule speedController) {
        this.speedController = speedController;
    }

    public boolean enable() {
        return speedController.enable();
    }

    public boolean isEnabled() {
        return speedController.isEnabled();
    }

    public double get() {
        return speedController.get();
    }

    public synchronized void set(double speed) {
        if (!locked) {
            speedController.set(speed);
        }
    }

    public synchronized void set(double speed, byte syncGroup) {
        if (!locked) {
            speedController.set(speed, syncGroup);
        }
    }

    public synchronized SpeedControllerModule lock() {
        locked = true;
        return speedController;
    }

    public synchronized void unlock() {
        locked = false;
        speedController.set(0);
    }

    public void disable() {
        speedController.disable();
    }

    public void pidWrite(double output) {
        speedController.pidWrite(output);
    }
}