package edu.ATA.module.speedcontroller;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Module specifically designed to wrap {@link SpeedController} objects. Is a
 * fully functional {@link SpeedController}, but also encompasses the features
 * from {@link Module}. When it is enabled, it acts normally, but when disabled
 * it only sets the speed to 0.
 *
 * @author Joel Gallant
 */
public class SpeedControllerModule implements Module, SpeedController {

    private boolean enabled;
    private final SpeedController speedController;

    /**
     * Constructs the wrapper object with the wrapped {@code SpeedController}.
     *
     * @param speedController object to use underneath this class
     */
    public SpeedControllerModule(SpeedController speedController) {
        this.speedController = speedController;
    }

    /**
     * Allows the speed controller to set the speed to anything from -1 to +1.
     *
     * @return whether module was successfully enabled
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether the module is currently enabled. If it is, the controller
     * can set the speed to anything.
     *
     * @return if the module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Stops the module from being able to set the speed to anything but 0. Also
     * stops the motor when called.
     */
    public void disable() {
        speedController.set(0);
        enabled = false;
    }

    /**
     * If the module is enabled, sets the speed of the speed controller.
     * Otherwise sets to 0.
     *
     * @param speed The speed to set. Value should be between -1.0 and 1.0.
     * @param syncGroup The update group to add this Set() to, pending
     * UpdateSyncGroup(). If 0, update immediately.
     */
    public void set(double speed, byte syncGroup) {
        speedController.set(isEnabled() ? speed : 0, syncGroup);
    }

    /**
     * If the module is enabled, sets the speed of the speed controller.
     * Otherwise sets to 0.
     *
     * @param speed The speed to set. Value should be between -1.0 and +1.0.
     */
    public void set(double speed) {
        speedController.set(isEnabled() ? speed : 0);
    }

    /**
     * Returns the current speed that is being set for the controller.
     *
     * @return the current speed between -1.0 and +1.0.
     */
    public double get() {
        return speedController.get();
    }

    /**
     * Calls {@link SpeedControllerModule#set(double)}.
     *
     * @param output speed between -1.0 to +1.0.
     */
    public void pidWrite(double output) {
        set(output);
    }
}
