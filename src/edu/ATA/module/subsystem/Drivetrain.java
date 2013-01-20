package edu.ATA.module.subsystem;

import edu.ATA.module.Module;
import edu.ATA.module.driving.RobotDriveModule;
import edu.ATA.module.joystick.XboxController;

/**
 * Module to control a basic drivetrain. Uses a {@link RobotDriveModule} and
 * {@link XboxController} to move the robot.
 *
 * @author joel
 */
public class Drivetrain implements Module.DisableableModule {

    protected final RobotDriveModule driveModule;
    protected final XboxController controller;
    private final boolean arcadeDrive;

    /**
     * Constructs the drivetrain using a {@link RobotDriveModule} and
     * {@link XboxController}. If you are not using arcade drive, this defaults
     * to tank drive. It's generally advisable to only use the modules here, and
     * not use them elsewhere, as references to the same objects can cause
     * conflicts.
     *
     * @param driveModule driving module
     * @param controller control to move robot with
     * @param arcadeDrive whether to use arcade or tank
     */
    public Drivetrain(RobotDriveModule driveModule, XboxController controller, boolean arcadeDrive) {
        this.driveModule = driveModule;
        this.controller = controller;
        this.arcadeDrive = arcadeDrive;
    }

    /**
     * Disables the driving module and controller.
     *
     * @return if they disabled correctly
     */
    public boolean disable() {
        driveModule.disable();
        controller.disable();
        return !isEnabled();
    }

    /**
     * Enables the driving module and controller.
     *
     * @return if they enabled correctly
     */
    public boolean enable() {
        driveModule.enable();
        controller.enable();
        return isEnabled();
    }

    /**
     * Returns whether or not both modules are enabled.
     *
     * @return if both modules are enabled
     */
    public boolean isEnabled() {
        return driveModule.isEnabled() && controller.isEnabled();
    }

    /**
     * Drives the drivetrain using the settings.
     */
    public void drive() {
        if (arcadeDrive) {
            driveModule.arcadeDrive(controller.getLeftDistanceFromMiddle(), controller.getRightX());
        } else {
            driveModule.tankDrive(controller.getLeftDistanceFromMiddle(), controller.getRightDistanceFromMiddle());
        }
    }
}
