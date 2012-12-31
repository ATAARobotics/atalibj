package edu.ATA.module.driving;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Module designed to drive robots. Has all of the benefits of
 * {@link edu.wpi.first.wpilibj.RobotDrive}, as well as {@link RobotDrive}. When
 * it is not enabled, this class will not move the robot. (the max speed is set
 * to 0)
 *
 * @author Joel Gallant
 */
public class RobotDriveModule extends ForwardingRobotDrive implements Module.DisableableModule {

    private boolean enabled;
    private double maxSpeed = 1;

    /**
     * Constructs the object by using composition, using the given robot drive
     * object to control methods in this class.
     *
     * @param drive actual underlying class used
     */
    public RobotDriveModule(RobotDrive drive) {
        super(drive);
    }

    /**
     * Enables the robot drive to drive normally. Sets the max speed back to
     * what it was before disabling. (default is 1 - this should usually not be
     * changed)
     *
     * @return returns whether it successfully enabled and is ready to work
     */
    public boolean enable() {
        setMaxOutput(maxSpeed);
        return (enabled = true);
    }

    /**
     * Returns whether or not the object can move motors successfully.
     *
     * @return if module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Disables the module. This effectively stops all of the motors from
     * running.
     *
     * @return whether the module successfully disabled
     */
    public boolean disable() {
        super.setMaxOutput(0);
        stopMotors();
        return !(enabled = false);
    }

    /**
     * {@inheritDoc}
     */
    public void setMaxOutput(double maxOutput) {
        super.setMaxOutput(maxSpeed = maxOutput);
    }
}
