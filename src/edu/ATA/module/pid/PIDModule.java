package edu.ATA.module.pid;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.PIDController;

/**
 * Module designed to use a PID controller (see
 * http://www.chiefdelphi.com/forums/showthread.php?threadid=110268) to use
 * setpoints to attain the correct position or speed.
 *
 * @author Joel Gallant
 */
public class PIDModule extends ForwardingPIDController implements Module.DisableableModule {

    /**
     * Constructs the object by using composition, using the given controller
     * object to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    public PIDModule(PIDController controller) {
        super(controller);
    }

    /**
     * Enables the controller. Is the same thing as
     * {@link edu.wpi.first.wpilibj.PIDController#enable()}. Begins the
     * execution of the PID task.
     *
     * @return whether controller has successfully enabled
     */
    public boolean enable() {
        getController().enable();
        return isEnabled();
    }

    /**
     * Returns whether the controller is enabled (running).
     *
     * @return if controller is running
     */
    public boolean isEnabled() {
        return getController().isEnable();
    }

    /**
     * Stops the controller from running. Is the same thing as
     * {@link edu.wpi.first.wpilibj.PIDController#disable()}. Prevents
     * controller from moving the PIDOutput.
     *
     * @return whether controller has successfully disabled
     */
    public boolean disable() {
        getController().disable();
        return !isEnabled();
    }
}
